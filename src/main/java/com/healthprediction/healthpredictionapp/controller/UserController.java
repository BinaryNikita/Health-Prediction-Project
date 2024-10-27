package com.healthprediction.healthpredictionapp.controller;

import com.healthprediction.healthpredictionapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.healthprediction.healthpredictionapp.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;


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
    public ResponseEntity<User> postMethodName(@RequestParam String email, @RequestParam String password ) {
         return userService.loginUser(email, password)
         .map(user -> ResponseEntity.ok(user))
         .orElse(ResponseEntity.status(404).body(null));
    }
    

    
}
