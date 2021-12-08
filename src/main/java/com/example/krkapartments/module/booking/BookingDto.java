package com.example.krkapartments.module.booking;

import com.example.krkapartments.module.user.User;
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
    private User user;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    //private boolean occupied;
}
