package com.DBMSproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/api/user/profile")
    public String getUserProfile() {
        return "✅ Access granted to protected route! (Your JWT works fine)";
    }
}