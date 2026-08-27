package com.practice.practiceDevBackend.dto.booking;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingCarResponse {

    private Long id;
    private String brand;
    private String model;
    private Integer year;
    private String image;
}