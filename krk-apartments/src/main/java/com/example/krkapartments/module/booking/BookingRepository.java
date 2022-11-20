package com.example.krkapartments.module.booking;

import com.example.krkapartments.module.apartment.ApartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<BookingEntity, UUID> {

    @Query("SELECT b FROM BookingEntity b WHERE b.apartment = ?1 AND (b.checkInDate BETWEEN ?2 AND ?3 OR b.checkOutDate BETWEEN ?2 AND ?3)")
    List<BookingEntity> findAllByApartmentEqualsAndCheckInDateIsBetweenOrCheckOutDateIsBetween(ApartmentEntity apartmentEntity, LocalDate checkInDate, LocalDate checkOutDate);

    List<BookingEntity> findAllByApartmentEqualsAndAndCheckInDateIsBeforeAndAndCheckOutDateIsAfter(ApartmentEntity apartmentEntity, LocalDate checkInDate, LocalDate checkOutDate);
}