package com.example.krkapartments.module.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class BookingServiceTest {


    @Mock
    private BookingRepository bookingRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldAddBooking() {
    }

    @Test
    void findAllBookings() {
    }

    @Test
    void deactivateBooking() {
    }

    @Test
    void findBookingInDatabase() {
    }

    @Test
    void updateBooking() {
    }

    @Test
    void setOccupiedToTrue() {
    }
}