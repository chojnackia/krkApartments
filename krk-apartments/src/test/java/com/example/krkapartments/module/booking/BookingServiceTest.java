//package com.example.krkapartments.module.booking;
//
//import com.example.krkapartments.consumer.RestConsumerImpl;
//import com.example.krkapartments.endpoint.apartment.exception.ApartmentIsOccupiedException;
//import com.example.krkapartments.generator.ObjectGenerator;
//import com.example.krkapartments.domain.address.Address;
//import com.example.krkapartments.endpoint.address.dto.AddressDto;
//import com.example.krkapartments.domain.apartment.Apartment;
//import com.example.krkapartments.endpoint.apartment.dto.ApartmentDto;
//import com.example.krkapartments.persistence.apartment.repository.ApartmentRepository;
//import com.example.krkapartments.business.apartment.ApartmentService;
//import com.example.krkapartments.endpoint.payment.dto.ClientTransactionRequestDTO;
//import com.example.krkapartments.domain.user.User;
//import net.fortuna.ical4j.data.ParserException;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.*;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
//class BookingServiceTest {
//
//    private final ObjectGenerator generator = new ObjectGenerator();
//
//    @Mock
//    private BookingRepository bookingRepository;
//
//    @Mock
//    private BookingService bookingService;
//
//    @Mock
//    private ApartmentService apartmentService;
//
//    @Mock
//    private RestConsumerImpl restConsumer;
//
//    @Mock
//    private ApartmentRepository apartmentRepository;
//
//    @BeforeEach
//    void init() {
//        MockitoAnnotations.openMocks(this);
//        apartmentService = new ApartmentService(apartmentRepository);
//        bookingService = new BookingService(bookingRepository, apartmentService, restConsumer);
//    }
//
//    List<Apartment> apartments = generator.getApartmentList();
//    List<Address> addresses = generator.getAddressList();
//    List<Booking> bookings = generator.getBookingList();
//    List<User> users = generator.getUserList();
//
//    List<ApartmentDto> apartmentDtos = generator.getApartmentDtos();
//    List<AddressDto> addressDtos = generator.getAddressDtos();
//    List<BookingDto> bookingDtos = generator.getBookingDtoList();
//
//    @Test
//    void shouldAddBooking() throws ApartmentIsOccupiedException {
//
//        BookingDto bookingDto = generator.getBookingDtoList().get(0);
//
//
//        generator.generateDependencies(apartments, addresses, bookings, users);
//        generator.generateDtoDependencies(apartmentDtos, addressDtos, bookingDtos, users);
//
//        Optional<Apartment> expectedApartment = Optional.of(apartments.get(0));
//
//        Mockito.when(apartmentRepository.findById(apartments.get(0).getId())).thenReturn(expectedApartment);
//
//        BookingDto addedBooking = bookingService.addBooking(bookingDto);
//        bookingDto.setId(addedBooking.getId());
//
//        assertThat(addedBooking).isEqualTo(bookingDto);
//        Mockito.verify(bookingRepository, times(1)).save(Mockito.any(Booking.class));
//
//
//    }
//
//    @Test
//    void shouldAddBookingWhenDateAlreadyExistForAnotherApartment() throws ApartmentIsOccupiedException{
//
//        BookingDto bookingDto = generator.getBookingDtoList().get(1);
//
//        generator.generateDependencies(apartments, addresses, bookings, users);
//        generator.generateDtoDependencies(apartmentDtos, addressDtos, bookingDtos, users);
//
//        Optional<Apartment> expectedApartment = Optional.of(apartments.get(1));
//
//        Mockito.when(apartmentRepository.findById(apartments.get(1).getId())).thenReturn(expectedApartment);
//
//        BookingDto addedBooking = bookingService.addBooking(bookingDto);
//        bookingDto.setId(addedBooking.getId());
//        Mockito.verify(bookingRepository, times(1)).save(Mockito.any(Booking.class));
//
//
//    }
//
//    @Test
//    void shouldThrowAppartmentIsOccupiedException() {
//
//        BookingDto bookingDto = generator.getBookingDtoList().get(0);
//        Booking bookingInDatabase = bookings.get(0);
//
//        generator.generateDependencies(apartments, addresses, bookings, users);
//        generator.generateDtoDependencies(apartmentDtos, addressDtos, bookingDtos, users);
//
//        Optional<Apartment> expectedApartment = Optional.of(apartments.get(0));
//
//        Mockito.when(bookingRepository.findAllByApartmentEqualsAndCheckInDateIsBetweenOrCheckOutDateIsBetween(apartments.get(0), bookingDtos.get(0).getCheckInDate(), bookingDtos.get(0).getCheckOutDate())).thenReturn(List.of(bookingInDatabase));
//        Mockito.when(apartmentRepository.findById(apartments.get(0).getId())).thenReturn(expectedApartment);
//
//        ApartmentIsOccupiedException e = Assertions.assertThrows(ApartmentIsOccupiedException.class, () ->
//                bookingService.addBooking(bookingDto));
//
//        assertThat(e.getMessage()).isEqualTo("Apartment is occupied between " + bookingDtos.get(0).getCheckInDate() + " - " + bookingDtos.get(0).getCheckOutDate());
//
//    }
//
//    @Test
//    void shouldFindAllBookings() {
//        bookingService.findAllBookings();
//        verify(bookingRepository).findAll();
//    }
//
//    @Test
//    void shouldDeleteBooking() {
//        Booking booking = generator.getBookingList().get(0);
//        Mockito.when(bookingRepository.findById(booking.getId())).thenReturn(Optional.of(booking));
//        bookingService.deleteBooking(booking.getId());
//        Mockito.verify(bookingRepository, Mockito.times(1)).deleteById(booking.getId());
//    }
//
//    @Test
//    void shouldFindBookingInDatabase() {
//        List<Booking> bookings = generator.getBookingList();
//        UUID id = bookings.get(2).getId();
//        BookingDto expectedBookingDto = generator.getBookingDtoList().get(2);
//
//        Mockito.when(bookingRepository.findById(id)).thenReturn(Optional.ofNullable(bookings.get(2)));
//
//        BookingDto bookingById = BookingConverter.convertToBookingDto(bookingService.findBookingInDatabase(id));
//
//        assertThat(bookingById).isEqualTo(expectedBookingDto);
//    }
//
//    @Test
//    void shouldUpdateOnlyCheckInDate() {
//        UUID id = generator.getBookingList().get(1).getId();
//        Booking booking = generator.getBookingList().get(1);
//        BookingDto bookingDto = generator.getBookingDtoList().get(1);
//
//        Mockito.when(bookingRepository.findById(id)).thenReturn(Optional.ofNullable(booking));
//        Mockito.when(bookingRepository.save(Mockito.any(Booking.class))).thenReturn(booking);
//        bookingDto.setCheckInDate(LocalDate.of(2021, 10, 5));
//
//        Map<Object, Object> changes = new HashMap<>();
//        changes.put("checkInDate", LocalDate.of(2021, 10, 5));
//        BookingDto updatedBooking = bookingService.updateBooking(id, changes);
//
//        assertThat(bookingDto).isEqualTo(updatedBooking);
//    }
//
//}