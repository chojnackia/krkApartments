package com.example.krkapartments.module.apartment;

import com.example.krkapartments.module.address.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private boolean occupied;
    private Address address;
}
