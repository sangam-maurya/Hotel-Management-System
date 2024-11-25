package com.example.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpService {

    private static final String ACCOUNT_SID = "ACd7e1b390d72d77c34a6ed16fca6cb1a5";
    private static final String AUTH_TOKEN = "16155c445e34d7e946e9f27f5dca37c3";
    private static final String TWILIO_PHONE_NUMBER = "+19787248108";

    private Map<String, String> otpStore = new HashMap<>();
    // Constructor for Twilio initialization
    public OtpService() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    // OTP generate karne ka method
    public String generateOtp() {
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }

    // OTP ko SMS ke through bhejne ka method
    public void sendOtp(String phoneNumber, String otp) {
        Message.creator(
                        new PhoneNumber("+917318383616"), // Receiver's phone number
                        new PhoneNumber(TWILIO_PHONE_NUMBER), // Sender's Twilio phone number
                        "Your OTP code is: " + otp) // Message content
                .create();
        otpStore.put(phoneNumber, otp);
    }

    public boolean verifyOtp(String phoneNumber, String enteredOtp) {
        // Compare entered OTP with the stored OTP
        String storedOtp = otpStore.get(phoneNumber);
        return storedOtp != null && storedOtp.equals(enteredOtp);
    }
}
