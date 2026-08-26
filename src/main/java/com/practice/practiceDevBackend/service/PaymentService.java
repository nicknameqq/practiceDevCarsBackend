package com.practice.practiceDevBackend.service;

import com.practice.practiceDevBackend.dto.payment.PaymentResponse;
import com.practice.practiceDevBackend.entity.Booking;
import com.practice.practiceDevBackend.entity.Payment;
import com.practice.practiceDevBackend.entity.User;
import com.practice.practiceDevBackend.entity.enums.BookingStatus;
import com.practice.practiceDevBackend.entity.enums.PaymentStatus;
import com.practice.practiceDevBackend.exception.BookingNotFoundException;
import com.practice.practiceDevBackend.exception.PaymentAlreadyExistsException;
import com.practice.practiceDevBackend.exception.PaymentNotFoundException;
import com.practice.practiceDevBackend.repository.BookingRepository;
import com.practice.practiceDevBackend.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    public PaymentResponse createPayment(Long bookingId, Authentication authentication){
        User user = (User) authentication.getPrincipal();

        Booking booking = bookingRepository
                .findByIdAndUserId(bookingId, user.getId())
                .orElseThrow(() -> new BookingNotFoundException("Booking not found."));

        if(paymentRepository.existsByBookingId(bookingId)){
            throw new PaymentAlreadyExistsException("Payment already exists for this booking.");
        }

        if(booking.getStatus() != BookingStatus.PENDING){
            throw new IllegalStateException("Only pending bookings can be paid.");
        }

        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setAmount(booking.getTotalPrice());
        payment.setStatus(PaymentStatus.PAID);
        payment.setCreatedAt(LocalDateTime.now());

        Payment savedPayment = paymentRepository.save(payment);
        return toResponse(savedPayment);

    }

    public PaymentResponse getPayment(
            Long bookingId,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();

        Booking booking = bookingRepository
                .findByIdAndUserId(bookingId, user.getId())
                .orElseThrow(() ->
                        new BookingNotFoundException("Booking not found.")
                );

        Payment payment = paymentRepository
                .findByBookingId(booking.getId())
                .orElseThrow(() ->
                        new PaymentNotFoundException("Payment not found.")
                );

        return toResponse(payment);
    }

    private PaymentResponse toResponse(Payment payment){

        PaymentResponse response = new PaymentResponse();
        response.setId(payment.getId());
        response.setBookingId(payment.getBooking().getId());
        response.setAmount(payment.getAmount());
        response.setStatus(payment.getStatus());
        response.setCreatedAt(payment.getCreatedAt());
        return response;

    }

}
