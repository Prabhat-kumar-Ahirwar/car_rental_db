package com.DBMSproject.controller;

import com.DBMSproject.dto.BookingRequest;
import com.DBMSproject.dto.CarSearchDTO;
import com.DBMSproject.entity.Booking;
import com.DBMSproject.entity.Car;
import com.DBMSproject.services.Auth.CarService;
import com.DBMSproject.services.Auth.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // ✅ Get all available cars
    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAllCars() {
        return ResponseEntity.ok(customerService.getAllCars());
    }

    // ✅ Get car by ID
    @GetMapping("/car/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCarById(id));
    }

    // ✅ Book a car (using @RequestParam)
    @PostMapping("/book")
    public ResponseEntity<?> bookCar(@RequestBody BookingRequest request) {
        Booking booking = customerService.bookCar(
                request.getUserId(),
                request.getCarId(),
                request.getFromDate(),
                request.getToDate()
        );
        return ResponseEntity.ok(booking);
    }

    // ✅ View bookings by user
    @GetMapping("/bookings/{userId}")
    public ResponseEntity<List<Booking>> getBookingsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(customerService.getBookingsByUser(userId));
    }

    @Autowired
    private CarService carService;
    // ✅ Customer can search for cars
    @PostMapping("/car/search")
    public ResponseEntity<List<Car>> searchCars(@RequestBody CarSearchDTO searchDTO) {
        return ResponseEntity.ok(carService.searchCars(searchDTO));
    }
}
