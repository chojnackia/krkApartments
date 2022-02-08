package com.example.krkapartments.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AdminAlreadyExistException extends RuntimeException {
    public AdminAlreadyExistException(String message) {
        super(message);
    }
}
