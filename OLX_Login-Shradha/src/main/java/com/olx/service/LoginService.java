package com.olx.service;

import org.springframework.http.ResponseEntity;

import com.olx.dto.User;

public interface LoginService {

    ResponseEntity<Boolean> logout(String token);

    User registerUser(User user);

    User getUserInfo(String username);
    
//    boolean validateLogin(String authToken);
}
