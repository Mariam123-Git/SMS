package com.example.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.example.backend.model.User;
import com.example.backend.model.OtpCode;

import com.example.backend.repository.OtpCodeRepository;
import com.example.backend.service.SmsSender;


@Service
public class OtpService {

    @Autowired
    private OtpCodeRepository otpRepo;

    @Autowired
    private SmsSender smsSender;

    // Délai minimum entre deux OTP
    private static final long MIN_INTERVAL_SECONDS = 60;

    // Durée de validité OTP
    private static final long OTP_EXPIRATION_MINUTES = 2;

    public boolean generateAndSendOtp(User user) {
        // Vérifier le délai depuis le dernier OTP
        Optional<OtpCode> lastOtpOpt = otpRepo.findTopByUserIdOrderByCreatedAtDesc(user.getId());
        if (lastOtpOpt.isPresent()) {
            OtpCode lastOtp = lastOtpOpt.get();
            if (lastOtp.getCreatedAt().plusSeconds(MIN_INTERVAL_SECONDS).isAfter(LocalDateTime.now())) {
                return false; // Trop tôt pour renvoyer un OTP
            }
        }

        // Générer OTP
        String otp = String.valueOf((int)(Math.random() * 900000) + 100000);

        // Sauvegarder OTP
        OtpCode otpCode = new OtpCode();
        otpCode.setUserId(user.getId());
        otpCode.setCode(otp);
        otpCode.setExpiration(LocalDateTime.now().plusMinutes(OTP_EXPIRATION_MINUTES));
        otpCode.setUsed(false);
        otpRepo.save(otpCode);

        // Envoyer SMS (avec tentative si échec)
        String message = "Votre code OTP est : " + otp;
        boolean sent = smsSender.sendSmsWithRetry(user.getPhone(), message, 2);

        return sent;
    }

    public boolean verifyOtp(User user, String inputCode) {
        Optional<OtpCode> lastOtpOpt = otpRepo.findTopByUserIdOrderByCreatedAtDesc(user.getId());
        if (lastOtpOpt.isEmpty()) return false;

        OtpCode otp = lastOtpOpt.get();

        if (otp.isUsed()) return false;
        if (otp.getExpiration().isBefore(LocalDateTime.now())) return false;
        if (!otp.getCode().equals(inputCode)) return false;

        otp.setUsed(true);
        otpRepo.save(otp);
        return true;
    }
}
