package com.dental.clinic_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dental.clinic_api.model.User;
import com.dental.clinic_api.repository.UserRepository;
import com.dental.clinic_api.dto.LoginRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // SIGNUP
    @PostMapping("/signup")
public ResponseEntity<?> signup(@RequestBody User user) {

    user.setEmail(user.getEmail().trim().toLowerCase());

    if(userRepository.findByEmail(user.getEmail()) != null){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", "Email already exists!"));
    }

    user.setRole("USER");
    userRepository.save(user);

    return ResponseEntity.ok(Map.of("message", "User registered!"));
}

    // LOGIN
    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest request) {

    String email = request.getEmail().trim().toLowerCase();

    User user = userRepository.findByEmail(email);

    if(user == null || !user.getPassword().equals(request.getPassword())){
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "Invalid email or password"));
    }

    return ResponseEntity.ok(user);
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