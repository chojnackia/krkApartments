package com.example.krkapartments.module.apartment;

import com.example.krkapartments.generator.ObjectGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ApartmentConverterTest {

    private final ObjectGenerator generator = new ObjectGenerator();

    @Test
    void shouldConvertApartmentToDto() {

        ApartmentEntity apartmentEntity = generator.getApartmentEntityList().get(0);
        ApartmentDto expectedApartment = generator.getApartmentDtos().get(0);

        ApartmentDto apartmentDto = ApartmentConverter.convertApartmentToDto(apartmentEntity);

        assertThat(apartmentDto).isEqualTo(expectedApartment);
    }

    @Test
    void shouldConvertDtoToApartment() {

        ApartmentDto apartmentDto = generator.getApartmentDtos().get(0);
        ApartmentEntity expectedApartmentEntity = generator.getApartmentEntityList().get(0);

        ApartmentEntity apartmentEntity = ApartmentConverter.convertDtoToApartment(apartmentDto);

        assertThat(apartmentEntity).isEqualTo(expectedApartmentEntity);
    }

    @Test
    void shouldConvertApartmentListToDtoList() {

        List<ApartmentEntity> apartmentEntityList = generator.getApartmentEntityList();
        List<ApartmentDto> expectedList = generator.getApartmentDtos();

        List<ApartmentDto> apartments = ApartmentConverter.convertApartmentListToDtoList(apartmentEntityList);

        assertThat(apartments).isEqualTo(expectedList);
    }

    @Test
    void shouldConvertDtoListToApartmentList() {

        List<ApartmentDto> apartmentList = generator.getApartmentDtos();
        List<ApartmentEntity> expectedList = generator.getApartmentEntityList();

        List<ApartmentEntity> apartmentEntities = ApartmentConverter.convertDtoListToApartmentList(apartmentList);

        assertThat(apartmentEntities).isEqualTo(expectedList);
    }
}