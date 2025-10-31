package com.DBMSproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private UserDto user;

    public JwtResponse(String token, Long id, String email, String string) {
        this.token=token;
    }
}