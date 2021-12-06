package com.example.krkapartments.generator;

import com.example.krkapartments.module.address.Address;
import com.example.krkapartments.module.address.AddressDto;
import com.example.krkapartments.module.apartment.Apartment;
import com.example.krkapartments.module.apartment.ApartmentDto;
import lombok.Getter;

import java.util.List;

@Getter
public class ObjectGenerator {

    private final List<Address> addressList = AddressGenerator.generateAddressList();
    private final List<AddressDto> addressDtos = AddressGenerator.generateAddressDtoList();
    private final List<Apartment> apartmentList = ApartmentGenerator.generateApartmentList();
    private final List<ApartmentDto> apartmentDtos = ApartmentGenerator.generateApartmentDtoList();

    public void generateDependencies(List<Apartment> apartments, List<Address> addresses){
            apartments.get(0).setAddress(addresses.get(0));
            apartments.get(1).setAddress(addresses.get(1));
            apartments.get(2).setAddress(addresses.get(2));
            apartments.get(3).setAddress(addresses.get(3));
            apartments.get(4).setAddress(addresses.get(4));
    }

}
