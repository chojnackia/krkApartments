package com.example.krkapartments.domain.address;

import com.example.krkapartments.endpoint.address.dto.AddressCreateCommand;
import com.example.krkapartments.endpoint.address.dto.AddressDto;
import com.example.krkapartments.persistence.address.entity.AddressEntity;
import org.mapstruct.Mapper;

@Mapper
public interface AddressMapper {
    Address mapFromEntityToDomain(AddressEntity addressEntity);

    AddressEntity mapFromDomainToEntity(Address address);

    AddressDto mapFromDomainToDto(Address address);

    Address mapFromDtoToDomain(AddressDto addressDto);

    Address mapFromCreateCommandToDomain(AddressCreateCommand command);
}
