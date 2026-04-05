package com.app.microservices.auth_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VerifyOtpRequestDto {
	
	@NotNull(message = "Email is required")
	@Email(message = "Enter a valid email")
	private String email;
	
	@NotBlank(message = "OTP is required")
	private String otp;
}