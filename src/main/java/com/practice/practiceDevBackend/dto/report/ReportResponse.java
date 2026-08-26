package com.practice.practiceDevBackend.dto.report;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ReportResponse {

    private long totalCars;
    private long availableCars;

    private long totalBookings;
    private long pendingBookings;
    private long cancelledBookings;
    private long completedBookings;

    private BigDecimal totalRevenue;

}
