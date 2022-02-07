package com.example.krkapartments.module.apartment;

import com.example.krkapartments.module.address.Address;
import com.example.krkapartments.module.booking.Booking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ApartmentDto {
    private UUID id;
    private String apartmentName;
    private double priceForOneDay;
    private String apartmentDescription;
    private boolean active;
    private List<Booking> bookings;
    private Address address;
}
