package com.example.krkapartments.generator;

import com.example.krkapartments.endpoint.address.dto.AddressDto;
import com.example.krkapartments.persistence.address.entity.AddressEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AddressGenerator {

    private static final UUID id1 = UUID.randomUUID();
    private static final UUID id2 = UUID.randomUUID();
    private static final UUID id3 = UUID.randomUUID();
    private static final UUID id4 = UUID.randomUUID();
    private static final UUID id5 = UUID.randomUUID();

    static List<AddressEntity> generateAddressList() {
        List<AddressEntity> addressEntities = new ArrayList<>();
        AddressEntity addressEntity1 = new AddressEntity(id1, "Poznań", "Lechicka", 8, 5, "61-101", "Poland", null);
        AddressEntity addressEntity2 = new AddressEntity(id2, "Poznań", "Polska", 7, 4, "61-201", "Poland", null);
        AddressEntity addressEntity3 = new AddressEntity(id3, "Poznań", "Długa", 6, 3, "61-301", "Poland", null);
        AddressEntity addressEntity4 = new AddressEntity(id4, "Poznań", "Krótka", 5, 2, "61-401", "Poland", null);
        AddressEntity addressEntity5 = new AddressEntity(id5, "Poznań", "Zamek", 4, 1, "61-501", "Poland", null);

        addressEntities.add(addressEntity1);
        addressEntities.add(addressEntity2);
        addressEntities.add(addressEntity3);
        addressEntities.add(addressEntity4);
        addressEntities.add(addressEntity5);
        return addressEntities;
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