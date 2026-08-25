package com.practice.practiceDevBackend.mapper;

import com.practice.practiceDevBackend.dto.booking.BookingRequest;
import com.practice.practiceDevBackend.dto.booking.BookingResponse;
import com.practice.practiceDevBackend.entity.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public BookingResponse toResponse(Booking booking) {

        BookingResponse response = new BookingResponse();

        response.setId(booking.getId());
        response.setCarId(booking.getCar().getId());
        response.setStartDate(booking.getStartDate());
        response.setEndDate(booking.getEndDate());
        response.setTotalPrice(booking.getTotalPrice());
        response.setStatus(booking.getStatus());

        return response;
    }

    public void updateEntity(Booking booking, BookingRequest request) {

        booking.setStartDate(request.getStartDate());
        booking.setEndDate(request.getEndDate());
    }
}