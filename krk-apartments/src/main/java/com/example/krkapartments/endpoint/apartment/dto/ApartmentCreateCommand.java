package com.example.krkapartments.endpoint.apartment.dto;

import com.example.krkapartments.endpoint.address.dto.AddressCreateCommand;
import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
public class ApartmentCreateCommand {
    private String apartmentName;
    private int priceForOneDay;
    private String apartmentDescription;
    private String bookingUrl;
    private boolean active;
    private AddressCreateCommand address;
}
