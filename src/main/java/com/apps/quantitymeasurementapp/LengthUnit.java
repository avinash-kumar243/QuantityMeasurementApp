package com.apps.quantitymeasurementapp;

public enum LengthUnit {
	FEET(1.0),
	INCHES(1.0 / 12.0),
	YARDS(3.0),
	CENTIMETERS(1.0 / 30.48);
	
	private final double conversionFactor;

	private LengthUnit(double conversionFactor) {
		this.conversionFactor = conversionFactor;
	}
	
	
	public double getConversionFactor() {
		return this.conversionFactor; 
	} 
	
	public double convertToBaseUnit(double value) {
		return round(value * conversionFactor); 
	}
	
	public double convertFromBaseUnit(double baseValue) {
		return round(baseValue / conversionFactor); 
	}
	
	private double round(double value) {
		return Math.round(value * 1000.0) / 1000.0; 
	}
}
