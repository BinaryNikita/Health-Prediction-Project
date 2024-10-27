package com.healthprediction.healthpredictionapp.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.healthprediction.healthpredictionapp.model.User;
import com.healthprediction.healthpredictionapp.repository.UserRepository;

public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public User registerUser(User user){
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already exist");
        }

     return userRepository.save(user);
    }

    @Override
    public Optional<User> loginUser(String email, String password){

        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)){
             return user;
        } else {
            return Optional.empty();
        }
    }

    
}
