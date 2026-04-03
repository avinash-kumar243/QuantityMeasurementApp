package com.app.microservices.auth_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.microservices.auth_service.dto.LoginRequestDTO;
import com.app.microservices.auth_service.dto.ResponseDTO;
import com.app.microservices.auth_service.dto.SignupRequestDTO;
import com.app.microservices.auth_service.service.AuthService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private AuthService authService;
	 
	public AuthController(AuthService userService) {
		this.authService = userService;
	}


	@PostMapping("/login")
	public ResponseEntity<ResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
		
		ResponseDTO user = authService.loginUser(request);
		
		return ResponseEntity.ok().body(new ResponseDTO(user.getToken(), "login sucess")); 
	}
	
	
	@PostMapping("/signup")
	public ResponseEntity<ResponseDTO> signup(@Valid @RequestBody SignupRequestDTO request) {
		
		ResponseDTO user = authService.registerUser(request);
		
		return ResponseEntity.ok().body(user); 
	} 
}