package com.DBMSproject.controller;

import com.DBMSproject.dto.CarSearchDTO;
import com.DBMSproject.entity.Car;
import com.DBMSproject.services.Auth.AdminCarService;
import com.DBMSproject.services.Auth.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000") // frontend URL
public class AdminCarController {

    @Autowired
    private AdminCarService adminCarService;

    @Autowired
    private CarService carService;

    // ✅ Add new car
    @PostMapping("/car")
    public ResponseEntity<Car> addCar(
            @RequestParam("brand") String brand,
            @RequestParam("color") String color,
            @RequestParam("name") String name,
            @RequestParam("type") String type,
            @RequestParam("transmission") String transmission,
            @RequestParam("description") String description,
            @RequestParam("price") double price,
            @RequestParam("year") int year,
            @RequestParam("image") MultipartFile image) throws IOException {

        Car car = new Car();
        car.setBrand(brand);
        car.setColor(color);
        car.setName(name);
        car.setType(type);
        car.setTransmission(transmission);
        car.setDescription(description);
        car.setPrice(price);
        car.setYear(year);
        car.setImage(image.getBytes());

        return ResponseEntity.ok(adminCarService.saveCar(car));
    }

    // ✅ Get all cars
    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAllCars() {
        return ResponseEntity.ok(adminCarService.getAllCars());
    }

    // ✅ Get car by ID (for edit page)
    @GetMapping("/car/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Car car = adminCarService.getCarById(id);
        if (car != null) {
            return ResponseEntity.ok(car);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    // ✅ Update car by ID
    @PutMapping("/car/{id}")
    public ResponseEntity<Car> updateCar(
            @PathVariable Long id,
            @RequestParam("brand") String brand,
            @RequestParam("color") String color,
            @RequestParam("name") String name,
            @RequestParam("type") String type,
            @RequestParam("transmission") String transmission,
            @RequestParam("description") String description,
            @RequestParam("price") double price,
            @RequestParam("year") int year,
            @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {

        Car existingCar = adminCarService.getCarById(id);
        if (existingCar == null) {
            return ResponseEntity.status(404).body(null);
        }

        existingCar.setBrand(brand);
        existingCar.setColor(color);
        existingCar.setName(name);
        existingCar.setType(type);
        existingCar.setTransmission(transmission);
        existingCar.setDescription(description);
        existingCar.setPrice(price);
        existingCar.setYear(year);

        if (image != null && !image.isEmpty()) {
            existingCar.setImage(image.getBytes());
        }

        return ResponseEntity.ok(adminCarService.saveCar(existingCar));
    }

    // ✅ Delete car
    @DeleteMapping("/car/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id) {
        boolean deleted = adminCarService.deleteCar(id);
        if (deleted)
            return ResponseEntity.ok("Car deleted successfully");
        else
            return ResponseEntity.status(404).body("Car not found");
    }

    // ✅ Search cars (admin)
    @PostMapping("/car/search")
    public ResponseEntity<List<Car>> searchCars(@RequestBody CarSearchDTO searchDTO) {
        return ResponseEntity.ok(carService.searchCars(searchDTO));
    }
}
