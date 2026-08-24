package com.practice.practiceDevBackend.service;

import com.practice.practiceDevBackend.dto.car.CarRequest;
import com.practice.practiceDevBackend.dto.car.CarResponse;
import com.practice.practiceDevBackend.entity.Car;
import com.practice.practiceDevBackend.exception.CarNotFoundException;
import com.practice.practiceDevBackend.mapper.CarMapper;
import com.practice.practiceDevBackend.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //обозначает для Spring что это сервисный Spring-компонент (Bean), в котором обычно находится бизнес-логика.
@RequiredArgsConstructor // автоматически генерирует в классе конструктор для всех полей, отмеченных как final, а также для полей с аннотацией @NonNull.
public class CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public List<CarResponse> getAllCars() {
        return carRepository.findAll()
                .stream()
                .map(carMapper::toResponse)
                .toList(); // — готовый метод Spring Data JPA, который выполнит запрос примерно: "SELECT * FROM cars;"
    }

    public CarResponse createCar(CarRequest request) {
        Car car = carMapper.toEntity(request);
        Car savedCar = carRepository.save(car);
        return carMapper.toResponse(savedCar);
    }

    public CarResponse getCarById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Car not found."));
        return carMapper.toResponse(car);
    }

    public CarResponse deleteCarById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Car not found."));
        carRepository.delete(car);
        System.out.println("Deleting was successfully.");
        return carMapper.toResponse(car);
    }

    public CarResponse updateCar(Long id, CarRequest request) {
        Car car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException("Car not found."));

        carMapper.updateEntity(car, request);

        Car updatedCar = carRepository.save(car);
        return carMapper.toResponse(updatedCar);
    }
}
