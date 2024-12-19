package com.aslufcode.pizza_backend.controllers;

import com.aslufcode.pizza_backend.dao.UserDAO;
import com.aslufcode.pizza_backend.models.ApiResponse;
import com.aslufcode.pizza_backend.models.LoginRequest;
import com.aslufcode.pizza_backend.models.RegisterRequest;
import com.aslufcode.pizza_backend.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody RegisterRequest registerRequest) {
        if (userDAO.registerUser(registerRequest)) {
            return ResponseEntity.status(201).body(new ApiResponse(true, "User registered successfully!"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse(false, "Registration failed!"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        User user = userDAO.validateLogin(loginRequest.getEmail(), loginRequest.getPassword());
        if (user != null) {
            return ResponseEntity.ok(new ApiResponse(true, "Login successful!", user));
        } else {
            return ResponseEntity.status(401).body(new ApiResponse(false, "Invalid email or password!"));
        }
    }
}