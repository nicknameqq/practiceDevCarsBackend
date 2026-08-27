package com.practice.practiceDevBackend.mapper;

import com.practice.practiceDevBackend.dto.booking.BookingCarResponse;
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

        BookingCarResponse carResponse = new BookingCarResponse();

        carResponse.setId(booking.getCar().getId());
        carResponse.setBrand(booking.getCar().getBrand());
        carResponse.setModel(booking.getCar().getModel());
        carResponse.setYear(booking.getCar().getYear());
        carResponse.setImage(booking.getCar().getImage());

        response.setCar(carResponse);

        return response;
    }

    public void updateEntity(Booking booking, BookingRequest request) {

        booking.setStartDate(request.getStartDate());
        booking.setEndDate(request.getEndDate());
    }
}