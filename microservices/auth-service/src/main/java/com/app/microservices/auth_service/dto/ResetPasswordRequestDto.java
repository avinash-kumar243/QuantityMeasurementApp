package com.app.microservices.auth_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResetPasswordRequestDto {

	@NotNull(message = "Email is required")
	@Email(message = "Enter a valid email")
	private String email;
	
	@NotNull(message = "OTP is required")
	private String otp;
	
	@NotNull(message = "New password is required")
	private String newPassword;
		
	@NotNull(message = "Conform password is required")
	private String confirmPassword;	
}