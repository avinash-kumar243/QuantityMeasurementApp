package com.app.microservices.quantity_service.exception;

public class UnsupportedOperationException extends RuntimeException {
	public UnsupportedOperationException(String message) {
		super(message);
	}
}
