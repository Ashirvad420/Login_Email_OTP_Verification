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

    @Autowired
    private EmailService emailService;
    static final Map<String, String> emailOtpMapping = new HashMap<>();

    //Email verification part date:-2024-02-20
    public Map<String, String> verifyOtp(String email, String otp) {
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

    //Login verification part date:-2024-02-21

    public Map<String, String> sendOtpForLogin(String email) {
        if (userService.isEmailVerified(email)) {
            String otp = emailService.generateOtp();
            emailOtpMapping.put(email, otp);

            // Send OTP to the user's email
            emailService.sendOtpEmail(email);

            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "OTP send successfully");
            return response;
        }
        else {
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Email is not successfully");
            return response;
        }
    }


    public Map<String, String> verifyOtpForLogin(String email, String otp) {

        String storedOtp = emailOtpMapping.get(email);

        Map<String, String> response = new HashMap<>();

        if (storedOtp != null && storedOtp.equals(otp)) {

            //OTP is valid
            response.put("status", "success");
            response.put("message", "OTP verified successfully");
        }
        else {
            //OTP is invalid
            response.put("status", "error");
            response.put("message", "invalid otp");
        }
        return response;
    }
}



// This email and Opt verification Should happen with the hashmap stored