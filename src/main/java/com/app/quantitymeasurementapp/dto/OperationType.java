package com.app.quantitymeasurementapp.dto;


// The OperationType enum is used in the QuantityDTO, QuantityMeasurementDTO

public enum OperationType {
	ADD, SUBTRACT, MULTIPLY, DIVIDE, COMPARE, CONVERT;
	
	public String getDisplayName() {
		return this.name().toLowerCase(); 
	}
}