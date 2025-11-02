package com.DBMSproject.controller;

import com.DBMSproject.entity.Booking;
import com.DBMSproject.entity.BookingStatus;
import com.DBMSproject.repository.BookingRepository;
import com.DBMSproject.services.Auth.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/car")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingRepository.findAll());
    }

    @PutMapping("/booking/{id}/{status}")
    public ResponseEntity<?> updateBookingStatus(@PathVariable Long id, @PathVariable String status) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus(BookingStatus.valueOf(status.toUpperCase()));
        bookingRepository.save(booking);
        return ResponseEntity.ok("Status updated to " + status);
    }
}
