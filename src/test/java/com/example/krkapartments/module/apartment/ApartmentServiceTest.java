package com.example.krkapartments.module.apartment;

import com.example.krkapartments.generator.ObjectGenerator;
import com.example.krkapartments.module.address.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.stream.Collectors;

class ApartmentServiceTest {

    private final ObjectGenerator generator = new ObjectGenerator();

    private ApartmentService apartmentService;

    @Mock
    private ApartmentRepository apartmentRepository;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
        apartmentService = new ApartmentService(apartmentRepository);
    }

    private List<ApartmentDto> convertApartmentToDtoList(List<Apartment> apartments){
        return apartments.stream()
                .map(ApartmentConverter::convertApartmentToDto)
                .collect(Collectors.toList());
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
        org.assertj.core.api.Assertions.assertThat(allApartmentsDtoList).isEqualTo(expectedAllApartmentsDtoList);
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

        //then
        org.assertj.core.api.Assertions.assertThat(addedApartment).isEqualTo(expectedApartment);
    }

    @Test
    void findApartmentInDatabase() {
    }

    @Test
    void findAllActiveApartments() {
        //given
        List<Apartment> apartments = generator.getApartmentList();
        List<Address> addresses = generator.getAddressList();
        generator.generateDependencies(apartments,addresses);

        List<Apartment> activeApartments = apartments.stream()
                .filter(Apartment::isActive)
                .collect(Collectors.toList());
        List<ApartmentDto> activeApartmentsDto = convertApartmentToDtoList(activeApartments);

        //when
//        Mockito.when(apartmentRepository.findAllByActive(true)).thenReturn(activeApartments);
        List<ApartmentDto> allActiveApartments = apartmentService.findAllActiveApartments();

        //then
        org.assertj.core.api.Assertions.assertThat(allActiveApartments).isEqualTo(activeApartmentsDto);
    }

    @Test
    void updateApartment() {
    }

    @Test
    void findById() {
    }

    @Test
    void deactivateApartment() {
    }
}