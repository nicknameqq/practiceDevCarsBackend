package com.practice.practiceDevBackend.dto.car;

import com.practice.practiceDevBackend.entity.enums.CarStatus;
import com.practice.practiceDevBackend.entity.enums.FuelType;
import com.practice.practiceDevBackend.entity.enums.Transmission;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
//Frontend → CarRequest → Service → Car
public class CarRequest {
    @NotBlank(message = "Brand cannot be empty.")
    private String brand;
    @NotBlank(message = "Model cannot be empty.")
    private String model;
    @NotBlank(message = "Body type cannot be empty.")
    private String bodyType;
    @NotNull(message = "Price cannot be null.")
    @Positive(message = "Price must be greater than 0.")
    private BigDecimal price;
    private String image;
    @NotNull(message = "Year cannot be null.")
    @Min(value = 1900, message = "Year cannot be less than 1900.")
    @Max(value = 2100, message = "Year cannot be greater than 2100.")
    private Integer year;
    @NotNull(message = "Transmission cannot be null.")
    private Transmission transmission;
    @NotNull(message = "Fuel type cannot be null.")
    private FuelType fuel;
    @NotNull(message = "Seats cannot be null.")
    @Min(value = 1, message = "Seats must be at least 1.")
    @Max(value = 10, message = "Seats cannot be greater than 10.")
    private Integer seats;
    @NotNull(message = "Status cannot be null.")
    private CarStatus status;
}
