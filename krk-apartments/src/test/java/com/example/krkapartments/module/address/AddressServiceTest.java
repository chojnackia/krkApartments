package com.example.krkapartments.module.address;

import com.example.krkapartments.exception.AddressNotFoundException;
import com.example.krkapartments.exception.FieldDoesNotExistException;
import com.example.krkapartments.generator.ObjectGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class AddressServiceTest {

    private final ObjectGenerator generator = new ObjectGenerator();

    private AddressService addressService;

    @Mock
    private AddressRepository addressRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        addressService = new AddressService(addressRepository);
    }

    private List<AddressDto> convertAddressToDtoList(List<AddressEntity> addressEntities) {
        return addressEntities.stream()
                .map(AddressConverter::convertToAddressDto)
                .collect(Collectors.toList());
    }

    @Test
    void shouldFindAddressByIdAndReturnAddressDto() {

        List<AddressEntity> addressEntities = generator.getAddressEntityList();

        AddressEntity addressEntity = addressEntities.get(3);
        UUID id = addressEntity.getId();
        Optional<AddressEntity> searchedAddress = Optional.of(addressEntity);

        List<AddressDto> addressDtos = generator.getAddressDtos();
        AddressDto expectedAddressDto = addressDtos.get(3);

        Mockito.when(addressRepository.findById(id)).thenReturn(searchedAddress);

        AddressDto addressDtoById = addressService.findById(id);

        assertThat(addressDtoById).isEqualTo(expectedAddressDto);
    }

    @Test
    void shouldThrowAddressNotFoundExceptionWhenSearchedAddressDoesNotExist() {

        UUID id = UUID.randomUUID();
        AddressNotFoundException e = Assertions.assertThrows(AddressNotFoundException.class, () ->
                addressService.findById(id));

        assertThat(e.getMessage()).isEqualTo("Could not find address with id: " + id);
    }

    @Test
    void shouldFindAllAddressAndReturnAddressDtoList() {

        List<AddressEntity> addressEntities = generator.getAddressEntityList();
        List<AddressDto> expectedAddressDtoList = convertAddressToDtoList(addressEntities);

        Mockito.when(addressRepository.findAll()).thenReturn(addressEntities);
        List<AddressDto> addressDtoList = addressService.findAll();

        assertThat(addressDtoList).isEqualTo(expectedAddressDtoList);
    }

    @Test
    void shouldAddAddressToDatabase() {

        AddressDto addressDto = generator.getAddressDtos().get(0);
        AddressEntity expectedAddressEntity = generator.getAddressEntityList().get(0);

        Mockito.when(addressService.createAddress(addressDto)).thenReturn(expectedAddressEntity);

        AddressEntity addedAddressEntity = addressService.createAddress(addressDto);
        expectedAddressEntity.setId(addedAddressEntity.getId());

        assertThat(addedAddressEntity).isEqualTo(expectedAddressEntity);

    }

    @Test
    void shouldReturnAddressWhenAddedAddressExistingInDatabase() {

        AddressDto addressDto = generator.getAddressDtos().get(0);
        AddressEntity expectedAddressEntity = generator.getAddressEntityList().get(0);
        Optional<AddressEntity> optionalAddress = Optional.of(expectedAddressEntity);

        Mockito.when(addressRepository.findByCityAndStreetNameAndBuildingNumberAndApartmentNumber(
                addressDto.getCity(),
                addressDto.getStreetName(),
                addressDto.getBuildingNumber(),
                addressDto.getApartmentNumber()
        )).thenReturn(optionalAddress);
        AddressEntity addedAddressEntity = addressService.createAddress(addressDto);
        addedAddressEntity.setId(expectedAddressEntity.getId());
        addedAddressEntity.setApartmentEntity(null);

        assertThat(expectedAddressEntity).isEqualTo(addedAddressEntity);
    }

    @Test
    void shouldUpdateAddress() {

        AddressDto expectedAddressDto = generator.getAddressDtos().get(0);
        expectedAddressDto.setCountry("UpdatedCountry");
        expectedAddressDto.setCity("UpdatedCity");
        expectedAddressDto.setPostCode("UpdatedPostCode");
        expectedAddressDto.setStreetName("UpdatedStreetName");
        expectedAddressDto.setBuildingNumber(8);
        expectedAddressDto.setApartmentNumber(8);
        UUID id = expectedAddressDto.getId();
        Optional<AddressEntity> address = Optional.of(generator.getAddressEntityList().get(0));

        Mockito.when(addressRepository.findById(id)).thenReturn(address);
        Map<Object, Object> changesMap = new HashMap<>();
        changesMap.put("country", "UpdatedCountry");
        changesMap.put("city", "UpdatedCity");
        changesMap.put("postCode", "UpdatedPostCode");
        changesMap.put("streetName", "UpdatedStreetName");
        changesMap.put("buildingNumber", 8);
        changesMap.put("apartmentNumber", 8);

        AddressDto updatedAddressDto = addressService.updateAddress(id, changesMap);

        assertThat(updatedAddressDto).isEqualTo(expectedAddressDto);
    }

    @Test
    void shouldThrowAddressNotFoundExceptionIfSearchedAddressNotExist() {

        UUID id = UUID.randomUUID();

        AddressNotFoundException e = Assertions.assertThrows(AddressNotFoundException.class, () -> addressService.findById(id));

        assertThat(e.getMessage()).isEqualTo("Could not find address with id: " + id);
    }

    @Test
    void shouldThrowFieldNotExistExceptionWhenTryingUpdateNonExistingField() {

        AddressDto expectedAddressDto = generator.getAddressDtos().get(0);
        UUID id = expectedAddressDto.getId();
        UUID updatedId = UUID.fromString("1715c6ec-7939-4f9b-b0ad-6c6a11111111");
        Optional<AddressEntity> address = Optional.ofNullable(generator.getAddressEntityList().get(0));

        Mockito.when(addressRepository.findById(id)).thenReturn(address);
        Map<Object, Object> changesMap = new HashMap<>();
        String key = "NonExistingField";
        changesMap.put(key, updatedId);

        FieldDoesNotExistException e = Assertions.assertThrows(FieldDoesNotExistException.class, () -> addressService.updateAddress(id, changesMap));

        assertThat(e.getMessage()).isEqualTo("Field " + key + " does not exist");
    }
}