package com.example.krkapartments.module.booking;

import com.example.krkapartments.exception.ApartmentIsOccupiedException;
import com.example.krkapartments.exception.BookingNotFoundException;
import com.example.krkapartments.exception.FieldDoesNotExistException;
import com.example.krkapartments.module.apartment.Apartment;
import com.example.krkapartments.module.apartment.ApartmentService;
import lombok.AllArgsConstructor;
import net.fortuna.ical4j.data.ParserException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ApartmentService apartmentService;

    public BookingDto addBooking(BookingDto bookingDto) throws ApartmentIsOccupiedException, ParserException, IOException {

        List<Booking> occupiedApartments = bookingRepository.findAllByApartmentEqualsAndCheckInDateIsBetweenOrCheckOutDateIsBetween(
                apartmentService.findApartmentInDatabase(bookingDto.getApartmentId()),
                bookingDto.getCheckInDate(),
                bookingDto.getCheckOutDate());

        List<Booking> occupiedApartmentsV2 = bookingRepository.findAllByApartmentEqualsAndAndCheckInDateIsBeforeAndAndCheckOutDateIsAfter(
                apartmentService.findApartmentInDatabase(bookingDto.getApartmentId()),
                bookingDto.getCheckInDate(),
                bookingDto.getCheckOutDate());

        LocalDate checkInDate = bookingDto.getCheckInDate();
        LocalDate checkOutDate = bookingDto.getCheckOutDate();

        Apartment apartmentInDatabase = apartmentService.findApartmentInDatabase(bookingDto.getApartmentId());
        Booking booking = BookingConverter.convertToBooking(bookingDto, apartmentInDatabase);
        booking.setId(UUID.randomUUID());


        if (occupiedApartments.isEmpty() && occupiedApartmentsV2.isEmpty()) {
            bookingRepository.save(booking);

        } else {
            throw new ApartmentIsOccupiedException("Apartment is occupied between " + checkInDate + " - " + checkOutDate);
        }
        return BookingConverter.convertToBookingDto(booking);
    }

    public List<BookingDto> findAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(BookingConverter::convertToBookingDto)
                .collect(Collectors.toList());
    }

    public Booking deleteBooking(UUID id) {
        Booking bookingToDelete = findBookingInDatabase(id);
        bookingRepository.deleteById(id);
        return bookingToDelete;
    }

    public Booking findBookingInDatabase(UUID id) {
        return bookingRepository.findById(id).orElseThrow(() ->
                new BookingNotFoundException("Could not find project with id: " + id));
    }

    public BookingDto updateBooking(UUID id, Map<Object, Object> fields) {
        Booking booking = findBookingInDatabase(id);
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Booking.class, (String) key);
            if (field == null) {
                throw new FieldDoesNotExistException("Field " + key + " does not exist");
            }
            field.setAccessible(true);
            ReflectionUtils.setField(field, booking, value);
        });
        bookingRepository.save(booking);
        return BookingConverter.convertToBookingDto(booking);
    }
}
