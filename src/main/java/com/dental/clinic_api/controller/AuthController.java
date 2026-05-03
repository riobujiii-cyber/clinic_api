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

        if(userRepository.findByEmail(user.getEmail()) != null){
            return "Email already exists!";
        }

        userRepository.save(user);
        return "User registered!";
    }

    // LOGIN
    @PostMapping("/login")
public String login(@RequestBody LoginRequest request) {

    String email = request.getEmail().trim().toLowerCase();

    User user = userRepository.findByEmail(email);
    
    user.setEmail(user.getEmail().trim().toLowerCase());

    if(user == null){
        return "User not found";
    }

    if(!user.getPassword().equals(request.getPassword())){
        return "Wrong password";
    }

    return "Login successful";
}

    @GetMapping("/ping")
    public String ping(){
        return "WORKING";
    }
}