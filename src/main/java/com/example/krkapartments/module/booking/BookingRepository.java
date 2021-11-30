package com.example.krkapartments.module.booking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

    List<Booking> findAllByOccupiedIsFalse();

    List<Booking> findAllByOccupiedIsTrue();
}
