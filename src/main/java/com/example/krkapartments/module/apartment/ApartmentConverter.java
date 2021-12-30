package com.example.krkapartments.module.apartment;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApartmentConverter {

    public static ApartmentDto convertApartmentToDto(Apartment apartment) {
        if (apartment == null) return null;
        return ApartmentDto.builder()
                .id(apartment.getId())
                .apartmentName(apartment.getApartmentName())
                .priceForOneDay(apartment.getPriceForOneDay())
                .apartmentDescription(apartment.getApartmentDescription())
                .active(apartment.isActive())
                .bookingUrl(apartment.getBookingUrl())
                .bookings(apartment.getBookings())
                .address(apartment.getAddress())
                .build();
    }


    public static Apartment convertDtoToApartment(ApartmentDto apartmentDto) {
        if (apartmentDto == null) return null;
        return Apartment.builder()
                .id(apartmentDto.getId())
                .apartmentName(apartmentDto.getApartmentName())
                .priceForOneDay(apartmentDto.getPriceForOneDay())
                .apartmentDescription(apartmentDto.getApartmentDescription())
                .active(apartmentDto.isActive())
                .bookingUrl(apartmentDto.getBookingUrl())
                .bookings(apartmentDto.getBookings())
                .address(apartmentDto.getAddress())
                .build();
    }

    public static List<ApartmentDto> convertApartmentListToDtoList(List<Apartment> apartments) {
        return apartments.stream()
                .map(ApartmentConverter::convertApartmentToDto)
                .collect(Collectors.toList());
    }

    public static List<Apartment> convertDtoListToApartmentList(List<ApartmentDto> apartmentDtos) {
        return apartmentDtos.stream()
                .map(ApartmentConverter::convertDtoToApartment)
                .collect(Collectors.toList());
    }

}