package com.example.Service;

import org.springframework.stereotype.Service;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;

@Service
public class TwilioWhatsappService {
    @Value("${twilio.phone.number}")
    private String fromNumber;

    public String sendWhatsAppMessage(String toNumber, String messageContent) {
        try {
            // Twilio Sandbox ka WhatsApp number - From number
            Message message = Message.creator(
                    new com.twilio.type.PhoneNumber("whatsapp:" + toNumber ),  // Recipient's WhatsApp number (To)
                    new com.twilio.type.PhoneNumber("whatsapp:" + fromNumber),  // Twilio Sandbox ka WhatsApp number (From)
                    messageContent
            ).create();

            return "Message sent successfully. SID: " + message.getSid();
        } catch (Exception e) {
            return "Failed to send message: " + e.getMessage();
        }
    }
}
