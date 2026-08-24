package com.practice.practiceDevBackend.service;

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

    public List<Car> getAllCars() {
        return carRepository.findAll(); // — готовый метод Spring Data JPA, который выполнит запрос примерно: "SELECT * FROM cars;"
    }

    public Car createCar(Car car){
        return carRepository.save(car);
    }

    public Car getCarById(Long id){
        return carRepository.findById(id).orElseThrow(() -> new CarNotFoundException("Car not found."));
    }

    public Car deleteCarById(Long id){
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Car not found"));
        carRepository.delete(car);
        System.out.println("Deleting was successfully.");
        return car;
    }

    public Car updateCar(Long id, Car updatedCar){
        Car car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException("Car not found."));

        car.setBrand(updatedCar.getBrand());
        car.setModel(updatedCar.getModel());
        car.setBodyType(updatedCar.getBodyType());
        car.setPrice(updatedCar.getPrice());
        car.setImage(updatedCar.getImage());
        car.setYear(updatedCar.getYear());
        car.setTransmission(updatedCar.getTransmission());
        car.setFuel(updatedCar.getFuel());
        car.setSeats(updatedCar.getSeats());
        car.setStatus(updatedCar.getStatus());
        return carRepository.save(car);
    }
}
