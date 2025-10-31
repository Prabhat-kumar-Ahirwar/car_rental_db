package com.DBMSproject.controller;

import com.DBMSproject.dto.JwtResponse;
import com.DBMSproject.dto.LoginRequest;
import com.DBMSproject.dto.SignupRequest;
import com.DBMSproject.dto.UserDto;
import com.DBMSproject.entity.User;
import com.DBMSproject.security.JwtUtils;
import com.DBMSproject.services.Auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    // ====================== SIGNUP ======================
    @PostMapping("/signup")
    public ResponseEntity<?> signupCustomer(@RequestBody SignupRequest signupRequest) {
        if (authService.hasCustomerWithEmail(signupRequest.getEmail()))
            return new ResponseEntity<>("Customer already exists with this email", HttpStatus.NOT_ACCEPTABLE);

        UserDto createdCustomerDto = authService.createCustomer(signupRequest);
        if (createdCustomerDto == null)
            return new ResponseEntity<>("Customer not created, try again later", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(createdCustomerDto, HttpStatus.CREATED);
    }

    // ====================== LOGIN ======================
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // 1️⃣ Authenticate credentials
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            // 2️⃣ Load user and generate token
            User user = authService.getUserByEmail(loginRequest.getEmail());
            if (user == null) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }

            String token = jwtUtils.generateToken(user.getEmail());

            JwtResponse jwtResponse = new JwtResponse(
                    token,
                    user.getId(),
                    user.getEmail(),
                    user.getUserRole().toString()
            );

            return new ResponseEntity<>(jwtResponse, HttpStatus.OK);

        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Login failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
