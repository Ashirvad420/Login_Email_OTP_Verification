package com.otpexample.controller;

import com.otpexample.entity.User;
import com.otpexample.service.EmailService;
import com.otpexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/register")
    public Map<String, String> registerUser(@RequestBody User user)
    {
        //Register user without email verification
        User registeredUser = userService.registerUser(user);

        // send OTP email for verification
        emailService.sendOtpEmail(user.getEmail());
        return null;
    }
}

// if email validation is show 0 in database its false.. if it is show 1 in database its true.

// Date :- 2024-02-16 to 2024-02-19 to 2024-02-20