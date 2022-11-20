package com.example.krkapartments.business.booking;

import com.example.krkapartments.business.apartment.ApartmentService;
import com.example.krkapartments.endpoint.apartment.exception.ApartmentIsOccupiedException;
import com.example.krkapartments.endpoint.booking.dto.BookingDto;
import com.example.krkapartments.endpoint.booking.exception.BookingNotFoundException;
import com.example.krkapartments.exception.FieldDoesNotExistException;
import com.example.krkapartments.persistence.apartment.entity.ApartmentEntity;
import com.example.krkapartments.persistence.booking.entity.BookingEntity;
import com.example.krkapartments.persistence.booking.repository.BookingRepository;
import com.example.krkapartments.persistence.shared.BookingPayment;
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

    @SneakyThrows
    public BookingDto addBooking(BookingDto bookingDto) {

        ApartmentEntity apartmentEntityInDatabase = apartmentService.findApartmentInDatabase(bookingDto.getApartmentId());
        BookingEntity bookingEntity = BookingConverter.convertToBooking(bookingDto, apartmentEntityInDatabase);
        bookingEntity.setId(UUID.randomUUID());
        bookingEntity.setPaymentStatus(BookingPayment.NOT_PAID);
        LocalDate checkInDate = bookingDto.getCheckInDate();
        LocalDate checkOutDate = bookingDto.getCheckOutDate();
        bookingEntity.setPrice(priceForBooking(checkInDate, checkOutDate, bookingDto.getApartmentId()));

        if (isApartmentOccupied(bookingDto)) {
            bookingRepository.save(bookingEntity);
            return BookingConverter.convertToBookingDto(bookingEntity);
        } else
            throw new ApartmentIsOccupiedException("Apartment is occupied between " + checkInDate + " - " + checkOutDate);
    }

    public List<BookingDto> findAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(BookingConverter::convertToBookingDto)
                .collect(Collectors.toList());
    }

    public BookingEntity deleteBooking(UUID id) {
        BookingEntity bookingEntityToDelete = findBookingInDatabase(id);
        bookingRepository.deleteById(id);
        return bookingEntityToDelete;
    }

    public BookingEntity findBookingInDatabase(UUID id) {
        return bookingRepository.findById(id).orElseThrow(() ->
                new BookingNotFoundException("Could not find project with id: " + id));
    }

    public BookingDto updateBooking(UUID id, Map<Object, Object> fields) {
        BookingEntity bookingEntity = findBookingInDatabase(id);
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(BookingEntity.class, (String) key);
            if (field == null) {
                throw new FieldDoesNotExistException("Field " + key + " does not exist");
            }
            field.setAccessible(true);
            ReflectionUtils.setField(field, bookingEntity, value);
        });
        bookingRepository.save(bookingEntity);
        return BookingConverter.convertToBookingDto(bookingEntity);
    }

    private Boolean isApartmentOccupied(BookingDto bookingDto) {

        List<BookingEntity> occupiedApartments = bookingRepository.findAllByApartmentEqualsAndCheckInDateIsBetweenOrCheckOutDateIsBetween(
                apartmentService.findApartmentInDatabase(bookingDto.getApartmentId()),
                bookingDto.getCheckInDate(),
                bookingDto.getCheckOutDate());

        List<BookingEntity> occupiedApartmentsV2 = bookingRepository.findAllByApartmentEqualsAndAndCheckInDateIsBeforeAndAndCheckOutDateIsAfter(
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
        ApartmentEntity apartmentEntityInDatabase = apartmentService.findApartmentInDatabase(apartmentId);

        int days = checkInDate.until(checkOutDate).getDays();
        return days * apartmentEntityInDatabase.getPriceForOneDay();
    }
}
