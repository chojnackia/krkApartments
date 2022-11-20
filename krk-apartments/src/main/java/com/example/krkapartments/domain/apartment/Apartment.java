package com.example.krkapartments.domain.apartment;

import com.example.krkapartments.domain.address.Address;
import com.example.krkapartments.domain.booking.Booking;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Apartment {
    private UUID id;
    private String apartmentName;
    private int priceForOneDay;
    private String apartmentDescription;
    private boolean active;
    private String bookingUrl;
    private List<Booking> bookings = new ArrayList<>();
    private Address address;
}
