package com.example.krkapartments.module.apartment;

import com.example.krkapartments.generator.ObjectGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ApartmentConverterTest {

    private final ObjectGenerator generator = new ObjectGenerator();

    @Test
    void shouldConvertApartmentToDto() {

        Apartment apartment = generator.getApartmentList().get(0);
        ApartmentDto expectedApartment = generator.getApartmentDtos().get(0);

        ApartmentDto apartmentDto = ApartmentConverter.convertApartmentToDto(apartment);

        assertThat(apartmentDto).isEqualTo(expectedApartment);
    }

    @Test
    void shouldConvertDtoToApartment() {

        ApartmentDto apartmentDto = generator.getApartmentDtos().get(0);
        Apartment expectedApartment = generator.getApartmentList().get(0);

        Apartment apartment = ApartmentConverter.convertDtoToApartment(apartmentDto);

        assertThat(apartment).isEqualTo(expectedApartment);
    }

    @Test
    void shouldConvertApartmentListToDtoList() {

        List<Apartment> apartmentList = generator.getApartmentList();
        List<ApartmentDto> expectedList = generator.getApartmentDtos();

        List<ApartmentDto> apartments = ApartmentConverter.convertApartmentListToDtoList(apartmentList);

        assertThat(apartments).isEqualTo(expectedList);
    }

    @Test
    void shouldConvertDtoListToApartmentList() {

        List<ApartmentDto> apartmentList = generator.getApartmentDtos();
        List<Apartment> expectedList = generator.getApartmentList();

        List<Apartment> apartments = ApartmentConverter.convertDtoListToApartmentList(apartmentList);

        assertThat(apartments).isEqualTo(expectedList);
    }
}