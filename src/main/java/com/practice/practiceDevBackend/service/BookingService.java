package com.practice.practiceDevBackend.service;

import com.practice.practiceDevBackend.dto.booking.BookingRequest;
import com.practice.practiceDevBackend.dto.booking.BookingResponse;
import com.practice.practiceDevBackend.entity.Booking;
import com.practice.practiceDevBackend.entity.Car;
import com.practice.practiceDevBackend.entity.enums.BookingStatus;
import com.practice.practiceDevBackend.entity.enums.CarStatus;
import com.practice.practiceDevBackend.exception.*;
import com.practice.practiceDevBackend.mapper.BookingMapper;
import com.practice.practiceDevBackend.repository.BookingRepository;
import com.practice.practiceDevBackend.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final CarRepository carRepository;
    private final BookingMapper bookingMapper;

    public BookingResponse createBooking(BookingRequest request) {

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
                bookingRepository.existsByCarIdAndStartDateLessThanAndEndDateGreaterThan(
                        car.getId(),
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

        Booking savedBooking = bookingRepository.save(booking);

        return bookingMapper.toResponse(savedBooking);
    }

    public BookingResponse getBookingById(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found."));

        return bookingMapper.toResponse(booking);
    }

    public Page<BookingResponse> getAllBookings(Pageable pageable){
        return bookingRepository.findAll(pageable)
                .map(bookingMapper::toResponse);
    }

    public BookingResponse deleteBookingById(Long id){
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found."));
        bookingRepository.delete(booking);
        System.out.println("Deleting was successfully.");
        return bookingMapper.toResponse(booking);
    }

    public BookingResponse cancelBooking(Long id){
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found."));

        if(booking.getStatus() == BookingStatus.CANCELLED){
            throw new BookingAlreadyCancelledException("Booking is already cancelled");
        }

        if(booking.getStatus() == BookingStatus.COMPLETED) {
            throw new BookingCannotBeCanceledException("Completed booking cannot be cancelled");
        }
        booking.setStatus(BookingStatus.CANCELLED);
        Booking cancelledBooking = bookingRepository.save(booking);
        return bookingMapper.toResponse(cancelledBooking);
    }
}