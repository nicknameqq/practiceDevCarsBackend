package com.practice.practiceDevBackend.controller.Admin;

import com.practice.practiceDevBackend.dto.car.CarRequest;
import com.practice.practiceDevBackend.dto.car.CarResponse;
import com.practice.practiceDevBackend.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/cars")
@RequiredArgsConstructor
public class AdminCarController {
    private final CarService carService;

    @PostMapping
    public ResponseEntity<CarResponse> createCar(
            @Valid @RequestBody CarRequest request)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.createCar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarResponse> updateCar(
            @PathVariable Long id,
            @Valid @RequestBody CarRequest request) {

        return ResponseEntity.ok(
                carService.updateCar(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CarResponse> deleteCar(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                carService.deleteCarById(id)
        );
    }
}
