package com.DBMSproject.entity;

import com.DBMSproject.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    // ✅ Important: ensures enum is stored as text, not as number
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
}
