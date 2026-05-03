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

        // 👇 DEBUG SIGNUP EMAIL
        System.out.println("SIGNUP EMAIL = " + user.getEmail());

        // CLEAN EMAIL BEFORE SAVING
        user.setEmail(user.getEmail().trim().toLowerCase());

        if(userRepository.findByEmailIgnoreCase(user.getEmail()) != null){
            return "Email already exists!";
        }

        userRepository.save(user);
        return "User registered!";
    }

    // LOGIN
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        String email = request.getEmail().trim().toLowerCase();

        // 👇 DEBUG LOGIN EMAIL
        System.out.println("LOGIN EMAIL = " + email);

        User user = userRepository.findByEmailIgnoreCase(email);

        if(user == null){
            return "User not found";
        }

        return "Login successful";
    }

    @GetMapping("/ping")
    public String ping(){
        return "WORKING";
    }
}