package com.practice.practiceDevBackend.controller.Admin;


import com.practice.practiceDevBackend.dto.booking.BookingResponse;
import com.practice.practiceDevBackend.entity.enums.BookingStatus;
import com.practice.practiceDevBackend.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/bookings")
@RequiredArgsConstructor
public class AdminBookingController {

    private final BookingService bookingService;

    @PutMapping("/{id}/cancel")
    public ResponseEntity<BookingResponse> cancelBookingByAdmin(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                bookingService.cancelBookingByAdmin(id)
        );
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<BookingResponse> updateBookingStatus(
            @PathVariable Long id,
            @RequestBody BookingStatus status) {

        return ResponseEntity.ok(
                bookingService.updateBookingStatus(id, status)
        );
    }
}
