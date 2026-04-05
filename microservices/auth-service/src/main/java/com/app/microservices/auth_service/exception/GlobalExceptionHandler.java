package com.app.microservices.auth_service.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler { 
	
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e, HttpServletRequest request) {
		ErrorResponse response = new ErrorResponse();
		
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
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
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
	        MethodArgumentNotValidException e,
	        HttpServletRequest request) {

	    ErrorResponse response = new ErrorResponse();

	    response.setStatus(HttpStatus.BAD_REQUEST.value());
	    response.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());

	    String message = "Validation failed";
	    if (e.getBindingResult().getFieldError() != null) {
	        message = e.getBindingResult().getFieldError().getDefaultMessage();
	    }

	    response.setMessage(message);
	    response.setDateTime(LocalDateTime.now());
	    response.setPath(request.getRequestURL().toString());

	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(MailException.class)
	public ResponseEntity<ErrorResponse> handleMailException(
	        MailException e,
	        HttpServletRequest request) {

	    ErrorResponse response = new ErrorResponse();

	    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	    response.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
	    response.setMessage("Failed to send OTP email. Check mail configuration.");
	    response.setDateTime(LocalDateTime.now());
	    response.setPath(request.getRequestURL().toString());

	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> handleRuntimeException(
	        RuntimeException e,
	        HttpServletRequest request) {

	    ErrorResponse response = new ErrorResponse();

	    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	    response.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
	    response.setMessage(e.getMessage());
	    response.setDateTime(LocalDateTime.now());
	    response.setPath(request.getRequestURL().toString());

	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
}