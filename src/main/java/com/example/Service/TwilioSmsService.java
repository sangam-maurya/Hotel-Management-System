package com.example.Service;

import org.springframework.stereotype.Service;
import com.twilio.rest.api.v2010.account.Message;

@Service
public class TwilioSmsService {

    public String sendSms(String to , String from , String body){
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(to),
                new com.twilio.type.PhoneNumber(from),
                body).create();
        return message.getSid();

    }
}
