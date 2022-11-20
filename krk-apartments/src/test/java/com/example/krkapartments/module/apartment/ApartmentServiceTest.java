package com.example.krkapartments.module.apartment;

import com.example.krkapartments.exception.ApartmentNotFoundException;
import com.example.krkapartments.exception.FieldDoesNotExistException;
import com.example.krkapartments.generator.ObjectGenerator;
import com.example.krkapartments.module.address.AddressEntity;
import com.example.krkapartments.module.booking.BookingEntity;
import com.example.krkapartments.module.user.UserEntity;
import com.example.krkapartments.module.user.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class ApartmentServiceTest {

    private final ObjectGenerator generator = new ObjectGenerator();

    private ApartmentService apartmentService;

    @Mock
    private ApartmentRepository apartmentRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        apartmentService = new ApartmentService(apartmentRepository);
    }

    private List<ApartmentDto> convertApartmentToDtoList(List<ApartmentEntity> apartmentEntities) {
        return apartmentEntities.stream()
                .map(ApartmentConverter::convertApartmentToDto)
                .collect(Collectors.toList());
    }

    @Test
    void shouldFindApartmentByIdAndReturnApartmentDto() {
        //given
        List<ApartmentEntity> apartmentEntities = generator.getApartmentEntityList();

        ApartmentEntity apartmentEntity = apartmentEntities.get(3);
        UUID id = apartmentEntity.getId();
        Optional<ApartmentEntity> searchedApartment = Optional.of(apartmentEntity);

        List<ApartmentDto> apartmentDto = generator.getApartmentDtos();
        ApartmentDto expectedApartmentDto = apartmentDto.get(3);

        Mockito.when(apartmentRepository.findById(id)).thenReturn(searchedApartment);

        //when
        ApartmentDto apartmentDtoById = apartmentService.findById(id);
        expectedApartmentDto.setBookingEntities(apartmentDtoById.getBookingEntities());

        //then
        assertThat(apartmentDtoById).isEqualTo(expectedApartmentDto);
    }

    @Test
    void shouldThrowApartmentNotFoundExceptionWhenSearchedApartmentDoesNotExist() {
        //given
        UUID id = UUID.randomUUID();
        ApartmentNotFoundException e = Assertions.assertThrows(ApartmentNotFoundException.class, () ->
                apartmentService.findById(id));

        assertThat(e.getMessage()).isEqualTo("Could not find apartment with id: " + id);
    }

    @Test
    void shouldFindAllApartmentsAndReturnApartmentsDtoList() {

        List<ApartmentEntity> apartmentEntities = generator.getApartmentEntityList();
        List<ApartmentDto> expectedAllApartmentsDtoList = convertApartmentToDtoList(apartmentEntities);

        Mockito.when(apartmentRepository.findAll()).thenReturn(apartmentEntities);
        List<ApartmentDto> allApartmentsDtoList = apartmentService.findAll();

        assertThat(allApartmentsDtoList).isEqualTo(expectedAllApartmentsDtoList);
    }

    @Test
    void shouldAddApartmentToDatabase() {

        ApartmentDto apartmentDto = generator.getApartmentDtos().get(0);
        ApartmentEntity expectedApartmentEntity = generator.getApartmentEntityList().get(0);

        Mockito.when(apartmentService.addApartment(apartmentDto)).thenReturn(expectedApartmentEntity);

        ApartmentEntity addedApartmentEntity = apartmentService.addApartment(apartmentDto);
        expectedApartmentEntity.setId(addedApartmentEntity.getId());
        addedApartmentEntity.setBookings(expectedApartmentEntity.getBookings());

        assertThat(addedApartmentEntity).isEqualTo(expectedApartmentEntity);
    }

    @Test
    void shouldFindApartmentInDatabase() {

        ApartmentDto apartmentDto = generator.getApartmentDtos().get(0);
        ApartmentEntity expectedApartmentEntity = generator.getApartmentEntityList().get(0);

        Mockito.when(apartmentService.addApartment(apartmentDto)).thenReturn(expectedApartmentEntity);

        ApartmentEntity addedApartmentEntity = apartmentService.addApartment(apartmentDto);
        expectedApartmentEntity.setId(addedApartmentEntity.getId());
        expectedApartmentEntity.setBookings(addedApartmentEntity.getBookings());

        assertThat(addedApartmentEntity).isEqualTo(expectedApartmentEntity);
    }

    @Test
    void findAllActiveApartments() {

        List<ApartmentEntity> apartmentEntities = generator.getApartmentEntityList();
        List<AddressEntity> addressEntities = generator.getAddressEntityList();
        List<BookingEntity> bookingEntities = generator.getBookingEntityList();
        List<UserEntity> userEntities = generator.getUserEntityList();
        List<UserDto> usersDto = generator.getUserDtoList();
        generator.generateDependencies(apartmentEntities, addressEntities, bookingEntities, userEntities);

        List<ApartmentEntity> activeApartmentEntities = apartmentEntities.stream()
                .filter(ApartmentEntity::isActive)
                .collect(Collectors.toList());
        List<ApartmentDto> activeApartmentsDto = convertApartmentToDtoList(activeApartmentEntities);

        Mockito.when(apartmentRepository.findAllByActive(true)).thenReturn(activeApartmentEntities);
        List<ApartmentDto> allActiveApartments = apartmentService.findAllActiveApartments();

        assertThat(allActiveApartments).isEqualTo(activeApartmentsDto);
    }

    @Test
    void shouldUpdateApartment() {

        ApartmentDto expectedApartmentDto = generator.getApartmentDtos().get(0);
        expectedApartmentDto.setApartmentName("UpdatedName");
        expectedApartmentDto.setPriceForOneDay(500);
        expectedApartmentDto.setApartmentDescription("UpdatedDescription");
        expectedApartmentDto.setActive(true);
        expectedApartmentDto.setBookingEntities(null);
        expectedApartmentDto.setAddressEntity(null);
        UUID id = expectedApartmentDto.getId();
        Optional<ApartmentEntity> apartment = Optional.of(generator.getApartmentEntityList().get(0));

        Mockito.when(apartmentRepository.findById(id)).thenReturn(apartment);
        Map<Object, Object> changesMap = new HashMap<>();
        changesMap.put("apartmentName", "UpdatedName");
        changesMap.put("priceForOneDay", 500);
        changesMap.put("apartmentDescription", "UpdatedDescription");
        changesMap.put("active", true);
        changesMap.put("bookings", null);
        changesMap.put("address", null);

        ApartmentDto updatedApartmentDto = apartmentService.updateApartment(id, changesMap);

        assertThat(updatedApartmentDto).isEqualTo(expectedApartmentDto);
    }

    @Test
    void shouldThrowApartmentNotFoundExceptionIfSearchedApartmentNotExist() {

        UUID id = UUID.randomUUID();

        ApartmentNotFoundException e = Assertions.assertThrows(ApartmentNotFoundException.class, () -> apartmentService.findById(id));

        assertThat(e.getMessage()).isEqualTo("Could not find apartment with id: " + id);
    }

    @Test
    void shouldThrowFieldNotExistExceptionWhenTryingUpdateNonExistingField() {

        ApartmentDto expectedApartmentDto = generator.getApartmentDtos().get(0);
        UUID id = expectedApartmentDto.getId();
        UUID updatedId = UUID.fromString("1715c6ec-7939-4f9b-b0ad-6c6a11111111");
        Optional<ApartmentEntity> apartment = Optional.ofNullable(generator.getApartmentEntityList().get(0));

        Mockito.when(apartmentRepository.findById(id)).thenReturn(apartment);
        Map<Object, Object> changesMap = new HashMap<>();
        String key = "NonExistingField";
        changesMap.put(key, updatedId);

        FieldDoesNotExistException e = Assertions.assertThrows(FieldDoesNotExistException.class, () -> apartmentService.updateApartment(id, changesMap));

        assertThat(e.getMessage()).isEqualTo("Field " + key + " does not exist");
    }

    @Test
    void shouldDeactivateApartment() {

        ApartmentEntity apartmentEntity = generator.getApartmentEntityList().get(0);
        UUID id = apartmentEntity.getId();

        Mockito.when(apartmentRepository.findById(id)).thenReturn(Optional.of(apartmentEntity));
        Mockito.when(apartmentRepository.save(apartmentEntity)).thenReturn(apartmentEntity);

        apartmentService.deactivateApartment(id);

        assertThat(apartmentEntity.isActive()).isFalse();
    }

    @Test
    void shouldReturnApartmentWhenAddedApartmentExistingInDatabase() {

        ApartmentDto apartmentDto = generator.getApartmentDtos().get(0);
        ApartmentEntity expectedApartmentEntity = generator.getApartmentEntityList().get(0);
        Optional<ApartmentEntity> optionalApartment = Optional.of(expectedApartmentEntity);

        Mockito.when(apartmentRepository.findByApartmentName(apartmentDto.getApartmentName())).thenReturn(optionalApartment);
        ApartmentEntity addedApartmentEntity = apartmentService.addApartment(apartmentDto);
        addedApartmentEntity.setId(expectedApartmentEntity.getId());

        assertThat(expectedApartmentEntity).isEqualTo(addedApartmentEntity);
    }

}