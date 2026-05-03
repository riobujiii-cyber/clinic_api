package com.dental.clinic_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dental.clinic_api.model.User;
import com.dental.clinic_api.repository.UserRepository;
import com.dental.clinic_api.dto.LoginRequest;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // SIGNUP
    @PostMapping("/signup")
public String signup(@RequestBody User user) {

    user.setEmail(user.getEmail().trim().toLowerCase());

    if(userRepository.findByEmail(user.getEmail()) != null){
        return "Email already exists!";
    }

    user.setRole("USER"); // 👈 DEFAULT

    userRepository.save(user);
    return "User registered!";
}

    // LOGIN
    @PostMapping("/login")
public User login(@RequestBody LoginRequest request) {

    String email = request.getEmail().trim().toLowerCase();

    User user = userRepository.findByEmail(email);

    if(user == null){
        throw new RuntimeException("User not found");
    }

    return user; // returns JSON (includes role)
}

    // FORGOT PASSWORD (SIMPLE RESET)
    @PostMapping("/forgot-password")
public String forgotPassword(@RequestBody LoginRequest request) {

    String email = request.getEmail().trim().toLowerCase();

    User user = userRepository.findByEmail(email);

    if(user == null){
        return "User not found";
    }

    user.setPassword("123456");
    userRepository.save(user);

    return "Password reset successful";
}

@PostMapping("/reset-password")
public String resetPassword(@RequestBody LoginRequest request) {

    String email = request.getEmail().trim().toLowerCase();
    String newPassword = request.getPassword();

    User user = userRepository.findByEmail(email);

    if(user == null){
        return "User not found";
    }

    user.setPassword(newPassword);
    userRepository.save(user);

    return "Password updated successfully";
}
    @GetMapping("/ping")
    public String ping(){
        return "WORKING";
    }
}