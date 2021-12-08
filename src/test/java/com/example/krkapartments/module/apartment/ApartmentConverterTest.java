package com.example.krkapartments.module.apartment;

import com.example.krkapartments.generator.ObjectGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ApartmentConverterTest {

    private final ObjectGenerator generator = new ObjectGenerator();

    @Test
    void shouldConvertApartmentToDto() {
        //given
        Apartment apartment = generator.getApartmentList().get(0);
        ApartmentDto expectedApartment = generator.getApartmentDtos().get(0);

        ApartmentDto apartmentDto = ApartmentConverter.convertApartmentToDto(apartment);

        //then
        assertThat(apartmentDto).isEqualTo(expectedApartment);
    }

    @Test
    void shouldConvertDtoToApartment() {
        //given
        ApartmentDto apartmentDto = generator.getApartmentDtos().get(0);
        Apartment expectedApartment = generator.getApartmentList().get(0);

        //when
        Apartment apartment = ApartmentConverter.convertDtoToApartment(apartmentDto);

        //then
        assertThat(apartment).isEqualTo(expectedApartment);
    }

    @Test
    void shouldConvertApartmentListToDtoList() {
        //given
        List<Apartment> apartmentList = generator.getApartmentList();
        List<ApartmentDto> expectedList = generator.getApartmentDtos();

        //when
        List<ApartmentDto> apartments = ApartmentConverter.convertApartmentListToDtoList(apartmentList);

        //then
        assertThat(apartments).isEqualTo(expectedList);
    }

    @Test
    void shouldConvertDtoListToApartmentList() {
        //given
        List<ApartmentDto> apartmentList = generator.getApartmentDtos();
        List<Apartment> expectedList = generator.getApartmentList();

        //when
        List<Apartment> apartments = ApartmentConverter.convertDtoListToApartmentList(apartmentList);

        //then
        assertThat(apartments).isEqualTo(expectedList);
    }
}