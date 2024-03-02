package com.otpexample.controller;

import com.otpexample.service.EmailVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {

    // Login Controller Day:- 2024-02-21

    @Autowired
    public EmailVerificationService emailVerificationService;

    // http://localhost:8080/api/send-otp-for-login?email=""
    @PostMapping("/send-otp-for-login")
    public Map<String,String> sendOtpForLogin(@RequestParam String email)
    {
        return emailVerificationService.sendOtpForLogin(email);
    }
    @PostMapping("/verify-otp-for-login")
    public Map<String,String> sendOtpForLogin(@RequestParam String email, @RequestParam String otp) {

        return emailVerificationService.verifyOtpForLogin(email, otp);

        }
    }
