package com.example.krkapartments.business.booking;

import com.example.krkapartments.business.apartment.ApartmentService;
import com.example.krkapartments.domain.apartment.Apartment;
import com.example.krkapartments.domain.booking.Booking;
import com.example.krkapartments.domain.booking.BookingMapper;
import com.example.krkapartments.endpoint.apartment.exception.ApartmentNotFoundException;
import com.example.krkapartments.endpoint.booking.exception.BookingNotFoundException;
import com.example.krkapartments.exception.FieldDoesNotExistException;
import com.example.krkapartments.persistence.booking.entity.BookingEntity;
import com.example.krkapartments.persistence.booking.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ApartmentService apartmentService;
    private final BookingMapper bookingMapper;

    @Transactional
    public Optional<Booking> save(Booking booking) {
        return Optional.ofNullable(booking)
                .map(bookingMapper::mapFromDomainToEntity)
                .map(bookingRepository::save)
                .map(bookingMapper::mapFromEntityToDomain);
    }

//    @SneakyThrows
    //TODO refactor this method COMPLETELY
//    public BookingDto addBooking(BookingDto bookingDto) {
//
//        ApartmentEntity apartmentEntityInDatabase = apartmentService.findApartmentInDatabase(bookingDto.getApartmentId());
//        BookingEntity bookingEntity = BookingConverter.convertToBooking(bookingDto, apartmentEntityInDatabase);
//        bookingEntity.setId(UUID.randomUUID());
//        bookingEntity.setPaymentStatus(BookingPayment.NOT_PAID);
//        LocalDate checkInDate = bookingDto.getCheckInDate();
//        LocalDate checkOutDate = bookingDto.getCheckOutDate();
//        bookingEntity.setPrice(priceForBooking(checkInDate, checkOutDate, bookingDto.getApartmentId()));
//
//        if (isApartmentOccupied(bookingDto)) {
//            bookingRepository.save(bookingEntity);
//            return BookingConverter.convertToBookingDto(bookingEntity);
//        } else
//            throw new ApartmentIsOccupiedException("Apartment is occupied between " + checkInDate + " - " + checkOutDate);
//    }

    public List<Booking> findAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(bookingMapper::mapFromEntityToDomain)
                .collect(Collectors.toList());
    }

    public void deleteBooking(UUID id) {
        Optional.ofNullable(id)
                .map(this::findBookingInDatabase)
                .map(BookingEntity::getId)
                .ifPresent(bookingRepository::deleteById);
    }

    public BookingEntity findBookingInDatabase(UUID id) {
        return bookingRepository.findById(id).orElseThrow(BookingNotFoundException::new);
    }

    public Optional<Booking> updateBooking(UUID id, Map<Object, Object> fields) {
        BookingEntity booking = findBookingInDatabase(id);
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(BookingEntity.class, (String) key);
            if (field == null) {
                throw new FieldDoesNotExistException("Field " + key + " does not exist");
            }
            field.setAccessible(true);
            ReflectionUtils.setField(field, booking, value);
        });
        return Optional.of(bookingRepository.save(booking))
                .map(bookingMapper::mapFromEntityToDomain);
    }

    //TODO make this a separate validator
//    private Boolean isApartmentOccupied(Booking booking) {
//
//        List<BookingEntity> occupiedApartments = bookingRepository.findAllByApartmentEqualsAndCheckInDateIsBetweenOrCheckOutDateIsBetween(
//                apartmentService.findApartmentInDatabase(booking.getApartmentId()),
//                booking.getCheckInDate(),
//                booking.getCheckOutDate());
//
//        List<BookingEntity> occupiedApartmentsV2 = bookingRepository.findAllByApartmentEqualsAndAndCheckInDateIsBeforeAndAndCheckOutDateIsAfter(
//                apartmentService.findApartmentInDatabase(booking.getApartmentId()),
//                booking.getCheckInDate(),
//                booking.getCheckOutDate());
//
//        return (occupiedApartments.isEmpty() && occupiedApartmentsV2.isEmpty());
//    }

    public List<Booking> findAllBookingsByApartment(UUID id) {
        return bookingRepository.findAllByApartmentUuid(id)
                .stream()
                .map(bookingMapper::mapFromEntityToDomain)
                .collect(Collectors.toList());
    }

    private int priceForBooking(LocalDate checkInDate, LocalDate checkOutDate, UUID apartmentId) {
        Apartment apartment = apartmentService.findApartmentInDatabase(apartmentId)
                .orElseThrow(ApartmentNotFoundException::new);

        int days = checkInDate.until(checkOutDate).getDays();
        return days * apartment.getPriceForOneDay();
    }
}




