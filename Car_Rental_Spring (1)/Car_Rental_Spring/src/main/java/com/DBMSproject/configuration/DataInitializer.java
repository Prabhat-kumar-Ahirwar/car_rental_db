package com.DBMSproject.configuration;

import com.DBMSproject.entity.User;
import com.DBMSproject.enums.UserRole;
import com.DBMSproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        String adminEmail = "admin@system.com";
        String adminPassword = "admin123";

        // 👇 Only create admin if not already present
        if (userRepository.findFirstByEmail(adminEmail).isEmpty()) {
            User admin = new User();
            admin.setName("System Admin");
            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setUserRole(UserRole.ADMIN);
            userRepository.save(admin);
            System.out.println("✅ Admin account created → " + adminEmail + " / " + adminPassword);
        } else {
            System.out.println("ℹ️ Admin account already exists.");
        }
    }
}