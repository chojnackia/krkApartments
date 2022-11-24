package com.example.krkapartments.endpoint.apartment.dto;

import com.example.krkapartments.endpoint.address.dto.AddressDto;
import com.example.krkapartments.endpoint.booking.dto.BookingDto;
import com.example.krkapartments.persistence.address.entity.AddressEntity;
import com.example.krkapartments.persistence.booking.entity.BookingEntity;
import lombok.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ApartmentDto {
    private UUID id;
    private String apartmentName;
    private int priceForOneDay;
    private String apartmentDescription;
    private String bookingUrl;
    private boolean active;
    private AddressDto address;
}
