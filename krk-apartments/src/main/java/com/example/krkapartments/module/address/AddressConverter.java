package com.example.krkapartments.module.address;

public class AddressConverter {
    public static AddressDto convertToAddressDto(AddressEntity addressEntity) {
        if (addressEntity == null) return null;
        return AddressDto.builder()
                .id(addressEntity.getId())
                .city(addressEntity.getCity())
                .streetName(addressEntity.getStreetName())
                .buildingNumber(addressEntity.getBuildingNumber())
                .apartmentNumber(addressEntity.getApartmentNumber())
                .postCode(addressEntity.getPostCode())
                .country(addressEntity.getCountry())
                .build();
    }

    public static AddressEntity convertDtoToAddress(AddressDto addressDto) {
        if (addressDto == null) return null;
        return AddressEntity.builder()
                .id(addressDto.getId())
                .city(addressDto.getCity())
                .streetName(addressDto.getStreetName())
                .buildingNumber(addressDto.getBuildingNumber())
                .apartmentNumber(addressDto.getApartmentNumber())
                .postCode(addressDto.getPostCode())
                .country(addressDto.getCountry())
                .build();
    }

}
