package com.otpexample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

// How EmailVerification static import
import static com.otpexample.service.EmailVerificationService.emailOtpMapping;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private final UserService userService;

    @Autowired
    public EmailService(JavaMailSender javaMailSender, UserService userService) {
        // it is constructor based injection
        this.javaMailSender = javaMailSender;
        this.userService = userService;
    }

    // Build one method generate opt

    public String generateOtp() {

        return String.format("%06d", new java.util.Random().nextInt(1000000));
    }

    public void sendOtpEmail(String email) {

        String otp = generateOtp(); // call generate otp and store otp string
        // save the otp for later verification
        emailOtpMapping.put(email, otp);

        //send otp to the user's email
        sendEmail(email,"OTP for Verification","Your OTP IS : "+ otp);
    }

    private void sendEmail(String to, String subject,String text)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("your.gmail@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
}

// we required mail java sender dependency without that sending otp can not happen

// generateOtp does when we call particular method then its generate six digit "%06d" Otp random number