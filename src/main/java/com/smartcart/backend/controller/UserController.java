package com.smartcart.backend.controller;

import com.smartcart.backend.dto.ApiResponse;
import com.smartcart.backend.dto.LoginResponse;
import com.smartcart.backend.model.User;
import com.smartcart.backend.security.JwtUtil;
import com.smartcart.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody User user) {
        try {
            User savedUser = userService.registerUser(user);
            String token = jwtUtil.generateToken(savedUser.getId());
            LoginResponse loginResponse = new LoginResponse(
                savedUser.getId(),
                savedUser.getFullName(),
                savedUser.getPhoneNumber(),
                savedUser.getMarketName()
            );
            // Include token in response
            Map<String, Object> data = Map.of(
                "user", loginResponse,
                "token", token
            );
            return ResponseEntity.ok(ApiResponse.success("Registration successful", data));
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
            String token = jwtUtil.generateToken(user.getId());
            
            LoginResponse loginResponse = new LoginResponse(
                user.getId(),
                user.getFullName(),
                user.getPhoneNumber(),
                user.getMarketName()
            );
            
            Map<String, Object> data = Map.of(
                "user", loginResponse,
                "token", token
            );
            return ResponseEntity.ok(ApiResponse.success("Login successful", data));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
}