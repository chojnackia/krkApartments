package com.example.krkapartments.generator;

import com.example.krkapartments.module.address.Address;
import com.example.krkapartments.module.address.AddressDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AddressGenerator {

    private static final UUID id1 = UUID.randomUUID();
    private static final UUID id2 = UUID.randomUUID();
    private static final UUID id3 = UUID.randomUUID();
    private static final UUID id4 = UUID.randomUUID();
    private static final UUID id5 = UUID.randomUUID();

    static List<Address> generateAddressList() {
        List<Address> addresses = new ArrayList<>();
        Address address1 = new Address(id1, "Poznań", "Lechicka", 8, 5, "61-101", "Poland", null);
        Address address2 = new Address(id2, "Poznań", "Polska", 7, 4, "61-201", "Poland", null);
        Address address3 = new Address(id3, "Poznań", "Długa", 6, 3, "61-301", "Poland", null);
        Address address4 = new Address(id4, "Poznań", "Krótka", 5, 2, "61-401", "Poland", null);
        Address address5 = new Address(id5, "Poznań", "Zamek", 4, 1, "61-501", "Poland", null);

        addresses.add(address1);
        addresses.add(address2);
        addresses.add(address3);
        addresses.add(address4);
        addresses.add(address5);
        return addresses;
    }

    static List<AddressDto> generateAddressDtoList() {
        List<AddressDto> addressesDtos = new ArrayList<>();
        AddressDto address1 = new AddressDto(id1, "Poznań", "Lechicka", 8, 5, "61-101", "Poland");
        AddressDto address2 = new AddressDto(id2, "Poznań", "Polska", 7, 4, "61-201", "Poland");
        AddressDto address3 = new AddressDto(id3, "Poznań", "Długa", 6, 3, "61-301", "Poland");
        AddressDto address4 = new AddressDto(id4, "Poznań", "Krótka", 5, 2, "61-401", "Poland");
        AddressDto address5 = new AddressDto(id5, "Poznań", "Zamek", 4, 1, "61-501", "Poland");

        addressesDtos.add(address1);
        addressesDtos.add(address2);
        addressesDtos.add(address3);
        addressesDtos.add(address4);
        addressesDtos.add(address5);
        return addressesDtos;
    }
}