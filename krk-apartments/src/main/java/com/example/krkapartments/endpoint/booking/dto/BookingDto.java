package com.example.krkapartments.endpoint.booking.dto;

import com.example.krkapartments.persistence.shared.BookingPayment;
import com.example.krkapartments.persistence.user.entity.UserEntity;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookingDto {

    private UUID id;
    private UUID apartmentId;
    private UserEntity userEntity;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private BookingPayment paymentStatus;
}
