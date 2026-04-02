package com.app.microservices.quantity_service.exception;

public class InvalidUnitMeasurementException extends RuntimeException {
	public InvalidUnitMeasurementException(String message) {
		super(message);
	}
}