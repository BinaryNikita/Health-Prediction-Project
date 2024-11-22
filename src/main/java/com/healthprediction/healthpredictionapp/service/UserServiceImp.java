package com.healthprediction.healthpredictionapp.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.healthprediction.healthpredictionapp.model.User;
import com.healthprediction.healthpredictionapp.repository.UserRepository;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Autowired
     private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user){
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already exist");
        }

     user.setPassword(passwordEncoder.encode(user.getPassword()));   
     return userRepository.save(user);
    }

    @Override
    public Optional<User> loginUser(String email, String password){

        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())){
             return user;
        } else {
            return Optional.empty();
        }
    }

    
}
