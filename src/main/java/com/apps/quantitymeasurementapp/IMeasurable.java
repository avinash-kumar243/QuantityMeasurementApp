package com.apps.quantitymeasurementapp;

public interface IMeasurable<U> {
	double getConversionFactor();
	double convertToBaseUnit(double value);
	double convertFromBaseUnit(double baseValue);
	String getUnitName();
}