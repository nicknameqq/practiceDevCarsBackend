package com.practice.practiceDevBackend.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PriceRangeValidator.class)
@Documented
public @interface PriceRange {
    String message() default "Minimum price cannot be greater than maximum price.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
