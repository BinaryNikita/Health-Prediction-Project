package com.healthprediction.healthpredictionapp.service;
import java.util.Optional;

import com.healthprediction.healthpredictionapp.model.User;

public interface UserService {

    User registerUser(User user);
    Optional<User> loginUser(String email, String password);


}
