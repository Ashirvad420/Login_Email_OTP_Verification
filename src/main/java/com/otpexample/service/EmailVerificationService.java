package com.otpexample.service;

import com.otpexample.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailVerificationService {

    @Autowired
    private UserService userService;
    static final Map<String,String> emailOtpMapping = new HashMap<>();

    //Email verification part date:-2024-02-24
    public Map<String,String> verifyOtp(String email,String otp)
    {
        String storedOtp = emailOtpMapping.get(email); // get  the OTP

        Map<String, String> response = new HashMap<>();

        if (storedOtp != null && storedOtp.equals(otp)) {

            User user = userService.getUserByEmail(email);

            if (user != null) {
                emailOtpMapping.remove(email);
                userService.verifyEmail(user);
                response.put("status", "success");
                response.put("message", "Email verified successfully");
            } else {

                response.put("status", "error");
                response.put("message", "User not found");
            }

        } else {

            response.put("status", "error");
            response.put("message", "Invalid otp");

        }
        return response;
    }

}


// This email and Opt verification Should happen with the hashmap stored