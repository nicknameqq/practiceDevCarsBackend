package com.practice.practiceDevBackend.service;

import com.practice.practiceDevBackend.dto.report.ReportResponse;
import com.practice.practiceDevBackend.entity.enums.BookingStatus;
import com.practice.practiceDevBackend.repository.BookingRepository;
import com.practice.practiceDevBackend.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final CarRepository carRepository;
    private final BookingRepository bookingRepository;

    public ReportResponse getReport(){

        ReportResponse response = new ReportResponse();
        response.setTotalCars(carRepository.count());
        response.setAvailableCars(
                carRepository.countByStatus(
                        com.practice.practiceDevBackend.entity.enums.CarStatus.AVAILABLE
                )
        );

        response.setTotalBookings(bookingRepository.count());
        response.setPendingBookings(
                bookingRepository.countByStatus(BookingStatus.PENDING)
        );
        response.setCancelledBookings(
                bookingRepository.countByStatus(BookingStatus.CANCELLED)
        );
        response.setCompletedBookings(
                bookingRepository.countByStatus(BookingStatus.COMPLETED)
        );

        BigDecimal revenue = bookingRepository.getTotalRevenue(BookingStatus.COMPLETED);

        response.setTotalRevenue(
                revenue != null ? revenue : BigDecimal.ZERO
        );

        return response;
    }

}
