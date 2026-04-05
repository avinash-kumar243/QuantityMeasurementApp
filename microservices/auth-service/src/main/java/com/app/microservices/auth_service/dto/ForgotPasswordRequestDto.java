package com.app.microservices.auth_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ForgotPasswordRequestDto {
	
	@NotBlank(message = "Email is required")
	@Email(message = "Enter a valid email")
	private String email;
}