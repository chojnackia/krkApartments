package com.example.krkapartments.module.booking;

import com.example.krkapartments.module.apartment.Apartment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public class BookingConverter {

    public static BookingDto convertToBookingDto(Booking booking) {
        if (booking == null) return null;
        return BookingDto.builder()
                .id(booking.getId())
                .user(booking.getUser())
                .apartmentId(booking.getApartment().getId())
                .checkInDate(booking.getCheckInDate())
                .checkOutDate(booking.getCheckOutDate())
                .build();
    }

    public static Booking convertToBooking(BookingDto bookingDto, Apartment apartment) {
        if (bookingDto == null) return null;
        return Booking.builder()
                .id(bookingDto.getId())
                .user(bookingDto.getUser())
                .apartment(apartment)
                .checkInDate(bookingDto.getCheckInDate())
                .checkOutDate(bookingDto.getCheckOutDate())
                .build();
    }

/*    public static List<Booking> convertToBookingDtoList(List<BookingDto> bookingDtos) {
        return bookingDtos.stream()
                .map(BookingConverter::convertToBooking)
                .collect(Collectors.toList());
    }*/

    public static List<BookingDto> convertToBookingList(List<Booking> bookings) {
        return bookings.stream()
                .map(BookingConverter::convertToBookingDto)
                .collect(Collectors.toList());
    }

}
