package com.practice.practiceDevBackend.mapper;

import com.practice.practiceDevBackend.dto.car.CarRequest;
import com.practice.practiceDevBackend.dto.car.CarResponse;
import com.practice.practiceDevBackend.entity.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {
    public Car toEntity(CarRequest request)
    {
        Car car = new Car();

        car.setBrand(request.getBrand());
        car.setModel(request.getModel());
        car.setBodyType(request.getBodyType());
        car.setPrice(request.getPrice());
        car.setImage(request.getImage());
        car.setYear(request.getYear());
        car.setTransmission(request.getTransmission());
        car.setFuel(request.getFuel());
        car.setSeats(request.getSeats());
        car.setStatus(request.getStatus());
        return car;
    }

    public CarResponse toResponse(Car car) {

        CarResponse response = new CarResponse();

        response.setId(car.getId());
        response.setBrand(car.getBrand());
        response.setModel(car.getModel());
        response.setBodyType(car.getBodyType());
        response.setPrice(car.getPrice());
        response.setImage(car.getImage());
        response.setYear(car.getYear());
        response.setTransmission(car.getTransmission());
        response.setFuel(car.getFuel());
        response.setSeats(car.getSeats());
        response.setStatus(car.getStatus());

        return response;
    }

    public void updateEntity(Car car, CarRequest request) {
        car.setBrand(request.getBrand());
        car.setModel(request.getModel());
        car.setBodyType(request.getBodyType());
        car.setPrice(request.getPrice());
        car.setImage(request.getImage());
        car.setYear(request.getYear());
        car.setTransmission(request.getTransmission());
        car.setFuel(request.getFuel());
        car.setSeats(request.getSeats());
        car.setStatus(request.getStatus());
    }

}
