package com.DBMSproject.services.Auth;

import com.DBMSproject.dto.SignupRequest;
import com.DBMSproject.dto.UserDto;
import com.DBMSproject.entity.User;

public interface AuthService {

    UserDto createCustomer(SignupRequest signupRequest);

    boolean hasCustomerWithEmail(String email );
    User getUserByEmail(String email);
}
