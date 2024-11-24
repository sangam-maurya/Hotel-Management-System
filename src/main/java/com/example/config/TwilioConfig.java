package com.example.config;

import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfig {

    @PostConstruct
    public void init(){
        Twilio.init("ACd7e1b390d72d77c34a6ed16fca6cb1a5" , "16155c445e34d7e946e9f27f5dca37c3");
    }

}
