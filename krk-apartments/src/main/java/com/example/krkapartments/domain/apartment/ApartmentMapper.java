package com.example.krkapartments.domain.apartment;

import com.example.krkapartments.endpoint.apartment.dto.ApartmentCreateCommand;
import com.example.krkapartments.endpoint.apartment.dto.ApartmentDto;
import com.example.krkapartments.persistence.apartment.entity.ApartmentEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ApartmentMapper {
    Apartment mapFromEntityToDomain(ApartmentEntity apartmentEntity);

    ApartmentEntity mapFromDomainToEntity(Apartment apartment);

    ApartmentDto mapFromDomainToDto(Apartment apartment);

    Apartment mapFromDtoToDomain(ApartmentDto apartmentDto);
    Apartment mapFromCreateCommandToDomain(ApartmentCreateCommand apartmentCreateCommand);
}
