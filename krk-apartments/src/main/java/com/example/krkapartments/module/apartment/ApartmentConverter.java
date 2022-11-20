package com.example.krkapartments.module.apartment;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApartmentConverter {

    public static ApartmentDto convertApartmentToDto(ApartmentEntity apartmentEntity) {
        if (apartmentEntity == null) return null;
        return ApartmentDto.builder()
                .id(apartmentEntity.getId())
                .apartmentName(apartmentEntity.getApartmentName())
                .priceForOneDay(apartmentEntity.getPriceForOneDay())
                .apartmentDescription(apartmentEntity.getApartmentDescription())
                .active(apartmentEntity.isActive())
                .bookingUrl(apartmentEntity.getBookingUrl())
                .bookingEntities(apartmentEntity.getBookings())
                .addressEntity(apartmentEntity.getAddress())
                .build();
    }


    public static ApartmentEntity convertDtoToApartment(ApartmentDto apartmentDto) {
        if (apartmentDto == null) return null;
        return ApartmentEntity.builder()
                .id(apartmentDto.getId())
                .apartmentName(apartmentDto.getApartmentName())
                .priceForOneDay(apartmentDto.getPriceForOneDay())
                .apartmentDescription(apartmentDto.getApartmentDescription())
                .active(apartmentDto.isActive())
                .bookingUrl(apartmentDto.getBookingUrl())
                .bookingEntities(apartmentDto.getBookingEntities())
                .addressEntity(apartmentDto.getAddressEntity())
                .build();
    }

    public static List<ApartmentDto> convertApartmentListToDtoList(List<ApartmentEntity> apartmentEntities) {
        return apartmentEntities.stream()
                .map(ApartmentConverter::convertApartmentToDto)
                .collect(Collectors.toList());
    }

    public static List<ApartmentEntity> convertDtoListToApartmentList(List<ApartmentDto> apartmentDtos) {
        return apartmentDtos.stream()
                .map(ApartmentConverter::convertDtoToApartment)
                .collect(Collectors.toList());
    }

}