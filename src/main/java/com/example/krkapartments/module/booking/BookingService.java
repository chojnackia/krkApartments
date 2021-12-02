package com.example.krkapartments.module.booking;

import com.example.krkapartments.module.apartment.Apartment;
import com.example.krkapartments.module.apartment.ApartmentConverter;
import com.example.krkapartments.module.apartment.ApartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ApartmentService apartmentService;
    private final ApartmentConverter apartmentConverter;

/*    public List<BookingDto> findAllByOccupiedIsFalse() {
        List<Booking> bookingList = bookingRepository.findAllByOccupiedIsFalse();
        return bookingList.stream()
                .map(BookingConverter::convertToBookingDto)
                .collect(Collectors.toList());
    }

    public List<BookingDto> findAllByOccupiedIsTrue() {
        List<Booking> bookingList = bookingRepository.findAllByOccupiedIsTrue();
        return bookingList.stream()
                .map(BookingConverter::convertToBookingDto)
                .collect(Collectors.toList());

    }*/

    public BookingDto addBooking(BookingDto bookingDto) throws ApartmentIsOccupiedException {
        Apartment apartmentInDatabase = apartmentService.findApartmentInDatabase(bookingDto.getApartmentId());
        Booking booking = BookingConverter.convertToBooking(bookingDto, apartmentInDatabase);
        booking.setId(UUID.randomUUID());
        booking.setIsOccupied(setOccupiedToTrue(bookingDto));
        bookingRepository.save(booking);
        return BookingConverter.convertToBookingDto(booking);
    }

    public List<BookingDto> findAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(BookingConverter::convertToBookingDto)
                .collect(Collectors.toList());
    }

    public Booking deactivateBooking(UUID id) {
        Booking booking = findBookingInDatabase(id);
        //booking.setOccupied(false);
        bookingRepository.save(booking);
        return booking;
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

    public Map<LocalDate, Boolean> setOccupiedToTrue(BookingDto bookingDto) throws ApartmentIsOccupiedException {
        Map<LocalDate, Boolean> isOccupiedAtDate = new HashMap<>();

        List<Booking> occupiedApartments = bookingRepository.findAllByApartmentEqualsAndCheckInDateIsBetweenOrCheckOutDateIsBetween(
                apartmentService.findApartmentInDatabase(bookingDto.getApartmentId()),
                bookingDto.getCheckInDate(),
                bookingDto.getCheckOutDate(),
                bookingDto.getCheckInDate(),
                bookingDto.getCheckOutDate());

        List<Booking> anotherOccupiedApartments = bookingRepository.findAllByApartmentEqualsAndCheckInDateBeforeAndCheckOutDateAfter(
                apartmentService.findApartmentInDatabase(bookingDto.getApartmentId()),
                bookingDto.getCheckInDate(),
                bookingDto.getCheckOutDate());

        LocalDate checkInDate = bookingDto.getCheckInDate();
        LocalDate checkOutDate = bookingDto.getCheckOutDate();

        if (occupiedApartments.isEmpty() && anotherOccupiedApartments.isEmpty()) {
            for (LocalDate date = checkInDate; date.isBefore(checkOutDate.plusDays(1)); date = date.plusDays(1)){
                isOccupiedAtDate.put(date, true);
            }
            return isOccupiedAtDate;
        } else {
            throw new ApartmentIsOccupiedException("Apartment is occupied between " + checkInDate + " - " + checkOutDate);
        }
    }
}
