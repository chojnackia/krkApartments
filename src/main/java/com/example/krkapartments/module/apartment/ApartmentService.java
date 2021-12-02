package com.example.krkapartments.module.apartment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;

    //TODO add specified exception
    public Apartment findApartmentInDatabase(UUID id){
        return apartmentRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Could not find apartment with id: " + id));
    }

    public List<ApartmentDto> getApartmentsList() {
        return apartmentRepository.findAll()
                .stream()
                .map(ApartmentConverter::convertApartmentToDto)
                .collect(Collectors.toList());
    }



}
