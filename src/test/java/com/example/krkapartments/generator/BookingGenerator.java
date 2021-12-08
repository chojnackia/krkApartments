package com.example.krkapartments.generator;

import com.example.krkapartments.module.apartment.Apartment;
import com.example.krkapartments.module.booking.Booking;
import com.example.krkapartments.module.booking.BookingDto;
import com.example.krkapartments.module.user.User;
import com.example.krkapartments.util.DateTimeProvider;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookingGenerator {

    private static final UUID id1 = UUID.randomUUID();
    private static final UUID id2 = UUID.randomUUID();
    private static final UUID id3 = UUID.randomUUID();
    private static final LocalDate now = DateTimeProvider.getLocalDateToday();

    static List<Booking> generateBookingList() {
        List<Booking> bookings = new ArrayList<>();

        Booking booking1 = Booking.builder()
                .id(id1)
                .user(new User())
                .apartment(new Apartment())
                .checkInDate(LocalDate.of(2021, 10, 1))
                .checkOutDate(LocalDate.of(2021, 10, 10))
                .build();

        Booking booking2 = Booking.builder()
                .id(id2)
                .user(new User())
                .apartment(new Apartment())
                .checkInDate(LocalDate.of(2021, 10, 1))
                .checkOutDate(LocalDate.of(2021, 10, 10))
                .build();

        Booking booking3 = Booking.builder()
                .id(id3)
                .user(new User())
                .apartment(new Apartment())
                .checkInDate(LocalDate.of(2021, 10, 11))
                .checkOutDate(LocalDate.of(2021, 10, 15))
                .build();

        bookings.add(booking1);
        bookings.add(booking2);
        bookings.add(booking3);

        return bookings;
    }

    static List<BookingDto> generateBookingDtoList(){
        List<BookingDto> bookingDtos = new ArrayList<>();

        BookingDto bookingDto1 = BookingDto.builder()
                .id(id1)
                .apartmentId(new Apartment().getId())
                .checkInDate(LocalDate.of(2021, 11, 1))
                .checkOutDate(LocalDate.of(2021, 11, 10))
                .user(new User()).build();

        BookingDto bookingDto2 = BookingDto.builder()
                .id(id2)
                .apartmentId(new Apartment().getId())
                .checkInDate(LocalDate.of(2021, 11, 1))
                .checkOutDate(LocalDate.of(2021, 11, 10))
                .user(new User()).build();

        BookingDto bookingDto3 = BookingDto.builder()
                .id(id3)
                .apartmentId(new Apartment().getId())
                .checkInDate(LocalDate.of(2021, 11, 11))
                .checkOutDate(LocalDate.of(2021, 11, 15))
                .user(new User()).build();

        bookingDtos.add(bookingDto1);
        bookingDtos.add(bookingDto2);
        bookingDtos.add(bookingDto3);

        return bookingDtos;
    }
}
