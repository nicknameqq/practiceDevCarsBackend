package com.practice.practiceDevBackend.repository;

import com.practice.practiceDevBackend.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

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
public interface CarRepository extends JpaRepository<Car, Long> {
}