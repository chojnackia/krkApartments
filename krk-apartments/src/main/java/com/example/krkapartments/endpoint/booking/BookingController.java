package com.example.krkapartments.endpoint.booking;

import com.example.krkapartments.business.booking.BookingService;
import com.example.krkapartments.endpoint.apartment.exception.ApartmentIsOccupiedException;
import com.example.krkapartments.endpoint.booking.dto.BookingDto;
import com.example.krkapartments.persistence.booking.entity.BookingEntity;
import lombok.AllArgsConstructor;
import net.fortuna.ical4j.data.ParserException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/")
    public BookingDto addBooking(@RequestBody BookingDto bookingDto /*ClientTransactionRequestDTO clientTransactionRequestDTO*/) throws ApartmentIsOccupiedException, ParserException, IOException {
        return bookingService.addBooking(bookingDto);
    }

    @GetMapping("/")
    public List<BookingDto> findAllBookings() {
        return bookingService.findAllBookings();
    }

    @DeleteMapping("/{id}")
    public BookingEntity deleteBooking(@PathVariable UUID id) {
        return bookingService.deleteBooking(id);
    }

    @PatchMapping("/{id}")
    public BookingDto updateBooking(@PathVariable UUID id, @RequestBody Map<Object, Object> fields) {
        return bookingService.updateBooking(id, fields);
    }

    @GetMapping("/apartments/{id}")
    public List<BookingDto> findAllBookingsByApartment(@PathVariable UUID id) {
        return bookingService.findAllBookingsByApartment(id);
    }
}