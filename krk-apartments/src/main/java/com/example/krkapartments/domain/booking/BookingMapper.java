package com.example.krkapartments.domain.booking;

import com.example.krkapartments.domain.apartment.ApartmentMapper;
import com.example.krkapartments.domain.user.UserMapper;
import com.example.krkapartments.endpoint.booking.dto.BookingCreateCommand;
import com.example.krkapartments.endpoint.booking.dto.BookingDto;
import com.example.krkapartments.persistence.booking.entity.BookingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {
        UserMapper.class,
        ApartmentMapper.class
})
public interface BookingMapper {
    Booking mapFromEntityToDomain(BookingEntity bookingEntity);

    BookingEntity mapFromDomainToEntity(Booking booking);

    BookingDto mapFromDomainToDto(Booking booking);

    Booking mapFromDtoToDomain(BookingDto bookingDto);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "apartment.id", source = "apartmentId")
    Booking mapFromCreateCommandToDomain(BookingCreateCommand command);
}
