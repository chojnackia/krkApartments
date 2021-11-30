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

    public List<ApartmentDto> getApartmentsList() {
        return apartmentRepository.findAll()
                .stream()
                .map(ApartmentConverter::convertApartmentToDto)
                .collect(Collectors.toList());
//        List<Apartment> apartments = apartmentRepository.findAll();
//        return ApartmentConverter.convertApartmentListToDtoList(apartments);
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
