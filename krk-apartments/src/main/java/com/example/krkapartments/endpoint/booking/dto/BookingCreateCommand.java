package com.example.krkapartments.endpoint.booking.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingCreateCommand {
    private UUID apartmentId;
    private UUID userId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
}
