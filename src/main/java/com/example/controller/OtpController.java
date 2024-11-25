package com.example.controller;

import com.example.Service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/otp")
public class OtpController {

    @Autowired
    private OtpService otpService;

    // Generate aur send OTP endpoint
    @PostMapping("/send")
    public ResponseEntity<String> sendOtp(@RequestParam String phoneNumber) {
        String otp = otpService.generateOtp(); // OTP generate karo
        otpService.sendOtp(phoneNumber, otp); // OTP send karo
        return ResponseEntity.ok("OTP sent successfully to: " + phoneNumber);
    }
}