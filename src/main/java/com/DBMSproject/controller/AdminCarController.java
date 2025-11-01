package com.DBMSproject.controller;



import com.DBMSproject.entity.Car;
import com.DBMSproject.services.Auth.AdminCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000") // or your frontend port
public class AdminCarController {

    @Autowired
    private AdminCarService adminCarService;

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

    // ✅ Delete car
    @DeleteMapping("/car/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id) {
        boolean deleted = adminCarService.deleteCar(id);
        if (deleted)
            return ResponseEntity.ok("Car deleted successfully");
        else
            return ResponseEntity.status(404).body("Car not found");
    }
    // ✅ Get car by ID (for pre-filling update form)
    @GetMapping("/car/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Car car = adminCarService.getCarById(id);
        if (car != null)
            return ResponseEntity.ok(car);
        else
            return ResponseEntity.status(404).body(null);
    }

    // ✅ Update existing car
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
            @RequestParam(value = "image", required = false) MultipartFile image
    ) throws IOException {

        Car updatedCar = adminCarService.updateCar(id, brand, color, name, type, transmission, description, price, year, image);
        if (updatedCar != null)
            return ResponseEntity.ok(updatedCar);
        else
            return ResponseEntity.status(404).body(null);
    }

}

