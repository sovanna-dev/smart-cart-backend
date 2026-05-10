package com.smartcart.backend.controller;

import com.smartcart.backend.dto.ApiResponse;
import com.smartcart.backend.dto.LoginResponse;
import com.smartcart.backend.model.User;
import com.smartcart.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody User user) {
        try {
            User savedUser = userService.registerUser(user);
            LoginResponse loginResponse = new LoginResponse(
                savedUser.getId(),
                savedUser.getFullName(),
                savedUser.getPhoneNumber(),
                savedUser.getMarketName()
            );
            return ResponseEntity.ok(ApiResponse.success("Registration successful", loginResponse));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> loginUser(@RequestBody Map<String, String> loginRequest) {
        try {
            String phoneNumber = loginRequest.get("phoneNumber");
            String password = loginRequest.get("password");
            
            User user = userService.loginUser(phoneNumber, password);
            LoginResponse loginResponse = new LoginResponse(
                user.getId(),
                user.getFullName(),
                user.getPhoneNumber(),
                user.getMarketName()
            );
            return ResponseEntity.ok(ApiResponse.success("Login successful", loginResponse));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
}