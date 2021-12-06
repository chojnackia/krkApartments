package com.example.krkapartments.module.address;

import com.example.krkapartments.generator.ObjectGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AddressConverterTest {

    private final ObjectGenerator generator =new ObjectGenerator();

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldConvertToAddressDto() {
        //given
        Address address = generator.getAddressList().get(0);
        AddressDto expectedAddressDto = generator.getAddressDtos().get(0);

        //when
        AddressDto addressDto = AddressConverter.convertToAddressDto(address);

        //then
        assertThat(addressDto).isEqualTo(expectedAddressDto);
    }

    @Test
    void shouldConvertDtoIntoAddress(){
        //given
        AddressDto addressDto = generator.getAddressDtos().get(0);
        Address expectedAddress = generator.getAddressList().get(0);

        //when
        Address address = AddressConverter.convertDtoToAddress(addressDto);

        //then
        assertThat(address).isEqualTo(expectedAddress);
    }

}