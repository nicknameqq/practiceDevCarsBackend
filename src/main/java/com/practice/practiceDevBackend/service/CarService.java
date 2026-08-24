package com.practice.practiceDevBackend.service;

import com.practice.practiceDevBackend.dto.car.CarRequest;
import com.practice.practiceDevBackend.dto.car.CarResponse;
import com.practice.practiceDevBackend.entity.Car;
import com.practice.practiceDevBackend.exception.CarNotFoundException;
import com.practice.practiceDevBackend.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //обозначает для Spring что это сервисный Spring-компонент (Bean), в котором обычно находится бизнес-логика.
@RequiredArgsConstructor // автоматически генерирует в классе конструктор для всех полей, отмеченных как final, а также для полей с аннотацией @NonNull.
public class CarService {

    private final CarRepository carRepository;

    public List<CarResponse> getAllCars() {
        return carRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList(); // — готовый метод Spring Data JPA, который выполнит запрос примерно: "SELECT * FROM cars;"
    }

    public CarResponse createCar(CarRequest request){
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
        return toResponse(carRepository.save(car));
    }

    public CarResponse getCarById(Long id){
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Car not found."));
        return toResponse(car);
    }

    public CarResponse deleteCarById(Long id){
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Car not found."));
        carRepository.delete(car);
        System.out.println("Deleting was successfully.");
        return toResponse(car);
    }

    public CarResponse updateCar(Long id, CarRequest request){
        Car car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException("Car not found."));

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
        Car updatedCar = carRepository.save(car);
        return toResponse(updatedCar);
    }

    private CarResponse toResponse(Car car) {

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
}
