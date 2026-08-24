package com.practice.practiceDevBackend.controller;

import com.practice.practiceDevBackend.entity.Car;
import com.practice.practiceDevBackend.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // этот класс обрабатывает HTTP-запросы и возвращает данные, например JSON.
@RequestMapping("/api/cars") // эЗадаёт общий адрес для всех методов Controller.
@RequiredArgsConstructor //
public class CarController {


    private final CarService carService;

    @GetMapping //этот метод обрабатывает GET /api/cars.
    public ResponseEntity<List<Car>> getAllCars(){
        return ResponseEntity.ok(carService.getAllCars());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id){
        return ResponseEntity.ok(carService.getCarById(id)); // те ж саме return ResponseEntity.ok().body(user);
    }


    @PostMapping
    public ResponseEntity<Car> createCar(@Valid @RequestBody Car car){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(carService.createCar(car));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCarById(@PathVariable Long id,
                                             @Valid @RequestBody Car car){
        return  ResponseEntity.ok(carService.updateCar(id, car));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Car> deleteCarById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(carService.deleteCarById(id));
    }



}
