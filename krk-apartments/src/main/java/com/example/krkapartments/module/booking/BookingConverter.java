package com.example.krkapartments.module.booking;

import com.example.krkapartments.module.apartment.ApartmentEntity;

import java.util.List;
import java.util.stream.Collectors;

public class BookingConverter {

    public static BookingDto convertToBookingDto(BookingEntity bookingEntity) {
        if (bookingEntity == null) return null;
        return BookingDto.builder()
                .id(bookingEntity.getId())
                .userEntity(bookingEntity.getUser())
                .apartmentId(bookingEntity.getApartment().getId())
                .checkInDate(bookingEntity.getCheckInDate())
                .checkOutDate(bookingEntity.getCheckOutDate())
                .paymentStatus(bookingEntity.getPaymentStatus())
                .build();
    }

    public static BookingEntity convertToBooking(BookingDto bookingDto, ApartmentEntity apartmentEntity) {
        if (bookingDto == null) return null;
        return BookingEntity.builder()
                .id(bookingDto.getId())
                .userEntity(bookingDto.getUserEntity())
                .apartmentEntity(apartmentEntity)
                .checkInDate(bookingDto.getCheckInDate())
                .checkOutDate(bookingDto.getCheckOutDate())
                .paymentStatus(bookingDto.getPaymentStatus())
                .build();
    }

/*    public static List<Booking> convertToBookingDtoList(List<BookingDto> bookingDtos) {
        return bookingDtos.stream()
                .map(BookingConverter::convertToBooking)
                .collect(Collectors.toList());
    }*/

    public static List<BookingDto> convertToBookingList(List<BookingEntity> bookingEntities) {
        return bookingEntities.stream()
                .map(BookingConverter::convertToBookingDto)
                .collect(Collectors.toList());
    }

}
