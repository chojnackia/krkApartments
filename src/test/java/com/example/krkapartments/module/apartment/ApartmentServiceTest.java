package com.example.krkapartments.module.apartment;

import com.example.krkapartments.exception.ApartmentNotFoundException;
import com.example.krkapartments.exception.FieldDoesNotExistException;
import com.example.krkapartments.generator.ObjectGenerator;
import com.example.krkapartments.module.address.Address;
import com.example.krkapartments.module.booking.Booking;
import com.example.krkapartments.module.user.User;
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

    private List<ApartmentDto> convertApartmentToDtoList(List<Apartment> apartments) {
        return apartments.stream()
                .map(ApartmentConverter::convertApartmentToDto)
                .collect(Collectors.toList());
    }

    @Test
    void shouldFindApartmentByIdAndReturnApartmentDto() {
        //given
        List<Apartment> apartments = generator.getApartmentList();

        Apartment apartment = apartments.get(3);
        UUID id = apartment.getId();
        Optional<Apartment> searchedApartment = Optional.of(apartment);

        List<ApartmentDto> apartmentDto = generator.getApartmentDtos();
        ApartmentDto expectedApartmentDto = apartmentDto.get(3);

        Mockito.when(apartmentRepository.findById(id)).thenReturn(searchedApartment);

        //when
        ApartmentDto apartmentDtoById = apartmentService.findById(id);
        expectedApartmentDto.setBookings(apartmentDtoById.getBookings());

        //then
        assertThat(apartmentDtoById).isEqualTo(expectedApartmentDto);
    }

    @Test
    void shouldThrowApartmentNotFoundExceptionWhenSearchedApartmentDoesNotExist() {
        //given
        UUID id = UUID.randomUUID();
        ApartmentNotFoundException e = Assertions.assertThrows(ApartmentNotFoundException.class, ()->
                apartmentService.findById(id));

        //when
        //then
        assertThat(e.getMessage()).isEqualTo("Could not find apartment with id: " + id);
    }

    @Test
    void shouldFindAllApartmentsAndReturnApartmentsDtoList() {
        //given
        List<Apartment> apartments = generator.getApartmentList();
        List<ApartmentDto> expectedAllApartmentsDtoList = convertApartmentToDtoList(apartments);

        //when
        Mockito.when(apartmentRepository.findAll()).thenReturn(apartments);
        List<ApartmentDto> allApartmentsDtoList = apartmentService.findAll();

        //then
        assertThat(allApartmentsDtoList).isEqualTo(expectedAllApartmentsDtoList);
    }

    @Test
    void shouldAddApartmentToDatabase() {
        //given
        ApartmentDto apartmentDto = generator.getApartmentDtos().get(0);
        Apartment expectedApartment = generator.getApartmentList().get(0);

        Mockito.when(apartmentService.addApartment(apartmentDto)).thenReturn(expectedApartment);

        //when
        Apartment addedApartment = apartmentService.addApartment(apartmentDto);
        expectedApartment.setId(addedApartment.getId());
        addedApartment.setBookings(expectedApartment.getBookings());

        //then
        assertThat(addedApartment).isEqualTo(expectedApartment);
    }

    @Test
    void shouldFindApartmentInDatabase() {
        //given
        ApartmentDto apartmentDto = generator.getApartmentDtos().get(0);
        Apartment expectedApartment = generator.getApartmentList().get(0);

        Mockito.when(apartmentService.addApartment(apartmentDto)).thenReturn(expectedApartment);

        //when
        Apartment addedApartment = apartmentService.addApartment(apartmentDto);
        expectedApartment.setId(addedApartment.getId());
        expectedApartment.setBookings(addedApartment.getBookings());

        //then
        assertThat(addedApartment).isEqualTo(expectedApartment);
    }

    @Test
    void findAllActiveApartments() {
        //given
        List<Apartment> apartments = generator.getApartmentList();
        List<Address> addresses = generator.getAddressList();
        List<Booking> bookings = generator.getBookingList();
        List<User> users = generator.getUserList();
        List<UserDto> usersDto = generator.getUserDtoList();
        generator.generateDependencies(apartments, addresses, bookings, users);

        List<Apartment> activeApartments = apartments.stream()
                .filter(Apartment::isActive)
                .collect(Collectors.toList());
        List<ApartmentDto> activeApartmentsDto = convertApartmentToDtoList(activeApartments);

        //when
        Mockito.when(apartmentRepository.findAllByActive(true)).thenReturn(activeApartments);
        List<ApartmentDto> allActiveApartments = apartmentService.findAllActiveApartments();

        //then
        assertThat(allActiveApartments).isEqualTo(activeApartmentsDto);
    }

    @Test
    void shouldUpdateApartment(){
        //given
        ApartmentDto expectedApartmentDto = generator.getApartmentDtos().get(0);
        expectedApartmentDto.setApartmentName("UpdatedName");
        expectedApartmentDto.setPriceForOneDay(500.0);
        expectedApartmentDto.setApartmentDescription("UpdatedDescription");
        expectedApartmentDto.setActive(true);
        expectedApartmentDto.setBookings(null);
        expectedApartmentDto.setAddress(null);
        UUID id = expectedApartmentDto.getId();
        Optional<Apartment> apartment = Optional.of(generator.getApartmentList().get(0));

        Mockito.when(apartmentRepository.findById(id)).thenReturn(apartment);
        Map<Object,Object> changesMap = new HashMap<>();
        changesMap.put("apartmentName", "UpdatedName");
        changesMap.put("priceForOneDay", 500.0);
        changesMap.put("apartmentDescription", "UpdatedDescription");
        changesMap.put("active", true);
        changesMap.put("bookings", null);
        changesMap.put("address", null);
        //when
        ApartmentDto updatedApartmentDto = apartmentService.updateApartment(id, changesMap);
        //then
        assertThat(updatedApartmentDto).isEqualTo(expectedApartmentDto);
    }

    @Test
    void shouldThrowApartmentNotFoundExceptionIfSearchedApartmentNotExist(){
        //given
        UUID id = UUID.randomUUID();

        ApartmentNotFoundException e = Assertions.assertThrows(ApartmentNotFoundException.class, ()-> apartmentService.findById(id));
        //when
        //then
        assertThat(e.getMessage()).isEqualTo("Could not find apartment with id: " + id);
    }
    @Test
    void shouldThrowFieldNotExistExceptionWhenTryingUpdateNonExistingField(){
        //given
        ApartmentDto expectedApartmentDto = generator.getApartmentDtos().get(0);
        UUID id = expectedApartmentDto.getId();
        UUID updatedId = UUID.fromString("1715c6ec-7939-4f9b-b0ad-6c6a11111111");
        Optional<Apartment>apartment = Optional.ofNullable(generator.getApartmentList().get(0));

        Mockito.when(apartmentRepository.findById(id)).thenReturn(apartment);
        Map<Object, Object> changesMap = new HashMap<>();
        String key = "NonExistingField";
        changesMap.put(key,updatedId);

        //when
        FieldDoesNotExistException e = Assertions.assertThrows(FieldDoesNotExistException.class, ()-> apartmentService.updateApartment(id, changesMap));
        //then
        assertThat(e.getMessage()).isEqualTo("Field " + key + " does not exist");
    }

    @Test
    void shouldDeactivateApartment(){
        //given
        Apartment apartment = generator.getApartmentList().get(0);
        UUID id = apartment.getId();

        Mockito.when(apartmentRepository.findById(id)).thenReturn(Optional.of(apartment));
        Mockito.when(apartmentRepository.save(apartment)).thenReturn(apartment);

        //when
        apartmentService.deactivateApartment(id);

        //then
        assertThat(apartment.isActive()).isFalse();
    }

    @Test
    void shouldReturnApartmentWhenAddedApartmentExistingInDatabase(){
        //given
        ApartmentDto apartmentDto = generator.getApartmentDtos().get(0);
        Apartment expectedApartment = generator.getApartmentList().get(0);
        Optional<Apartment> optionalApartment = Optional.of(expectedApartment);
        //when
        Mockito.when(apartmentRepository.findByApartmentName(apartmentDto.getApartmentName())).thenReturn(optionalApartment);
        Apartment addedApartment = apartmentService.addApartment(apartmentDto);
        addedApartment.setId(expectedApartment.getId());
        //then
        assertThat(expectedApartment).isEqualTo(addedApartment);
    }

}