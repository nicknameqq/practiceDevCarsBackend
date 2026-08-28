package com.practice.practiceDevBackend.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ReportSummaryResponse {

    private long totalUsers;

    private long totalCars;

    private long totalBookings;

    private BigDecimal totalRevenue;

    private long pendingBookings;

    private long activeBookings;

    private long completedBookings;

    private long cancelledBookings;
}
