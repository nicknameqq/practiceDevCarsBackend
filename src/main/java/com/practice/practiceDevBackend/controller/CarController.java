package com.practice.practiceDevBackend.controller;

import com.practice.practiceDevBackend.dto.car.CarRequest;
import com.practice.practiceDevBackend.dto.car.CarResponse;
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
    public ResponseEntity<List<CarResponse>> getAllCars(){
        return ResponseEntity.ok(carService.getAllCars());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> getCarById(@PathVariable Long id){
        return ResponseEntity.ok(carService.getCarById(id)); // те ж саме return ResponseEntity.ok().body(user);
    }


    @PostMapping
    public ResponseEntity<CarResponse> createCar(@Valid @RequestBody CarRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(carService.createCar(request));
    }


    @PutMapping("/{id}")
    public ResponseEntity<CarResponse> updateCarById(@PathVariable Long id,
                                             @Valid @RequestBody CarRequest request){
        return  ResponseEntity.ok(carService.updateCar(id, request));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<CarResponse> deleteCarById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(carService.deleteCarById(id));
    }



}
