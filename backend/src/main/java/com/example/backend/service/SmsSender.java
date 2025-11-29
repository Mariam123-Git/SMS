package com.example.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.*;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.*;

@Service
public class SmsSender {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${sms.api.url}")
    private String SMS_API_URL;

    @Value("${sms.health.url}")
    private String HEALTH_URL;

    @Value("${app.sms.api-key}")
    private String SMS_API_KEY;

    public boolean sendSmsWithRetry(String phone, String message, int attempts) {
     //   if (!isServerAlive()) return false;
        for (int i = 0; i < attempts; i++) {
            try {
                // Payload
                Map<String, String> payload = new HashMap<>();
                payload.put("to", phone);
                payload.put("message", message);

                // Headers avec x-api-key
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.set("x-api-key", SMS_API_KEY);  // <--- correct

                HttpEntity<Map<String, String>> request = new HttpEntity<>(payload, headers);

                // Envoi du POST
                restTemplate.postForObject(SMS_API_URL, request, String.class);
                return true;
            } catch (Exception e) {
                System.out.println("Erreur envoi SMS: " + e.getMessage());
                try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
            }
        }
        return false;
    }

    public boolean isServerAlive() {
        try {
            restTemplate.getForObject(HEALTH_URL, String.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
