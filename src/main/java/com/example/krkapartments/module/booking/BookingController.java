package com.example.krkapartments.module.booking;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
public class BookingController {

   private final BookingService bookingService;
   private final BookingRepository bookingRepository;

    @PostMapping("/")
    public BookingDto addBooking(@Valid @RequestBody BookingDto bookingDto) {
        return bookingService.addBooking(bookingDto);
    }

    @GetMapping("/")
    public List<BookingDto> findAllBookings() {
        return bookingService.findAllBookings();
    }

    @PatchMapping("/deactivate/{id}")
    public Booking deactivateBooking(@PathVariable UUID id){
        return bookingService.deactivateBooking(id);
    }

    @PatchMapping("/{id}")
    public BookingDto updateBooking(@PathVariable UUID id, @RequestBody Map<Object, Object> fields){
        return bookingService.updateBooking(id, fields);
    }
}
