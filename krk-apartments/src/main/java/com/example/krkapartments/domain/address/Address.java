package com.example.krkapartments.domain.address;

import com.example.krkapartments.domain.apartment.Apartment;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class Address {

    private UUID id;
    private String city;
    private String streetName;
    private int buildingNumber;
    private int apartmentNumber;
    private String postCode;
    private String country;
    private Apartment apartment;
}
