package com.example.krkapartments.generator;

import com.example.krkapartments.module.apartment.Apartment;
import com.example.krkapartments.module.apartment.ApartmentDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ApartmentGenerator {

    private static final UUID id1 = UUID.fromString("1ae88fa0-38b0-11ec-8d3d-0242ac130003");
    private static final UUID id2 = UUID.fromString("1ae88fa0-38b0-11ec-8d3d-0242ac130003");
    private static final UUID id3 = UUID.fromString("1ae88fa0-38b0-11ec-8d3d-0242ac130004");
    private static final UUID id4 = UUID.fromString("1ae88fa0-38b0-11ec-8d3d-0242ac130005");
    private static final UUID id5 = UUID.fromString("1ae88fa0-38b0-11ec-8d3d-0242ac130006");




    static List<Apartment> generateApartmentList() {
        List<Apartment> apartments = new ArrayList<>();
        Apartment apartments1 = new Apartment(id1, "The Beściak", 300, "Beściak z najlepszych", true,"apartmentURL1", new ArrayList<>(), null);
        Apartment apartments2 = new Apartment(id2, "Najdroższy", 350, "Najdroższy z najlepszych", true,"apartmentURL2", new ArrayList<>(), null);
        Apartment apartments3 = new Apartment(id3, "Taki se", 325, "Taki se z najlepszych", true,"apartmentURL3", new ArrayList<>(), null);
        Apartment apartments4 = new Apartment(id4, "Sredniak", 275, "Sredniak z najlepszych", true,"apartmentURL4", new ArrayList<>(), null);
        Apartment apartments5 = new Apartment(id5, "Najtańszy", 250, "Najtańszy z najlepszych", true,"apartmentURL5", new ArrayList<>(), null);


        apartments.add(apartments1);
        apartments.add(apartments2);
        apartments.add(apartments3);
        apartments.add(apartments4);
        apartments.add(apartments5);
        return apartments;
    }

    static List<ApartmentDto> generateApartmentDtoList() {
        List<ApartmentDto> apartments = new ArrayList<>();
        ApartmentDto apartments1 = new ApartmentDto(id1, "The Beściak", 300, "Beściak z najlepszych","apartmentURL1",true,new ArrayList<>(), null);
        ApartmentDto apartments2 = new ApartmentDto(id2, "Najdroższy", 350, "Najdroższy z najlepszych","apartmentURL1",true , new ArrayList<>(), null);
        ApartmentDto apartments3 = new ApartmentDto(id3, "Taki se", 325, "Taki se z najlepszych","apartmentURL1",true , new ArrayList<>(), null);
        ApartmentDto apartments4 = new ApartmentDto(id4, "Sredniak", 275, "Sredniak z najlepszych","apartmentURL1", true ,new ArrayList<>(), null);
        ApartmentDto apartments5 = new ApartmentDto(id5, "Najtańszy", 250, "Najtańszy z najlepszych","apartmentURL1",true , new ArrayList<>(), null);


        apartments.add(apartments1);
        apartments.add(apartments2);
        apartments.add(apartments3);
        apartments.add(apartments4);
        apartments.add(apartments5);
        return apartments;
    }
}
