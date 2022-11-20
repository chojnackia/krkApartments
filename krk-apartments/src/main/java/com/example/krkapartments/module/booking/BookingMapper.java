package com.example.krkapartments.module.booking;

import org.mapstruct.Mapper;

@Mapper
public interface BookingMapper {
    Booking mapFromEntityToDomain(BookingEntity bookingEntity);

    BookingEntity mapFromDomainToEntity(Booking booking);

    BookingDto mapFromDomainToDto(Booking booking);

    Booking mapFromDtoToDomain(BookingDto bookingDto);
}
