package com.practice.practiceDevBackend.repository;

import com.practice.practiceDevBackend.entity.Booking;
import com.practice.practiceDevBackend.entity.enums.BookingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    boolean existsByCarIdAndStatusInAndStartDateLessThanAndEndDateGreaterThan(
            Long carId,
            Collection<BookingStatus> statuses,
            LocalDate endDate,
            LocalDate startDate
    );

    Page<Booking> findAllByUserId(Long userId, Pageable pageable);
    Optional<Booking> findByIdAndUserId(Long id, Long userId);
    long countByStatus(BookingStatus status);
    @Query("""
        SELECT COALESCE(SUM(b.totalPrice), 0)
        FROM Booking b
        WHERE b.status = :status
        """)
    BigDecimal getTotalRevenue(@Param("status") BookingStatus status);

}
