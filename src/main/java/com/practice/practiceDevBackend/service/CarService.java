package com.practice.practiceDevBackend.service;

import com.practice.practiceDevBackend.dto.car.CarFilter;
import com.practice.practiceDevBackend.dto.car.CarRequest;
import com.practice.practiceDevBackend.dto.car.CarResponse;
import com.practice.practiceDevBackend.entity.Car;
import com.practice.practiceDevBackend.entity.enums.CarStatus;
import com.practice.practiceDevBackend.exception.CarNotFoundException;
import com.practice.practiceDevBackend.mapper.CarMapper;
import com.practice.practiceDevBackend.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.practice.practiceDevBackend.repository.CarSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service //обозначает для Spring что это сервисный Spring-компонент (Bean), в котором обычно находится бизнес-логика.
@RequiredArgsConstructor // автоматически генерирует в классе конструктор для всех полей, отмеченных как final, а также для полей с аннотацией @NonNull.
public class CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final PasswordEncoder passwordEncoder;

    public Page<CarResponse> getAllCars(CarFilter filter, Pageable pageable) {
        Specification<Car> specification =
                CarSpecification.isAvailableForCatalog();

        if (filter.getBrand() != null && !filter.getBrand().isBlank()) {
            specification = CarSpecification.filteredByBrand(filter.getBrand());
        }

        if (filter.getBodyType() != null && !filter.getBodyType().isBlank()) {

            Specification<Car> bodyTypeSpecification =
                    CarSpecification.filterByBodyType(filter.getBodyType());

            if (specification == null) {
                specification = bodyTypeSpecification;
            } else {
                specification = specification.and(bodyTypeSpecification);
            }
        }

        if (filter.getTransmission() != null) {
            Specification<Car> transmissionSpecification =
                    CarSpecification.filterByTransmission(filter.getTransmission());

            if (specification == null) {
                specification = transmissionSpecification;
            } else {
                specification = specification.and(transmissionSpecification);
            }
        }

        if (filter.getMinPrice() != null) {

            Specification<Car> minPriceSpecification =
                    CarSpecification.filterByMinPrice(filter.getMinPrice());

            if (specification == null) {
                specification = minPriceSpecification;
            } else {
                specification = specification.and(minPriceSpecification);
            }
        }

        if (filter.getMaxPrice() != null) {

            Specification<Car> maxPriceSpecification =
                    CarSpecification.filterByMaxPrice(filter.getMaxPrice());

            if (specification == null) {
                specification = maxPriceSpecification;
            } else {
                specification = specification.and(maxPriceSpecification);
            }
        }


        Page<Car> cars = carRepository.findAll(specification, pageable);

        return cars.map(carMapper::toResponse);
    }

    public CarResponse createCar(CarRequest request) {
        Car car = carMapper.toEntity(request);
        Car savedCar = carRepository.save(car);
        return carMapper.toResponse(savedCar);
    }

    public CarResponse getCarById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Car not found."));
        return carMapper.toResponse(car);
    }

    public CarResponse deleteCarById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Car not found."));
        car.setStatus(CarStatus.UNAVAILABLE);

        Car updatedCar = carRepository.save(car);
        return carMapper.toResponse(updatedCar);
    }

    public CarResponse updateCar(Long id, CarRequest request) {
        Car car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException("Car not found."));

        carMapper.updateEntity(car, request);

        Car updatedCar = carRepository.save(car);
        return carMapper.toResponse(updatedCar);
    }


}
