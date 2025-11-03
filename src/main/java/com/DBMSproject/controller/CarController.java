package com.DBMSproject.controller;

import com.DBMSproject.dto.CarSearchDTO;
import com.DBMSproject.entity.Car;
import com.DBMSproject.services.Auth.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
@CrossOrigin(origins = "http://localhost:3000")
public class CarController {

    @Autowired
    private CarService carService;

    // ✅ Public search for customers
    @PostMapping("/search")
    public ResponseEntity<List<Car>> searchCars(@RequestBody CarSearchDTO searchDTO) {
        return ResponseEntity.ok(carService.searchCars(searchDTO));
    }

    // ✅ Get all cars (for customer view)
    @GetMapping("/all")
    public ResponseEntity<List<Car>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }
}
