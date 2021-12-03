package com.example.krkapartments.module.address;

public class AddressConverter {
    public static AddressDto convertToAddressDto(Address address) {
        if (address == null) return null;
        return AddressDto.builder()
                .id(address.getId())
                .city(address.getCity())
                .streetName(address.getStreetName())
                .buildingNumber(address.getBuildingNumber())
                .apartmentNumber(address.getApartmentNumber())
                .postCode(address.getPostCode())
                .country(address.getCountry())
                .build();
    }

    public static Address convertDtoToAddress(AddressDto addressDto) {
        if (addressDto == null) return null;
        return Address.builder()
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
