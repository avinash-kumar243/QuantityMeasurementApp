package com.app.microservices.auth_service.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e, HttpServletRequest request) {
		ErrorResponse response = new ErrorResponse();
		
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		response.setMessage(e.getMessage());
		response.setDateTime(LocalDateTime.now());
		response.setPath(request.getRequestURL().toString());
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); 
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request) {
		ErrorResponse response = new ErrorResponse();
		
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		response.setMessage(e.getMessage());
		response.setDateTime(LocalDateTime.now());
		response.setPath(request.getRequestURL().toString());
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); 
	}
}