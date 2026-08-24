package com.practice.practiceDevBackend.validation;

import com.practice.practiceDevBackend.dto.car.CarFilter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PriceRangeValidator implements ConstraintValidator<PriceRange, CarFilter> {

    @Override
    public boolean isValid(
            CarFilter filter,
            ConstraintValidatorContext context) {

        if (filter == null) {
            return true;
        }

        if (filter.getMinPrice() == null || filter.getMaxPrice() == null) {
            return true;
        }

        return filter.getMinPrice().compareTo(filter.getMaxPrice()) <= 0;
    }
}