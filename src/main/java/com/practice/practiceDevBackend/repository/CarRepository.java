package com.practice.practiceDevBackend.repository;

import com.practice.practiceDevBackend.entity.Car;
import com.practice.practiceDevBackend.entity.enums.CarStatus;
import com.practice.practiceDevBackend.entity.enums.Transmission;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

//Мы не пишем реализацию:
//Spring Data JPA сам создаст реализацию этого интерфейса.

//мы сразу получим готовые методы:
//
//findAll()
//findById()
//save()
//delete()
//deleteById()
//count()
//existsById()
public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {
    Page<Car> findByBrandIgnoreCase(String brand, Pageable pageable); //пошук за брендом, не враховуючи регістр
    Page<Car> findByTransmission(Transmission transmission, Pageable pageable);
    long countByStatus(CarStatus status);
}