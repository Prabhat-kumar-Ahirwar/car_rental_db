package com.DBMSproject.repository;

import com.DBMSproject.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> { }
