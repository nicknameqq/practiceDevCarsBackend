package com.practice.practiceDevBackend.entity;

import com.practice.practiceDevBackend.entity.enums.CarStatus;
import com.practice.practiceDevBackend.entity.enums.FuelType;
import com.practice.practiceDevBackend.entity.enums.Transmission;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity //Этот класс - Сущность базы данных
@Table(name="cars") //Связывает этот класс с таблицей "cars" в БД, хранит метаданные полей в бд
@Getter // создает геттеры для класса
@Setter // создает сеттеры для класса
@NoArgsConstructor //создает конструктор без параметров , нужен для создания обьектов сущности
public class Car {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

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
@Enumerated(EnumType.STRING)
private Transmission transmission;
@NotNull(message = "Fuel type cannot be null.")
@Enumerated(EnumType.STRING)
private FuelType fuel;
@NotNull(message = "Seats cannot be null.")
@Min(value = 1, message = "Seats must be at least 1.")
@Max(value = 10, message = "Seats cannot be greater than 10.")
private Integer seats;
@NotNull(message = "Status cannot be null.")
@Enumerated(EnumType.STRING)
private CarStatus status;


}
