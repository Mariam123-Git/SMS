package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;

import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.OtpService;


@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private OtpService otpService;

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOtp(@RequestBody Map<String, String> req) {
        String username = req.get("username");
        Optional<User> userOpt = userRepo.findByUsername(username);
        if (userOpt.isEmpty()) return ResponseEntity.badRequest().body("Utilisateur inconnu");

        boolean sent = otpService.generateAndSendOtp(userOpt.get());
        if (!sent) return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Serveur SMS indisponible ou délai trop court");

        return ResponseEntity.ok("OTP envoyé");
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody Map<String, String> req) {
        String username = req.get("username");
        String otp = req.get("otp");

        Optional<User> userOpt = userRepo.findByUsername(username);
        if (userOpt.isEmpty()) return ResponseEntity.badRequest().body("Utilisateur inconnu");

        boolean ok = otpService.verifyOtp(userOpt.get(), otp);
        if (!ok) return ResponseEntity.badRequest().body("OTP invalide");

        return ResponseEntity.ok("OTP validé");
    }
}
