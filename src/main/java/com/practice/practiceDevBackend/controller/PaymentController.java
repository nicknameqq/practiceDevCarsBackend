package com.practice.practiceDevBackend.controller;

import com.practice.practiceDevBackend.dto.payment.PaymentResponse;
import com.practice.practiceDevBackend.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/{bookingId}")
    public ResponseEntity<PaymentResponse> createPayment(
            @PathVariable Long bookingId,
            Authentication authentication
    ){
        return  ResponseEntity.status(HttpStatus.CREATED).body(paymentService.createPayment(bookingId, authentication));
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<PaymentResponse> getPayment(
            @PathVariable Long bookingId,
            Authentication authentication
    ){
        return ResponseEntity.ok(paymentService.getPayment(bookingId, authentication));
    }
}
