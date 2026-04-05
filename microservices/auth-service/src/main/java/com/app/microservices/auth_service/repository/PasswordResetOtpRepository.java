package com.app.microservices.auth_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.microservices.auth_service.entity.PasswordResetOtpEntity;

public interface PasswordResetOtpRepository extends JpaRepository<PasswordResetOtpEntity, Long>{
	 Optional<PasswordResetOtpEntity> findTopByEmailOrderByCreatedAtDesc(String email);  //  -> findTopByEmailOrderByCreatedAtDesc(String email)

	 Optional<PasswordResetOtpEntity> findByEmailAndOtpAndOtpUsedFalse(String email, String otp); 
	/* This means:

		find OTP record where:
	    email matches
		otp matches
		used is false
	*/

	 List<PasswordResetOtpEntity> findByEmailAndOtpUsedFalse(String email);
	// get all unused OTPs for an email -> This is a good idea because only latest OTP should remain valid.
}