package com.example.krkapartments.module.address;

import org.mapstruct.Mapper;

@Mapper
public interface AddressMapper {
    Address mapFromEntityToDomain(AddressEntity addressEntity);
    AddressEntity mapFromDomainToEntity(Address address);
    AddressDto mapFromDomainToDto(Address address);
    Address mapFromDtoToDomain(AddressDto addressDto);
    Address mapFromCreateCommandToDomain(AddressCreateCommand command);
}
