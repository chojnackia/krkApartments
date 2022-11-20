package com.example.krkapartments.module.apartment;

import org.mapstruct.Mapper;

@Mapper
public interface ApartmentMapper {
    Apartment mapFromEntityToDomain(ApartmentEntity apartmentEntity);

    ApartmentEntity mapFromDomainToEntity(Apartment apartment);

    ApartmentDto mapFromDomainToDto(Apartment apartment);

    Apartment mapFromDtoToDomain(ApartmentDto apartmentDto);
}
