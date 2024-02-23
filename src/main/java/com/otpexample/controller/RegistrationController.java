package com.otpexample.controller;

import com.otpexample.entity.User;
import com.otpexample.service.EmailService;
import com.otpexample.service.EmailVerificationService;
import com.otpexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailVerificationService emailVerificationService;

    @PostMapping("/register")
    public Map<String, String> registerUser(@RequestBody User user)
    {
        //Register user without email verification
        User registeredUser = userService.registerUser(user);

        // send OTP email for verification
        emailService.sendOtpEmail(user.getEmail());


        // create a HashMap to send response back of email day:-2024-02-20

        Map<String,String> response = new HashMap<>(); // content can be store in hashmap as a key value pair
        response.put("status","success");
        response.put("message","User registration successfully.Check your email for verification");
        return response;

//        return null;
    }

    @PostMapping("/verify-otp")
    public Map<String,String> verifyOtp(@RequestParam String email, @RequestBody String otp)
    {
        return emailVerificationService.verifyOtp(email,otp);
    }
}

// if email validation is show 0 in database its false.. if it is show 1 in database its true.

// Date :- 2024-02-16 to 2024-02-19 to 2024-02-20