package com.apps.quantitymeasurementapp;

public interface IMeasurable<U> {
	
	SupportsArithmetic supportsArithmetic = () -> true;
	
	public String getUnitName();
	
	public double getConversionFactor();
	
	public double convertToBaseUnit(double value);
	 
	public double convertFromBaseUnit(double baseValue);
	 
	
	default boolean supportsArithmetic() {
		return supportsArithmetic.isSupported(); 
	}
	
	default void validateOperationSupport(String operation) { 
		// Default implementation
		if(!supportsArithmetic()) {
            throw new UnsupportedOperationException(
                this.getClass().getSimpleName() + " does not support " + operation
            );
		}
	}
}