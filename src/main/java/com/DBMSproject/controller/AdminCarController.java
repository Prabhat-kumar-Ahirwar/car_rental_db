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
    // ✅ Update existing car
    @PutMapping("/car/{id}")
    public ResponseEntity<Car> updateCar(
            @PathVariable Long id,
            @RequestParam(value = "brand", required = false) String brand,
            @RequestParam(value = "color", required = false) String color,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "transmission", required = false) String transmission,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "price", required = false) Double price,
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "image", required = false) MultipartFile image
    ) throws IOException {
        Car updatedCar = adminCarService.updateCar(id, brand, color, name, type, transmission, description, price, year, image);
        return ResponseEntity.ok(updatedCar);
    }
    // ✅ Search cars (admin)
    @Autowired
    private CarService carService;

    @PostMapping("/car/search")
    public ResponseEntity<List<Car>> searchCars(@RequestBody CarSearchDTO searchDTO) {
        return ResponseEntity.ok(carService.searchCars(searchDTO));
    }



}

