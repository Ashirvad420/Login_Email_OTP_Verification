package com.otpexample.service;


import com.otpexample.entity.User;
import com.otpexample.userrepositoty.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User registerUser(User user)
    {
        // Save the User to the database
        return userRepository.save(user); // it will take the user details and save the user
    }
}
