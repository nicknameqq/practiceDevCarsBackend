package com.practice.practiceDevBackend.controller.Admin;


import com.practice.practiceDevBackend.dto.booking.BookingResponse;
import com.practice.practiceDevBackend.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/bookings")
@RequiredArgsConstructor
public class AdminBookingController {

    private final BookingService bookingService;

    @PutMapping("/{id}/cancel")
    public ResponseEntity<BookingResponse> cancelBooking(
            @PathVariable Long id
    ) {
      return ResponseEntity.ok(bookingService.cancelBookingByAdmin(id));
    }
}
