package com.DBMSproject.services.Auth;

import com.DBMSproject.entity.Booking;
import com.DBMSproject.entity.BookingStatus;
import com.DBMSproject.entity.Car;
import com.DBMSproject.entity.User;
import com.DBMSproject.repository.BookingRepository;
import com.DBMSproject.repository.CarRepository;
import com.DBMSproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;

    // ✅ Get all cars
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    // ✅ Get car by ID
    public Car getCarById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));
    }

    // ✅ Book a car
    public Booking bookCar(Long userId, Long carId, String fromDateStr, String toDateStr) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        LocalDate fromDate = LocalDate.parse(fromDateStr);
        LocalDate toDate = LocalDate.parse(toDateStr);

        long days = ChronoUnit.DAYS.between(fromDate, toDate);
        if (days <= 0) throw new RuntimeException("Invalid date range");

        double totalPrice = car.getPrice() * days;

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setCar(car);
        booking.setFromDate(fromDate);
        booking.setToDate(toDate);
        booking.setDays(days);
        booking.setTotalPrice(totalPrice);
        booking.setStatus(BookingStatus.PENDING);// ✅ Use ENUM, not string

        return bookingRepository.save(booking);
    }

    // ✅ Get all bookings for a user
    public List<Booking> getBookingsByUser(Long userId) {
        return bookingRepository.findByUserId(userId);
    }
}
