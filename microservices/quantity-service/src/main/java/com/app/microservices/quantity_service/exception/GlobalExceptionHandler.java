package com.app.microservices.quantity_service.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> handleRunTimeException(RuntimeException e, HttpServletRequest request){
		ErrorResponse Error = new ErrorResponse();
		Error.setDateTime(LocalDateTime.now()); 
		Error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		Error.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		Error.setMessage(e.getMessage());
		Error.setPath(request.getRequestURL().toString());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Error);
	}
	
	@ExceptionHandler(CategoryMismatchException.class)
	public ResponseEntity<ErrorResponse> handleCategoryMismatchException(CategoryMismatchException e, HttpServletRequest request){
		ErrorResponse Error = new ErrorResponse();
		Error.setDateTime(LocalDateTime.now());
		Error.setStatus(HttpStatus.BAD_REQUEST.value());
		Error.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
		Error.setMessage(e.getMessage());
		Error.setPath(request.getRequestURL().toString());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Error);
	}	
	
	@ExceptionHandler(InvalidUnitException.class)
	public ResponseEntity<ErrorResponse> handleInvalidUnitException(InvalidUnitException e, HttpServletRequest request){
		ErrorResponse Error = new ErrorResponse();
		Error.setDateTime(LocalDateTime.now());
		Error.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		Error.setError(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase());
		Error.setMessage(e.getMessage());
		Error.setPath(request.getRequestURL().toString());
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(Error);
	}
	
	@ExceptionHandler(InvalidUnitMeasurementException.class)
	public ResponseEntity<ErrorResponse> handleInvalidUnitMeasurementException(InvalidUnitMeasurementException e, HttpServletRequest request){
		ErrorResponse Error = new ErrorResponse();
		Error.setDateTime(LocalDateTime.now());
		Error.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		Error.setError(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase());
		Error.setMessage(e.getMessage());
		Error.setPath(request.getRequestURL().toString());
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(Error);
	}
	
	@ExceptionHandler(QuantityMeasurementException.class)
	public ResponseEntity<ErrorResponse> handleQuantityMeasurementException(QuantityMeasurementException e, HttpServletRequest request){
		ErrorResponse Error = new ErrorResponse();
		Error.setDateTime(LocalDateTime.now());
		Error.setStatus(HttpStatus.BAD_REQUEST.value());
		Error.setError("Quantity Measurement Error");
		Error.setMessage(e.getMessage());
		Error.setPath(request.getRequestURL().toString());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Error);
	}
	
	@ExceptionHandler(UnsupportedOperationException.class)
	public ResponseEntity<ErrorResponse> handleUnsupportedOperationException(UnsupportedOperationException e, HttpServletRequest request){
		ErrorResponse Error = new ErrorResponse();
		Error.setDateTime(LocalDateTime.now());
		Error.setStatus(HttpStatus.BAD_REQUEST.value());
		Error.setError("This operation not supported yet!");
		Error.setMessage(e.getMessage());
		Error.setPath(request.getRequestURL().toString());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Error);
	}
		
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handle(Exception e, HttpServletRequest request){
		ErrorResponse Error = new ErrorResponse();
		Error.setDateTime(LocalDateTime.now());
		Error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		Error.setError("Internal Server error!");
		Error.setMessage(e.getMessage());
		Error.setPath(request.getRequestURL().toString());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Error);
	}
}
