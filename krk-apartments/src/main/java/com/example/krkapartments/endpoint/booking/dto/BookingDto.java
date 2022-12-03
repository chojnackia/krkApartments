package com.example.krkapartments.endpoint.booking.dto;

import com.example.krkapartments.endpoint.apartment.dto.ApartmentDto;
import com.example.krkapartments.endpoint.user.dto.UserDto;
import com.example.krkapartments.persistence.shared.BookingPayment;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class BookingDto {

    private UUID id;
    private ApartmentDto apartment;
    private UserDto user;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private BookingPayment paymentStatus;
}
