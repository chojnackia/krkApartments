package com.example.krkapartments.endpoint.booking.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = BookingIsOccupiedValidator.class)
@Target({TYPE})
@Retention(RUNTIME)
public @interface BookingIsOccupied {
    String message() default
            "Booking is occupied between passed dates";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
