package com.example.krkapartments.generator;

import com.example.krkapartments.endpoint.booking.dto.BookingDto;
import com.example.krkapartments.persistence.apartment.entity.ApartmentEntity;
import com.example.krkapartments.persistence.booking.entity.BookingEntity;
import com.example.krkapartments.persistence.user.entity.UserEntity;
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

    static List<BookingEntity> generateBookingList() {
        List<BookingEntity> bookingEntities = new ArrayList<>();

        BookingEntity bookingEntity1 = BookingEntity.builder()
                .id(id1)
                .userEntity(new UserEntity())
                .apartmentEntity(null)
                .checkInDate(LocalDate.of(2021, 10, 1))
                .checkOutDate(LocalDate.of(2021, 10, 10))
                .build();

        BookingEntity bookingEntity2 = BookingEntity.builder()
                .id(id2)
                .userEntity(new UserEntity())
                .apartmentEntity(new ApartmentEntity())
                .checkInDate(LocalDate.of(2021, 10, 1))
                .checkOutDate(LocalDate.of(2021, 10, 10))
                .build();

        BookingEntity bookingEntity3 = BookingEntity.builder()
                .id(id3)
                .userEntity(new UserEntity())
                .apartmentEntity(new ApartmentEntity())
                .checkInDate(LocalDate.of(2021, 10, 11))
                .checkOutDate(LocalDate.of(2021, 10, 15))
                .build();

        bookingEntities.add(bookingEntity1);
        bookingEntities.add(bookingEntity2);
        bookingEntities.add(bookingEntity3);

        return bookingEntities;
    }

    static List<BookingDto> generateBookingDtoList() {
        List<BookingDto> bookingDtos = new ArrayList<>();

        BookingDto bookingDto1 = BookingDto.builder()
                .id(id1)
                .apartmentId(new ApartmentEntity().getId())
                .checkInDate(LocalDate.of(2021, 10, 1))
                .checkOutDate(LocalDate.of(2021, 10, 10))
                .userEntity(new UserEntity()).build();

        BookingDto bookingDto2 = BookingDto.builder()
                .id(id2)
                .apartmentId(new ApartmentEntity().getId())
                .checkInDate(LocalDate.of(2021, 10, 1))
                .checkOutDate(LocalDate.of(2021, 10, 10))
                .userEntity(new UserEntity()).build();

        BookingDto bookingDto3 = BookingDto.builder()
                .id(id3)
                .apartmentId(new ApartmentEntity().getId())
                .checkInDate(LocalDate.of(2021, 10, 11))
                .checkOutDate(LocalDate.of(2021, 10, 15))
                .userEntity(new UserEntity()).build();

        bookingDtos.add(bookingDto1);
        bookingDtos.add(bookingDto2);
        bookingDtos.add(bookingDto3);

        return bookingDtos;
    }
}
