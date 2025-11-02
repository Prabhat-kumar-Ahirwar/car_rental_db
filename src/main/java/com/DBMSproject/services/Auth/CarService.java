package com.DBMSproject.services.Auth;

import com.DBMSproject.dto.CarSearchDTO;
import com.DBMSproject.entity.Car;
import com.DBMSproject.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> searchCars(CarSearchDTO searchDTO) {
        return carRepository.searchCars(
                searchDTO.getBrand(),
                searchDTO.getType(),
                searchDTO.getTransmission(),
                searchDTO.getColor(),
                searchDTO.getPrice(),
                searchDTO.getYear()
        );
    }
}
