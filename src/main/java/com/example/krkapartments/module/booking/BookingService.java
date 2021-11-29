package com.example.krkapartments.module.booking;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    public List<BookingDto> findAllByOccupiedIsFalse(){
        List<Booking> bookingList = bookingRepository.findAllByOccupiedIsFalse(true);
        return bookingList.stream()
                .map(BookingConverter::convertToBookingDto)
                .collect(Collectors.toList());

    }


}
