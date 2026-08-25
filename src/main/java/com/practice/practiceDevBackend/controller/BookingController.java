package com.practice.practiceDevBackend.controller;

import com.practice.practiceDevBackend.dto.booking.BookingRequest;
import com.practice.practiceDevBackend.dto.booking.BookingResponse;
import com.practice.practiceDevBackend.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(
            @Valid @RequestBody BookingRequest request,
            Authentication authentication) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookingService.createBooking(request, authentication));
    }

    @GetMapping
    public ResponseEntity<Page<BookingResponse>> getAllBookings(
            @PageableDefault(size = 12)Pageable pageable,
            Authentication authentication
            ){

        return ResponseEntity.status(HttpStatus.OK).body(bookingService.getAllBookings(pageable, authentication)) ;

    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getBookingById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                bookingService.getBookingById(id)
        );
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<BookingResponse> deleteBookingById(@PathVariable Long id){
        return ResponseEntity.ok(bookingService.cancelBooking(id));
    }

}