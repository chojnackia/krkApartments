package com.example.krkapartments.module.booking;

import com.example.krkapartments.consumer.RestConsumerImpl;
import com.example.krkapartments.exception.ApartmentIsOccupiedException;
import com.example.krkapartments.exception.BookingNotFoundException;
import com.example.krkapartments.exception.FieldDoesNotExistException;
import com.example.krkapartments.module.apartment.Apartment;
import com.example.krkapartments.module.apartment.ApartmentService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

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
    private final RestConsumerImpl restConsumer;

    @SneakyThrows
    public BookingDto addBooking(BookingDto bookingDto) {

        Apartment apartmentInDatabase = apartmentService.findApartmentInDatabase(bookingDto.getApartmentId());
        Booking booking = BookingConverter.convertToBooking(bookingDto, apartmentInDatabase);
        booking.setId(UUID.randomUUID());
        booking.setPaymentStatus(BookingPayment.NOT_PAID);
        LocalDate checkInDate = bookingDto.getCheckInDate();
        LocalDate checkOutDate = bookingDto.getCheckOutDate();
        booking.setPrice(priceForBooking(checkInDate, checkOutDate, bookingDto.getApartmentId()));

        if (isApartmentOccupied(bookingDto)) {
            bookingRepository.save(booking);
            return BookingConverter.convertToBookingDto(booking);
        } else
            throw new ApartmentIsOccupiedException("Apartment is occupied between " + checkInDate + " - " + checkOutDate);
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

    private Boolean isApartmentOccupied(BookingDto bookingDto) {

        List<Booking> occupiedApartments = bookingRepository.findAllByApartmentEqualsAndCheckInDateIsBetweenOrCheckOutDateIsBetween(
                apartmentService.findApartmentInDatabase(bookingDto.getApartmentId()),
                bookingDto.getCheckInDate(),
                bookingDto.getCheckOutDate());

        List<Booking> occupiedApartmentsV2 = bookingRepository.findAllByApartmentEqualsAndAndCheckInDateIsBeforeAndAndCheckOutDateIsAfter(
                apartmentService.findApartmentInDatabase(bookingDto.getApartmentId()),
                bookingDto.getCheckInDate(),
                bookingDto.getCheckOutDate());

        return (occupiedApartments.isEmpty() && occupiedApartmentsV2.isEmpty());
    }

    public List<BookingDto> findAllBookingsByApartment(UUID id) {
        return bookingRepository.findAll()
                .stream()
                .filter(p -> p.getApartment().getId().equals(id))
                .map(BookingConverter::convertToBookingDto)
                .collect(Collectors.toList());
    }

    private int priceForBooking(LocalDate checkInDate, LocalDate checkOutDate, UUID apartmentId) {
        Apartment apartmentInDatabase = apartmentService.findApartmentInDatabase(apartmentId);

        int days = checkInDate.until(checkOutDate).getDays();
        return days * apartmentInDatabase.getPriceForOneDay();
    }
}
