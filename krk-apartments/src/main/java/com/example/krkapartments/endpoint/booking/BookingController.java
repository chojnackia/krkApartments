package com.example.krkapartments.endpoint.booking;

import com.example.krkapartments.business.apartment.ApartmentService;
import com.example.krkapartments.business.booking.BookingService;
import com.example.krkapartments.business.user.UserService;
import com.example.krkapartments.domain.apartment.Apartment;
import com.example.krkapartments.domain.booking.BookingMapper;
import com.example.krkapartments.domain.user.User;
import com.example.krkapartments.endpoint.apartment.exception.ApartmentNotFoundException;
import com.example.krkapartments.endpoint.booking.dto.BookingCreateCommand;
import com.example.krkapartments.endpoint.booking.dto.BookingDto;
import com.example.krkapartments.endpoint.booking.exception.BookingCreateException;
import com.example.krkapartments.endpoint.booking.exception.BookingNotFoundException;
import com.example.krkapartments.endpoint.user.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final BookingMapper bookingMapper;
    private final UserService userService;
    private final ApartmentService apartmentService;

    @PostMapping
    public BookingDto addBooking(@Valid @RequestBody BookingCreateCommand command) {
        Apartment apartment = apartmentService.findById(command.getApartmentId())
                .orElseThrow(ApartmentNotFoundException::new);
        User user = userService.findById(command.getUserId())
                .orElseThrow(UserNotFoundException::new);
        return Optional.of(command)
                .map(bookingMapper::mapFromCreateCommandToDomain)
                .map(booking -> booking.toBuilder()
                        .apartment(apartment)
                        .user(user)
                        .build())
                .flatMap(bookingService::addBooking)
                .map(bookingMapper::mapFromDomainToDto)
                .orElseThrow(BookingCreateException::new);
    }

    @GetMapping
    public List<BookingDto> findAllBookings() {
        return bookingService.findAllBookings()
                .stream()
                .map(bookingMapper::mapFromDomainToDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable UUID id) {
        bookingService.deleteBooking(id);
    }

    @PatchMapping("/{id}")
    public BookingDto updateBooking(@PathVariable UUID id, @RequestBody Map<Object, Object> fields) {
        return bookingService.updateBooking(id, fields)
                .map(bookingMapper::mapFromDomainToDto)
                .orElseThrow(BookingNotFoundException::new);
    }

    @GetMapping("/apartments/{id}")
    public List<BookingDto> findAllBookingsByApartment(@PathVariable UUID id) {
        return bookingService.findAllBookingsByApartment(id)
                .stream()
                .map(bookingMapper::mapFromDomainToDto)
                .collect(Collectors.toList());
    }
}