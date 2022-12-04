package com.example.krkapartments.persistence.booking.repository;

import com.example.krkapartments.persistence.apartment.entity.ApartmentEntity;
import com.example.krkapartments.persistence.booking.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<BookingEntity, UUID> {

    @Query("SELECT b FROM BookingEntity b WHERE b.apartment.id = :apartmentId AND (b.checkInDate <= :checkInDate AND b.checkOutDate >= :checkInDate " +
            "OR b.checkInDate <= :checkOutDate AND  b.checkOutDate >= :checkOutDate)")
    List<BookingEntity> findAllBookedBetween(@Param("apartmentId") UUID apartmentId, @Param("checkInDate") LocalDate checkInDate,
                                             @Param("checkOutDate") LocalDate checkOutDate);

    List<BookingEntity> findAllByApartmentEqualsAndAndCheckInDateIsBeforeAndAndCheckOutDateIsAfter(ApartmentEntity apartmentEntity, LocalDate checkInDate, LocalDate checkOutDate);

    @Query("SELECT b FROM BookingEntity b WHERE b.apartment.id = :uuid")
    List<BookingEntity> findAllByApartmentUuid(@Param("uuid") UUID uuid);
}