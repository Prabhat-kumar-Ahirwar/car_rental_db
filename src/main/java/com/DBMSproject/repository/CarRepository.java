package com.DBMSproject.repository;

import com.DBMSproject.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("""
        SELECT c FROM Car c
        WHERE (:brand IS NULL OR LOWER(c.brand) LIKE LOWER(CONCAT('%', :brand, '%')))
          AND (:type IS NULL OR LOWER(c.type) LIKE LOWER(CONCAT('%', :type, '%')))
          AND (:transmission IS NULL OR LOWER(c.transmission) LIKE LOWER(CONCAT('%', :transmission, '%')))
          AND (:color IS NULL OR LOWER(c.color) LIKE LOWER(CONCAT('%', :color, '%')))
          AND (:price IS NULL OR c.price = :price)
          AND (:year IS NULL OR c.year = :year)
    """)
    List<Car> searchCars(
            @Param("brand") String brand,
            @Param("type") String type,
            @Param("transmission") String transmission,
            @Param("color") String color,
            @Param("price") Double price,
            @Param("year") Integer year
    );
}
