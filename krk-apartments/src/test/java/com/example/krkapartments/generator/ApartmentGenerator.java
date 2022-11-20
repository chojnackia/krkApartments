package com.example.krkapartments.generator;

import com.example.krkapartments.module.apartment.ApartmentEntity;
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


    static List<ApartmentEntity> generateApartmentList() {
        List<ApartmentEntity> apartmentEntities = new ArrayList<>();
        ApartmentEntity apartments1 = new ApartmentEntity(id1, "The Beściak", 300, "Beściak z najlepszych", true, "apartmentURL1", new ArrayList<>(), null);
        ApartmentEntity apartments2 = new ApartmentEntity(id2, "Najdroższy", 350, "Najdroższy z najlepszych", true, "apartmentURL2", new ArrayList<>(), null);
        ApartmentEntity apartments3 = new ApartmentEntity(id3, "Taki se", 325, "Taki se z najlepszych", true, "apartmentURL3", new ArrayList<>(), null);
        ApartmentEntity apartments4 = new ApartmentEntity(id4, "Sredniak", 275, "Sredniak z najlepszych", true, "apartmentURL4", new ArrayList<>(), null);
        ApartmentEntity apartments5 = new ApartmentEntity(id5, "Najtańszy", 250, "Najtańszy z najlepszych", true, "apartmentURL5", new ArrayList<>(), null);


        apartmentEntities.add(apartments1);
        apartmentEntities.add(apartments2);
        apartmentEntities.add(apartments3);
        apartmentEntities.add(apartments4);
        apartmentEntities.add(apartments5);
        return apartmentEntities;
    }

    static List<ApartmentDto> generateApartmentDtoList() {
        List<ApartmentDto> apartments = new ArrayList<>();
        ApartmentDto apartments1 = new ApartmentDto(id1, "The Beściak", 300, "Beściak z najlepszych", "apartmentURL1", true, new ArrayList<>(), null);
        ApartmentDto apartments2 = new ApartmentDto(id2, "Najdroższy", 350, "Najdroższy z najlepszych", "apartmentURL2", true, new ArrayList<>(), null);
        ApartmentDto apartments3 = new ApartmentDto(id3, "Taki se", 325, "Taki se z najlepszych", "apartmentURL3", true, new ArrayList<>(), null);
        ApartmentDto apartments4 = new ApartmentDto(id4, "Sredniak", 275, "Sredniak z najlepszych", "apartmentURL4", true, new ArrayList<>(), null);
        ApartmentDto apartments5 = new ApartmentDto(id5, "Najtańszy", 250, "Najtańszy z najlepszych", "apartmentURL5", true, new ArrayList<>(), null);


        apartments.add(apartments1);
        apartments.add(apartments2);
        apartments.add(apartments3);
        apartments.add(apartments4);
        apartments.add(apartments5);
        return apartments;
    }
}
