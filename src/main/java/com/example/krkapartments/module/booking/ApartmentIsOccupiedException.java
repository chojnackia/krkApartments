package com.example.krkapartments.module.booking;


public class ApartmentIsOccupiedException extends Throwable {
    public ApartmentIsOccupiedException(String message) {
        super(message);
    }
}
