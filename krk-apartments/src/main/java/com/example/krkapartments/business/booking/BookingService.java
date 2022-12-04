package com.example.krkapartments.business.booking;

import com.example.krkapartments.domain.booking.Booking;
import com.example.krkapartments.domain.booking.BookingMapper;
import com.example.krkapartments.endpoint.booking.exception.BookingNotFoundException;
import com.example.krkapartments.exception.FieldDoesNotExistException;
import com.example.krkapartments.persistence.booking.entity.BookingEntity;
import com.example.krkapartments.persistence.booking.repository.BookingRepository;
import com.example.krkapartments.persistence.shared.BookingPayment;
import lombok.RequiredArgsConstructor;
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
    private final BookingMapper bookingMapper;

    @Transactional
    public Optional<Booking> save(Booking booking) {
        return Optional.ofNullable(booking)
                .map(bookingMapper::mapFromDomainToEntity)
                .map(bookingRepository::save)
                .map(bookingMapper::mapFromEntityToDomain);
    }

    @Transactional
    public Optional<Booking> addBooking(Booking booking) {
        return Optional.ofNullable(booking)
                .map(b -> b.toBuilder()
                        .price(countPriceForBooking(b.getCheckInDate(), b.getCheckOutDate(), b.getApartment().getPriceForOneDay()))
                        .paymentStatus(BookingPayment.NOT_PAID)
                        .build())
                .flatMap(this::save);
    }

    @Transactional(readOnly = true)
    public List<Booking> findAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(bookingMapper::mapFromEntityToDomain)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteBooking(UUID id) {
        Optional.ofNullable(id)
                .map(this::findBookingInDatabase)
                .map(BookingEntity::getId)
                .ifPresent(bookingRepository::deleteById);
    }

    @Transactional(readOnly = true)
    public BookingEntity findBookingInDatabase(UUID id) {
        return bookingRepository.findById(id).orElseThrow(BookingNotFoundException::new);
    }

    @Transactional
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

    @Transactional(readOnly = true)
    public List<Booking> findAllBookingsByApartment(UUID id) {
        return bookingRepository.findAllByApartmentUuid(id)
                .stream()
                .map(bookingMapper::mapFromEntityToDomain)
                .collect(Collectors.toList());
    }

    private int countPriceForBooking(LocalDate checkInDate, LocalDate checkOutDate, int price) {
        int days = checkInDate.until(checkOutDate).getDays();
        return days * price;
    }
}




