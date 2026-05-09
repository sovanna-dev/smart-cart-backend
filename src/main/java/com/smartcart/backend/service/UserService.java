package com.smartcart.backend.service;

import com.smartcart.backend.model.User;
import com.smartcart.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Register a new user (with encrypted password)
    // Register a new user
    public String registerUser(User user) {
    if (userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
        return "ERROR: Phone number already registered!";
    }

    user.setPassword(passwordEncoder.encode(user.getPassword()));
    User savedUser = userRepository.save(user);
    return "SUCCESS:" + savedUser.getId() + ":" + savedUser.getFullName();
}

   // Login user - returns user ID and name
    public String loginUser(String phoneNumber, String password) {
    Optional<User> userOptional = userRepository.findByPhoneNumber(phoneNumber);

    if (userOptional.isEmpty()) {
        return "ERROR: User not found!";
    }

    User user = userOptional.get();

    if (!passwordEncoder.matches(password, user.getPassword())) {
        return "ERROR: Invalid password!";
    }

    // Return success with user ID and name
    return "SUCCESS:" + user.getId() + ":" + user.getFullName();
}
    // Get user by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}