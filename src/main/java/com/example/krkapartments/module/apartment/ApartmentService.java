package com.example.krkapartments.module.apartment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;

    //TODO add specified exception
    public Apartment findApartmentInDatabase(UUID id){
        return apartmentRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Could not find apartment with id: " + id));
    }



}
