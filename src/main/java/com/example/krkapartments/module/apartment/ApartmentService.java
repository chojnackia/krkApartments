package com.example.krkapartments.module.apartment;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;


    public ApartmentService(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    public List<ApartmentDto> findAllApartments() {
        List<Apartment> apartments = apartmentRepository.findAll();
        return apartments.stream()
                .map(ApartmentConverter::convertApartmentToDto)
                .collect(Collectors.toList());
    }

    public ApartmentDto addApartment(ApartmentDto apartmentDto) {
        if (apartmentDto == null) return null;
        Apartment apartment = Apartment.builder()
                .id(UUID.randomUUID())
                .apartmentName(apartmentDto.getApartmentName())
                .priceForOneDay(apartmentDto.getPriceForOneDay())
                .apartmentDescription(apartmentDto.getApartmentDescription())
                .occupied(apartmentDto.isOccupied())
                .address(apartmentDto.getAddress())
                .build();
        return ApartmentConverter.convertApartmentToDto(apartment);
    }


}
