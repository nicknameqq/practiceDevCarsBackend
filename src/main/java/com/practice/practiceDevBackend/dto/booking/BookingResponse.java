package com.practice.practiceDevBackend.dto.booking;

import com.practice.practiceDevBackend.entity.enums.BookingStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class BookingResponse {

    private Long id;

    private Long carId;

    private LocalDate startDate;

    private LocalDate endDate;

    private BigDecimal totalPrice;

    private BookingStatus status;
}