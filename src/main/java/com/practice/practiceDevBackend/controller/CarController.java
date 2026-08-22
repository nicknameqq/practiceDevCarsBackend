package com.practice.practiceDevBackend.controller;

import com.practice.practiceDevBackend.entity.Car;
import com.practice.practiceDevBackend.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // этот класс обрабатывает HTTP-запросы и возвращает данные, например JSON.
@RequestMapping("/api/cars") // эЗадаёт общий адрес для всех методов Controller.
@RequiredArgsConstructor //
public class CarController {


    private final CarService carService;

    @GetMapping //этот метод обрабатывает GET /api/cars.
    public List<Car> getAllCars(){
        return carService.getAllCars();
    }

    @PostMapping
    public Car createCar(@RequestBody Car car){
        return carService.createCar(car);
    }

    @GetMapping("/{id}")
    public Car getCarById(@PathVariable Long id){
        return carService.getCarById(id);
    }
}
