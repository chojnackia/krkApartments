package com.example.krkapartments.module.booking;

import com.example.krkapartments.module.apartment.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

    List<Booking> findAllByOccupiedIsFalse(boolean occupied);
}
