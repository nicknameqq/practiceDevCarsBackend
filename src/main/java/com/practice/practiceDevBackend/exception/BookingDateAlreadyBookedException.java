package com.practice.practiceDevBackend.exception;

public class BookingDateAlreadyBookedException extends RuntimeException{
    public BookingDateAlreadyBookedException(String message) {
        super(message);
    }
}
