package com.example.carrental.exception;

public class ReservationNotFoundException
        extends RuntimeException {

    public ReservationNotFoundException(
            String message) {

        super(message);
    }
}