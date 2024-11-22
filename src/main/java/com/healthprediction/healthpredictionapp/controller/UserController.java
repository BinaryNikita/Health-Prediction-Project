package com.healthprediction.healthpredictionapp.controller;

import com.healthprediction.healthpredictionapp.service.UserService;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.healthprediction.healthpredictionapp.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> postMethodName(@Valid @RequestBody User user) {
       User registeredUser = userService.registerUser(user);
       return ResponseEntity.ok(registeredUser);
    }
    

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestParam String email, @RequestParam String password) {
        Optional<User> user = userService.loginUser(email, password);
    
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get()); // Return 200 OK with the User if present
        } else {
            System.out.println("No user found with the provided email or password."); // Log message for debugging
            return ResponseEntity.status(401).body(null); // Return 401 Unauthorized if user not found
        }
    }
    
    
    

    
}
