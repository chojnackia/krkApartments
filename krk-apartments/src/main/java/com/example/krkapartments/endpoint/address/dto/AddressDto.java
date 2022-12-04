package com.example.krkapartments.endpoint.address.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private UUID id;
    private String city;
    private String streetName;
    private int buildingNumber;
    private int apartmentNumber;
    private String postCode;
    private String country;
}
