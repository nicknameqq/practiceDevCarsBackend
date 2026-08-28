package com.practice.practiceDevBackend.service;

import com.practice.practiceDevBackend.dto.booking.BookingRequest;
import com.practice.practiceDevBackend.dto.booking.BookingResponse;
import com.practice.practiceDevBackend.entity.Booking;
import com.practice.practiceDevBackend.entity.Car;
import com.practice.practiceDevBackend.entity.User;
import com.practice.practiceDevBackend.entity.enums.BookingStatus;
import com.practice.practiceDevBackend.entity.enums.CarStatus;
import com.practice.practiceDevBackend.entity.enums.UserRole;
import com.practice.practiceDevBackend.exception.*;
import com.practice.practiceDevBackend.mapper.BookingMapper;
import com.practice.practiceDevBackend.repository.BookingRepository;
import com.practice.practiceDevBackend.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final CarRepository carRepository;
    private final BookingMapper bookingMapper;

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }


    public BookingResponse createBooking(BookingRequest request, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        if (!request.getEndDate().isAfter(request.getStartDate())) {
            throw new InvalidBookingDateException(
                    "End date must be after start date."
            );
        }

        Car car = carRepository.findById(request.getCarId())
                .orElseThrow(() -> new CarNotFoundException("Car not found."));

        if (car.getStatus() != CarStatus.AVAILABLE) {
            throw new CarNotAvailableException(
                    "Car is not available for booking."
            );
        }


        boolean alreadyBooked =
                bookingRepository
                        .existsByCarIdAndStatusInAndStartDateLessThanAndEndDateGreaterThan(
                                car.getId(),
                                List.of(
                                        BookingStatus.PENDING,
                                        BookingStatus.ACTIVE
                                ),
                                request.getEndDate(),
                                request.getStartDate()
                        );

        if (alreadyBooked) {
            throw new BookingDateAlreadyBookedException(
                    "Car is already booked for these dates."
            );
        }


        long days = ChronoUnit.DAYS.between(
                request.getStartDate(),
                request.getEndDate()
        );

        BigDecimal totalPrice = car.getPrice()
                .multiply(BigDecimal.valueOf(days));

        Booking booking = new Booking();

        booking.setCar(car);
        booking.setStartDate(request.getStartDate());
        booking.setEndDate(request.getEndDate());
        booking.setTotalPrice(totalPrice);
        booking.setStatus(BookingStatus.PENDING);
        booking.setUser(user);


        Booking savedBooking = bookingRepository.save(booking);

        return bookingMapper.toResponse(savedBooking);
    }

    public BookingResponse getBookingById(Long id) {

        User currentUser = getCurrentUser();
        Booking booking = bookingRepository
                .findByIdAndUserId(id, currentUser.getId())
                .orElseThrow(() ->
                        new BookingNotFoundException("Booking not found.")
                );

        return bookingMapper.toResponse(booking);
    }

    public Page<BookingResponse> getAllBookings(
            Pageable pageable,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();

        if (user.getRole() == UserRole.ADMIN) {
            return bookingRepository.findAll(pageable)
                    .map(bookingMapper::toResponse);
        }

        return bookingRepository.findAllByUserId(user.getId(), pageable)
                .map(bookingMapper::toResponse);
    }

    public BookingResponse deleteBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found."));
        bookingRepository.delete(booking);
        System.out.println("Deleting was successfully.");
        return bookingMapper.toResponse(booking);
    }

    public BookingResponse cancelBooking(Long id) {

        User currentUser = getCurrentUser();

        Booking booking = bookingRepository
                .findByIdAndUserId(id, currentUser.getId())
                .orElseThrow(() ->
                        new BookingNotFoundException("Booking not found.")
                );

        BookingStatus status = booking.getStatus();

        if (status == BookingStatus.CANCELLED) {
            throw new BookingAlreadyCancelledException(
                    "Booking is already cancelled"
            );
        }

        if (status == BookingStatus.COMPLETED) {
            throw new BookingCannotBeCanceledException(
                    "Completed booking cannot be cancelled"
            );
        }

        if (status != BookingStatus.PENDING
                && status != BookingStatus.ACTIVE) {

            throw new BookingCannotBeCanceledException(
                    "Booking with status " + status + " cannot be cancelled"
            );
        }

        booking.setStatus(BookingStatus.CANCELLED);

        Booking cancelledBooking =
                bookingRepository.save(booking);

        return bookingMapper.toResponse(cancelledBooking);
    }

    public BookingResponse cancelBookingByAdmin(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() ->
                        new BookingNotFoundException("Booking not found.")
                );

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new BookingAlreadyCancelledException(
                    "Booking is already cancelled"
            );
        }

        if (booking.getStatus() == BookingStatus.COMPLETED) {
            throw new BookingCannotBeCanceledException(
                    "Completed booking cannot be cancelled"
            );
        }

        booking.setStatus(BookingStatus.CANCELLED);

        Booking cancelledBooking =
                bookingRepository.save(booking);

        return bookingMapper.toResponse(cancelledBooking);
    }


    public BookingResponse updateBookingStatus(
            Long id,
            BookingStatus newStatus
    ) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() ->
                        new BookingNotFoundException("Booking not found.")
                );

        BookingStatus currentStatus = booking.getStatus();

        if (currentStatus == BookingStatus.CANCELLED) {
            throw new BookingCannotBeCanceledException(
                    "Cancelled booking status cannot be changed."
            );
        }

        if (currentStatus == BookingStatus.COMPLETED) {
            throw new BookingCannotBeCanceledException(
                    "Completed booking status cannot be changed."
            );
        }

        if (currentStatus == BookingStatus.PENDING
                && newStatus != BookingStatus.ACTIVE
                && newStatus != BookingStatus.CANCELLED) {

            throw new IllegalArgumentException(
                    "Pending booking can only become ACTIVE or CANCELLED."
            );
        }

        if (currentStatus == BookingStatus.ACTIVE
                && newStatus != BookingStatus.COMPLETED
                && newStatus != BookingStatus.CANCELLED) {

            throw new IllegalArgumentException(
                    "Active booking can only become COMPLETED or CANCELLED."
            );
        }

        booking.setStatus(newStatus);

        return bookingMapper.toResponse(
                bookingRepository.save(booking)
        );
    }
}