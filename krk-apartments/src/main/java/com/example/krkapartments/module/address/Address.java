package com.example.krkapartments.module.address;

import com.example.krkapartments.module.apartment.Apartment;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
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
