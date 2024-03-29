package com.example.krkapartments.endpoint.apartment.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ApartmentIsOccupiedException extends Throwable {
    public ApartmentIsOccupiedException(String message) {
        super(message);
    }
}
