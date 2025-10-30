package com.DBMSproject.services.Auth;

import com.DBMSproject.dto.SignupRequest;
import com.DBMSproject.dto.UserDto;

public interface AuthService {

    UserDto createCustomer(SignupRequest signupRequest);

    boolean hasCustomerWithEmail(String email );
}
