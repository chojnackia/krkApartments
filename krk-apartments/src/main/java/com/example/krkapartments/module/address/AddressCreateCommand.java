package com.example.krkapartments.module.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
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
