package com.practice.practiceDevBackend.exception;

public class InvalidBookingDateException extends RuntimeException{
    public InvalidBookingDateException(String message){
        super(message);
    }
}
