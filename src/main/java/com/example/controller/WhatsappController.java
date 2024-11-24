package com.example.controller;

import com.example.Service.TwilioWhatsappService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/whatsapp")
public class WhatsappController {
    @Autowired
    private TwilioWhatsappService whatsAppService;

    @PostMapping("/send")
    public String sendWhatsAppMessage(@RequestParam String to, @RequestParam String message) {
        return whatsAppService.sendWhatsAppMessage(to, message);
    }
}
