package com.example.krkapartments.endpoint.booking;

import com.example.krkapartments.business.booking.BookingService;
import com.example.krkapartments.domain.booking.BookingMapper;
import com.example.krkapartments.endpoint.booking.dto.BookingDto;
import com.example.krkapartments.endpoint.booking.exception.BookingNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final BookingMapper bookingMapper;

    //TODO uncomment this after refactor bookingService.addBoking method
//    @PostMapping
//    public BookingDto addBooking(@RequestBody BookingDto bookingDto) {
//        return bookingService.addBooking(bookingDto);
//    }

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