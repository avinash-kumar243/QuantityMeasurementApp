package com.app.quantitymeasurementapp.unit;

public enum LengthUnit implements IMeasurable {
	FEET(1.0),
	INCHES(1.0 / 12.0), 
	YARDS(3.0), 
	CENTIMETERS(1.0 / 30.48);
	
	private final double conversionFactor;

	private LengthUnit(double conversionFactor) {
		this.conversionFactor = conversionFactor;
	}
	
	
	public double getConversionFactor() {
		return (int)(this.conversionFactor * 10000.0) / 10000.0; 
	} 
	
	public double convertToBaseUnit(double value) {
		return value * conversionFactor; 
	}
	
	public double convertFromBaseUnit(double baseValue) {
		return baseValue / conversionFactor; 
	}

	
	@Override
	public String getUnitName() {
		return this.name(); 
	}

	@Override
	public String getMeasurementType() {
		return this.getClass().getSimpleName();
	}
}