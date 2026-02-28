package com.apps.quantitymeasurementapp;

public enum VolumeUnit implements IMeasurable {
	
	LITRE(1.0),
	MILLILITRE(0.001),
	GALLON(3.78541);
	
	private final double conversionFactor;

	private VolumeUnit(double conversionFactor) {
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
//	
//	private double round(double value) {
//		return Math.round(value * 1000.0) / 1000.0; 
//	}
//	

	@Override
	public String getUnitName() {
		return "Volume";
	}
}
