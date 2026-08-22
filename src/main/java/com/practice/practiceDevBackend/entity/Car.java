package com.practice.practiceDevBackend.entity;

import jakarta.persistence.*;
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

private String brand;
private String model;
private String bodyType;
private BigDecimal price;
private String image;
private Integer year;
private String transmission;
private String fuel;
private Integer seats;
private String status;


}
