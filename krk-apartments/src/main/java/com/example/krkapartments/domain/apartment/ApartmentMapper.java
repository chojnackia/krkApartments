package com.example.krkapartments.domain.apartment;

import com.example.krkapartments.domain.address.AddressMapper;
import com.example.krkapartments.endpoint.apartment.dto.ApartmentCreateCommand;
import com.example.krkapartments.endpoint.apartment.dto.ApartmentDto;
import com.example.krkapartments.endpoint.booking.dto.BookingCreateCommand;
import com.example.krkapartments.persistence.apartment.entity.ApartmentEntity;
import org.mapstruct.Mapper;

import java.util.Optional;
import java.util.UUID;

@Mapper(uses = {
        AddressMapper.class
})
public interface ApartmentMapper {
    Apartment mapFromEntityToDomain(ApartmentEntity apartmentEntity);

    ApartmentEntity mapFromDomainToEntity(Apartment apartment);

    ApartmentDto mapFromDomainToDto(Apartment apartment);

    Apartment mapFromDtoToDomain(ApartmentDto apartmentDto);
    Apartment mapFromCreateCommandToDomain(ApartmentCreateCommand apartmentCreateCommand);

    default Apartment mapFromIdToDomain(UUID id) {
        return Optional.ofNullable(id)
                .map(i -> Apartment.builder()
                        .id(i)
                        .build())
                .orElse(null);
    }
}
