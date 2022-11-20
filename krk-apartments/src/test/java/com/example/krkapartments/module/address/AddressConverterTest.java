package com.example.krkapartments.module.address;

import com.example.krkapartments.generator.ObjectGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AddressConverterTest {

    private final ObjectGenerator generator = new ObjectGenerator();

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldConvertToAddressDto() {
        //given
        AddressEntity addressEntity = generator.getAddressEntityList().get(0);
        AddressDto expectedAddressDto = generator.getAddressDtos().get(0);

        //when
        AddressDto addressDto = AddressConverter.convertToAddressDto(addressEntity);

        //then
        assertThat(addressDto).isEqualTo(expectedAddressDto);
    }

    @Test
    void shouldConvertDtoIntoAddress() {
        //given
        AddressDto addressDto = generator.getAddressDtos().get(0);
        AddressEntity expectedAddressEntity = generator.getAddressEntityList().get(0);

        //when
        AddressEntity addressEntity = AddressConverter.convertDtoToAddress(addressDto);

        //then
        assertThat(addressEntity).isEqualTo(expectedAddressEntity);
    }

}