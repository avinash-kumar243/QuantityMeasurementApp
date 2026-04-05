package com.app.microservices.auth_service.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.microservices.auth_service.dto.ResetPasswordRequestDto;
import com.app.microservices.auth_service.entity.AuthEntity;
import com.app.microservices.auth_service.entity.PasswordResetOtpEntity;
import com.app.microservices.auth_service.exception.UserNotFoundException;
import com.app.microservices.auth_service.repository.AuthRepository;
import com.app.microservices.auth_service.repository.PasswordResetOtpRepository;

@Service
public class ForgotPasswordService {

    private final AuthRepository authRepository;
    private final PasswordResetOtpRepository passwordResetOtpRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public ForgotPasswordService(AuthRepository authRepository,
                                 PasswordResetOtpRepository passwordResetOtpRepository,
                                 EmailService emailService,
                                 PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.passwordResetOtpRepository = passwordResetOtpRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    // Request OTP
    public String requestOtp(String email) {
        Optional<AuthEntity> user = authRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found with this email: " + email);
        }

        // Invalidate old unused OTPs
        List<PasswordResetOtpEntity> oldOtps = passwordResetOtpRepository.findByEmailAndOtpUsedFalse(email);

        for(PasswordResetOtpEntity entity : oldOtps) {
            entity.setOtpUsed(true);
        }
        passwordResetOtpRepository.saveAll(oldOtps);

        // Generate new OTP
        String otp = generateOtp();

        // Store OTP
        PasswordResetOtpEntity otpEntity = new PasswordResetOtpEntity();
        otpEntity.setEmail(email);
        otpEntity.setOtp(otp);
        otpEntity.setCreatedAt(LocalDateTime.now());
        otpEntity.setExpireAt(LocalDateTime.now().plusMinutes(5));
        otpEntity.setOtpUsed(false);

        passwordResetOtpRepository.save(otpEntity);

        try {
        	emailService.sendOtpEmail(email, otp);
        } catch(Exception e) {
        	e.printStackTrace();
        	throw new RuntimeException("Failed to send OTP email: " + e.getMessage());
        }
        
        return "OTP sent successfully to your email";
    }

    // Verify OTP
    public String verifyOtp(String email, String otp) {
        Optional<PasswordResetOtpEntity> optionalOtp =
                passwordResetOtpRepository.findByEmailAndOtpAndOtpUsedFalse(email, otp);

        if (optionalOtp.isEmpty()) {
            return "Invalid OTP";
        }

        PasswordResetOtpEntity otpEntity = optionalOtp.get();

        if (otpEntity.getExpireAt().isBefore(LocalDateTime.now())) {
            return "OTP has expired";
        }

        return "OTP verified successfully";
    }

    // Reset Password
    public String resetPassword(ResetPasswordRequestDto requestDto) {
        if (!requestDto.getNewPassword().equals(requestDto.getConfirmPassword())) {
            return "New password and confirm password must be same";
        }

        Optional<AuthEntity> user = authRepository.findByEmail(requestDto.getEmail());

        if (user.isEmpty()) {
            return "Email does not exist";
        }

        Optional<PasswordResetOtpEntity> optionalOtp =
                passwordResetOtpRepository.findByEmailAndOtpAndOtpUsedFalse(
                        requestDto.getEmail(),
                        requestDto.getOtp()
                );

        if (optionalOtp.isEmpty()) {
            return "Invalid OTP";
        }

        PasswordResetOtpEntity otpEntity = optionalOtp.get();

        if (otpEntity.getExpireAt().isBefore(LocalDateTime.now())) {
            return "OTP has expired";
        }

        AuthEntity authUser = user.get();
        authUser.setPassword(passwordEncoder.encode(requestDto.getNewPassword()));
        authRepository.save(authUser);

        otpEntity.setOtpUsed(true);
        passwordResetOtpRepository.save(otpEntity);

        return "Password reset successful. Please login again.";
    }

    // Generate 6-digit OTP
    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
}