package com.example.krkapartments.endpoint.address.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddressCreateCommand {
    private String city;
    private String streetName;
    private int buildingNumber;
    private int apartmentNumber;
    private String postCode;
    private String country;
}
