package com.example.krkapartments.domain.booking;

import com.example.krkapartments.domain.apartment.Apartment;
import com.example.krkapartments.domain.user.User;
import com.example.krkapartments.persistence.shared.BookingPayment;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
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
