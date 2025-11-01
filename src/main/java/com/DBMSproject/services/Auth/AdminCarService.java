package com.DBMSproject.services.Auth;

import com.DBMSproject.entity.Car;
import com.DBMSproject.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
<<<<<<< HEAD
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
=======
>>>>>>> 43516dd4d93d0c4e4193769dda22ff3049d6423b
import java.util.List;
import java.util.Optional;

@Service
public class AdminCarService {

    @Autowired
    private CarRepository carRepository;

<<<<<<< HEAD
    // ✅ Save a new car
=======
>>>>>>> 43516dd4d93d0c4e4193769dda22ff3049d6423b
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

<<<<<<< HEAD
    // ✅ Get all cars
=======
>>>>>>> 43516dd4d93d0c4e4193769dda22ff3049d6423b
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

<<<<<<< HEAD
    // ✅ Delete car by ID
=======
>>>>>>> 43516dd4d93d0c4e4193769dda22ff3049d6423b
    public boolean deleteCar(Long id) {
        Optional<Car> car = carRepository.findById(id);
        if (car.isPresent()) {
            carRepository.deleteById(id);
            return true;
        }
        return false;
    }
<<<<<<< HEAD

    // ✅ Get car by ID
    public Car getCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    // ✅ Update car details
    public Car updateCar(Long id, String brand, String color, String name, String type,
                         String transmission, String description, double price, int year,
                         MultipartFile image) {

        Optional<Car> existingCarOpt = carRepository.findById(id);
        if (existingCarOpt.isPresent()) {
            Car car = existingCarOpt.get();

            // Update fields
            car.setBrand(brand);
            car.setColor(color);
            car.setName(name);
            car.setType(type);
            car.setTransmission(transmission);
            car.setDescription(description);
            car.setPrice(price);
            car.setYear(year);

            // Handle image (optional)
            if (image != null && !image.isEmpty()) {
                try {
                    car.setImage(image.getBytes());
                } catch (IOException e) {
                    throw new RuntimeException("Error reading car image file", e);
                }
            }

            return carRepository.save(car);
        }
        return null;
    }
=======
>>>>>>> 43516dd4d93d0c4e4193769dda22ff3049d6423b
}
