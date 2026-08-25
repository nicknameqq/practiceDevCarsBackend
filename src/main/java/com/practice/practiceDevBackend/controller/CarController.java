package com.practice.practiceDevBackend.controller;

import com.practice.practiceDevBackend.dto.car.CarFilter;
import com.practice.practiceDevBackend.dto.car.CarRequest;
import com.practice.practiceDevBackend.dto.car.CarResponse;
import com.practice.practiceDevBackend.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController // этот класс обрабатывает HTTP-запросы и возвращает данные, например JSON.
@RequestMapping("/api/cars") // эЗадаёт общий адрес для всех методов Controller.
@RequiredArgsConstructor //
public class CarController {


    private final CarService carService;

    //У Spring Data ми замінюємо звичайний список List<Message> на Page<Message>, щоб увімкнути автоматичну пагінацію на рівні бази даних та отримати метадані про сторінки.
    @GetMapping
    public ResponseEntity<Page<CarResponse>> getAllCars(
            @Valid CarFilter filter,
            @PageableDefault(size = 12) Pageable pageable)
    {
        return ResponseEntity.ok(carService.getAllCars(filter, pageable));
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
