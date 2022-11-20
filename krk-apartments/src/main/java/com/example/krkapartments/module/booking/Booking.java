package com.example.krkapartments.module.booking;

import com.example.krkapartments.module.apartment.Apartment;
import com.example.krkapartments.module.user.User;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Booking {
    private UUID id;
    private User user;
    private Apartment apartment;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int price;
    private BookingPayment paymentStatus;
}
