package com.example.krkapartments.module.booking;

import com.example.krkapartments.module.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BookingDto {

    private UUID id;
    private UUID apartmentId;
    private UserEntity userEntity;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private BookingPayment paymentStatus;
}
