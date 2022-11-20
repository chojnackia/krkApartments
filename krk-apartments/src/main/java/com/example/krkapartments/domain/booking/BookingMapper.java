package com.example.krkapartments.domain.booking;

import com.example.krkapartments.endpoint.booking.dto.BookingDto;
import com.example.krkapartments.persistence.booking.entity.BookingEntity;
import org.mapstruct.Mapper;

@Mapper
public interface BookingMapper {
    Booking mapFromEntityToDomain(BookingEntity bookingEntity);

    BookingEntity mapFromDomainToEntity(Booking booking);

    BookingDto mapFromDomainToDto(Booking booking);

    Booking mapFromDtoToDomain(BookingDto bookingDto);
}
