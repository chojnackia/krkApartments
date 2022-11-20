package com.example.krkapartments.endpoint.apartment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApartmentNotFoundException extends RuntimeException {
    public ApartmentNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
