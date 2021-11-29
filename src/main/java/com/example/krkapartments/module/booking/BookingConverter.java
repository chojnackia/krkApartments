package com.example.krkapartments.module.booking;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingConverter {

    public static BookingDto convertToBookingDto(Booking booking) {
        if (booking == null) return null;
        return BookingDto.builder()
                .id(booking.getId())
                .user(booking.getUser())
                .apartment(booking.getApartment())
                .checkinDate(booking.getCheckInDate())
                .checkoutDate(booking.getCheckOutDate())
                .occupied(booking.isOccupied())
                .build();
    }

    public static Booking convertToBooking(BookingDto bookingDto) {
        if (bookingDto == null) return null;
        return Booking.builder()
                .id(bookingDto.getId())
                .user(bookingDto.getUser())
                .apartment(bookingDto.getApartment())
                .checkInDate(bookingDto.getCheckinDate())
                .checkOutDate(bookingDto.getCheckoutDate())
                .occupied(bookingDto.isOccupied())
                .build();
    }

    public static List<Booking> convertToBookingDtoList(List<BookingDto> bookingDtos) {
        return bookingDtos.stream()
                .map(BookingConverter::convertToBooking)
                .collect(Collectors.toList());
    }

    public static List<BookingDto> convertToBookingList(List<Booking> bookings) {
        return bookings.stream()
                .map(BookingConverter::convertToBookingDto)
                .collect(Collectors.toList());
    }

}
