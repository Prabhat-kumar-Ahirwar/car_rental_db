package com.DBMSproject.services.Auth;

import com.DBMSproject.dto.CarSearchDTO;
import com.DBMSproject.entity.Car;
import com.DBMSproject.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class AdminCarService {

    @Autowired
    private CarRepository carRepository;

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public boolean deleteCar(Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Car updateCar(Long id, String brand, String color, String name, String type,
                         String transmission, String description, Double price,
                         Integer year, MultipartFile image) throws IOException {

        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        if (brand != null) car.setBrand(brand);
        if (color != null) car.setColor(color);
        if (name != null) car.setName(name);
        if (type != null) car.setType(type);
        if (transmission != null) car.setTransmission(transmission);
        if (description != null) car.setDescription(description);
        if (price != null) car.setPrice(price);
        if (year != null) car.setYear(year);
        if (image != null && !image.isEmpty()) car.setImage(image.getBytes());

        return carRepository.save(car);
    }

    // ✅ New: Search functionality
    public List<Car> searchCars(CarSearchDTO searchDTO) {
        Car exampleCar = new Car();
        exampleCar.setBrand(searchDTO.getBrand());
        exampleCar.setType(searchDTO.getType());
        exampleCar.setTransmission(searchDTO.getTransmission());
        exampleCar.setColor(searchDTO.getColor());

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues() // ✅ ignore null fields
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Car> example = Example.of(exampleCar, matcher);
        return carRepository.findAll(example);

    }
    public Car getCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

}
