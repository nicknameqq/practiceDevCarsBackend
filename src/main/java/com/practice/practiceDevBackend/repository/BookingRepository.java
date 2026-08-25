package com.practice.practiceDevBackend.repository;

import com.practice.practiceDevBackend.entity.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    boolean existsByCarIdAndStartDateLessThanAndEndDateGreaterThan(
            Long carId,
            LocalDate endDate,
            LocalDate startDate
    );

    Page<Booking> findAllByUserId(Long userId, Pageable pageable);
    Optional<Booking> findByIdAndUserId(Long id, Long userId);


}
