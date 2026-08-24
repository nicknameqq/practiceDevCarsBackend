package com.practice.practiceDevBackend.dto.car;

import com.practice.practiceDevBackend.entity.enums.CarStatus;
import com.practice.practiceDevBackend.entity.enums.FuelType;
import com.practice.practiceDevBackend.entity.enums.Transmission;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
//Car → CarResponse → Frontend
public class CarResponse {

    private Long id;
    private String brand;
    private String model;
    private String bodyType;
    private BigDecimal price;
    private String image;
    private Integer year;
    private Transmission transmission;
    private FuelType fuel;
    private Integer seats;
    private CarStatus status;
}
