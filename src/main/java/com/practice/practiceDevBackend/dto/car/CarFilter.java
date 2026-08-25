package com.practice.practiceDevBackend.dto.car;

import com.practice.practiceDevBackend.entity.enums.Transmission;
import com.practice.practiceDevBackend.validation.PriceRange;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@PriceRange
public class CarFilter {
    private String brand;
    private String bodyType;
    private Transmission transmission;
    @PositiveOrZero(message = "Minimum price cannot be negative.")
    private BigDecimal minPrice;
    @PositiveOrZero(message = "Maximum price cannot be negative.")
    private BigDecimal maxPrice;
}
