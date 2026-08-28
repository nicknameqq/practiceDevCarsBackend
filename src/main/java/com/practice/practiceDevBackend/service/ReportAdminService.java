package com.practice.practiceDevBackend.service;

import com.practice.practiceDevBackend.dto.report.ReportSummaryResponse;
import com.practice.practiceDevBackend.entity.enums.BookingStatus;
import com.practice.practiceDevBackend.repository.BookingRepository;
import com.practice.practiceDevBackend.repository.CarRepository;
import com.practice.practiceDevBackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ReportAdminService {

    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final BookingRepository bookingRepository;

    public ReportSummaryResponse getSummary(){
        long totalUsers = userRepository.count();

        long totalCars = carRepository.count();

        long totalBookings = bookingRepository.count();

        long pendingBookings =
                bookingRepository.countByStatus(BookingStatus.PENDING);

        long activeBookings =
                bookingRepository.countByStatus(BookingStatus.ACTIVE);

        long completedBookings =
                bookingRepository.countByStatus(BookingStatus.COMPLETED);

        long cancelledBookings =
                bookingRepository.countByStatus(BookingStatus.CANCELLED);

        BigDecimal totalRevenue =
                bookingRepository.getTotalRevenue(
                        BookingStatus.COMPLETED
                );

        return new ReportSummaryResponse(
                totalUsers,
                totalCars,
                totalBookings,
                totalRevenue,
                pendingBookings,
                activeBookings,
                completedBookings,
                cancelledBookings
        );
    }
}
