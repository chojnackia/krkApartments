package com.example.krkapartments.module.booking;

import com.example.krkapartments.module.apartment.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

    /*List<Booking> findAllByApartmentEqualsAndCheckInDateIsBetweenOrCheckOutDateIsBetween(Apartment apartment, LocalDate checkInDate, LocalDate checkOutDate, LocalDate checkInDate1, LocalDate checkOutDate1);

    List<Booking> findAllByApartmentEqualsAndCheckInDateBeforeAndCheckOutDateAfter(Apartment apartment, LocalDate checkInDate, LocalDate checkOutDate);
    */
    List<Booking> findAllByCheckInDateIsBetweenOrCheckOutDateIsBetween(LocalDate checkInDate, LocalDate checkOutDate, LocalDate checkInDate1, LocalDate checkOutDate1);

    List<Booking> findAllByCheckInDateBeforeAndCheckOutDateAfter(LocalDate checkInDate, LocalDate checkOutDate);

    List<Booking> findAllByApartment(Apartment apartment);
}