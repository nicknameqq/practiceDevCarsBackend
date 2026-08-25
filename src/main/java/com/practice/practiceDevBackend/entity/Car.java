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

@Column(nullable = false)
private String brand;
@Column(nullable = false)
private String model;
@Column(nullable = false)
private String bodyType;
@Column(nullable = false)
private BigDecimal price;
private String image;
@Column(nullable = false)
private Integer year;
@Column(nullable = false)
@Enumerated(EnumType.STRING)
private Transmission transmission;
@Column(nullable = false)
@Enumerated(EnumType.STRING)
private FuelType fuel;
@Column(nullable = false)
private Integer seats;
@Column(nullable = false)
@Enumerated(EnumType.STRING)
private CarStatus status;


}
