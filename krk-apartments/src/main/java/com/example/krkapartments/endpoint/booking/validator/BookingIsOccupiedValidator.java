package com.example.krkapartments.endpoint.booking.validator;

import com.example.krkapartments.endpoint.booking.dto.BookingCreateCommand;
import com.example.krkapartments.persistence.booking.repository.BookingRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class BookingIsOccupiedValidator implements ConstraintValidator<BookingIsOccupied, BookingCreateCommand> {
    private final BookingRepository bookingRepository;

    @Override
    public boolean isValid(BookingCreateCommand command, ConstraintValidatorContext constraintValidatorContext) {
        return bookingRepository.findAllBookedBetween(command.getApartmentId(), command.getCheckInDate(), command.getCheckOutDate())
                .isEmpty();
    }

    @Override
    public void initialize(BookingIsOccupied constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
