package com.app.microservices.auth_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.microservices.auth_service.dto.ForgotPasswordRequestDto;
import com.app.microservices.auth_service.dto.ResetPasswordRequestDto;
import com.app.microservices.auth_service.dto.VerifyOtpRequestDto;
import com.app.microservices.auth_service.service.ForgotPasswordService;

import jakarta.validation.Valid; 

@RestController
@RequestMapping("/auth/forgot-password")
@Validated
public class ForgotPasswordController {
	
	@Autowired
	private ForgotPasswordService forgotPasswordService;
	
	
	@PostMapping("/request-otp")
	public ResponseEntity<String> requestOtp(@Valid @RequestBody ForgotPasswordRequestDto requestDto) {
		String response = forgotPasswordService.requestOtp(requestDto.getEmail());
		return ResponseEntity.ok(response);
	}
	
	
	@PostMapping("/verify-otp")
	public ResponseEntity<String> verifyOtp(@Valid @RequestBody VerifyOtpRequestDto requestDto) {
		String response = forgotPasswordService.verifyOtp(requestDto.getEmail(), requestDto.getOtp());

	    if ("OTP verified successfully".equals(response)) {
	        return ResponseEntity.ok(response);
	    }
	    
	    return ResponseEntity.badRequest().body(response); 
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody ResetPasswordRequestDto requestDto) {
    	String response = forgotPasswordService.resetPassword(requestDto);

        if ("Password reset successful. Please login again.".equals(response)) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.badRequest().body(response); 
    }
}