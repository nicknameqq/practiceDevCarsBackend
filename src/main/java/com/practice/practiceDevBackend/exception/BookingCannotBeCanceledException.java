package com.practice.practiceDevBackend.exception;

public class BookingCannotBeCanceledException extends RuntimeException {
    public BookingCannotBeCanceledException(String message) {
        super(message);
    }
}
