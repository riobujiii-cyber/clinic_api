package com.dental.clinic_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dental.clinic_api.model.User;
import com.dental.clinic_api.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
public String signup(@RequestBody User user) {

    try {
        userRepository.save(user);
        return "User registered!";
    } catch (Exception e) {
        e.printStackTrace();
        return "ERROR: " + e.getMessage();
    }
}
    
    @GetMapping("/ping")
    public String ping(){
        return "WORKING";
    }
}