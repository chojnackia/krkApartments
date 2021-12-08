package com.example.krkapartments.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class FieldDoesNotExistException extends RuntimeException {
    public FieldDoesNotExistException(String message) {
        super(message);
    }
}
