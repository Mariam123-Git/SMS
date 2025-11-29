package com.example.backend.repository;

import com.example.backend.model.OtpCode;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OtpCodeRepository extends JpaRepository<OtpCode, Long> {
    Optional<OtpCode> findTopByUserIdOrderByCreatedAtDesc(Long userId);
}