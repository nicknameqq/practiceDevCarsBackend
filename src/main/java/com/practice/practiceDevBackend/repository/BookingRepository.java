package com.practice.practiceDevBackend.repository;

import com.practice.practiceDevBackend.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    boolean existsByCarIdAndStartDateLessThanAndEndDateGreaterThan(
            Long carId,
            LocalDate endDate,
            LocalDate startDate
    );
}
