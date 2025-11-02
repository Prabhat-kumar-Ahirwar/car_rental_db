package com.DBMSproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarSearchDTO {
    private String brand;
    private String type;
    private String transmission;
    private String color;
    private Double price;  // use wrapper types (can be null)
    private Integer year;  // use wrapper types (can be null)
}
