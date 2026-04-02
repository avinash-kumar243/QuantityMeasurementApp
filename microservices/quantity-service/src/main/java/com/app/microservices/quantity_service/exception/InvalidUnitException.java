package com.app.microservices.quantity_service.exception;

public class InvalidUnitException extends RuntimeException {
	public InvalidUnitException(String message) {
		super(message);
	}
}