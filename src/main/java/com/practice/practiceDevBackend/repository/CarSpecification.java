package com.practice.practiceDevBackend.repository;

import com.practice.practiceDevBackend.entity.Car;
import com.practice.practiceDevBackend.entity.enums.CarStatus;
import com.practice.practiceDevBackend.entity.enums.Transmission;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.parameters.P;

import java.math.BigDecimal;

public class CarSpecification {
    public static Specification<Car> filteredByBrand(String brand){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        criteriaBuilder.lower(root.get("brand")), brand.toLowerCase()
                );


    }
    public static Specification<Car> filterByTransmission(Transmission transmission) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("transmission"),
                        transmission
                );
    }


    public static Specification<Car> filterByMinPrice(BigDecimal minPrice) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(
                        root.get("price"),
                        minPrice
                );
    }
    public static Specification<Car> filterByMaxPrice(BigDecimal maxPrice) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(
                        root.get("price"),
                        maxPrice
                );
    }

    public static Specification<Car> filterByBodyType(String bodyType) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        criteriaBuilder.lower(root.get("bodyType")),
                        bodyType.toLowerCase()
                );
    }

    public static Specification<Car> filterByStatus(CarStatus status) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("status"),
                        status
                );
    }

    public static Specification<Car> isAvailableForCatalog() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.notEqual(
                        root.get("status"),
                        CarStatus.UNAVAILABLE
                );
    }


}
