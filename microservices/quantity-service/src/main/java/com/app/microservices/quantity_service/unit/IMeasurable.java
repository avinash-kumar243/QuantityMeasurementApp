package com.app.microservices.quantity_service.unit;

public interface IMeasurable { 
	String getUnitName();
	
	public double getConversionFactor();
	
	public double convertToBaseUnit(double value);
	 
	public double convertFromBaseUnit(double baseValue);

	public String getMeasurementType(); 
}