package com.app.quantitymeasurementapp.unit;

import com.app.quantitymeasurementapp.unit.IMeasurable;

public interface IMeasurable { 
	String getUnitName();
	
	public double getConversionFactor();
	
	public double convertToBaseUnit(double value);
	 
	public double convertFromBaseUnit(double baseValue);

	public String getMeasurementType(); 
}