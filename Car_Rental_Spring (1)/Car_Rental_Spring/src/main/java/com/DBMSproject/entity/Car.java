package com.DBMSproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String color;
    private String name;
    private String type;
    private String transmission;
    private String description;
    private double price;
    private int year;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;
}
