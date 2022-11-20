package com.example.krkapartments.domain.user;

import com.example.krkapartments.domain.booking.Booking;
import lombok.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String telephoneNumber;
    private List<Booking> bookings;
}
