package com.apps.quantitymeasurementapp;

public class Length {
	private double value; // Length value
	private LengthUnit unit;
	
	public Length(double value, LengthUnit unit) {
		this.value = value;
		this.unit = unit;
	}
	
	// Create LengthUnit ENUM
	public enum LengthUnit {
		FEET(12.0),
		INCHES(1.0);
		
		private final double conversionFactor;
		
		LengthUnit(double conversionFactor) {
			this.conversionFactor = conversionFactor; 
		}
		
		public double getConversionFactor() {
			return conversionFactor;
		}
	}
	
	// Convert the length value to base unit (inches)
	public double convertToBaseUnit() {
		return this.value * this.unit.getConversionFactor(); 
	}
	
	// Compare two length object for equality based on their values in the base unit
	public boolean compare(Length thatLength) {
		if(thatLength == null) {
			return false;
		}
		
		return Double.compare(this.convertToBaseUnit(), thatLength.convertToBaseUnit()) == 0; 
	}
	
	// Equals()
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		
		if(obj == null || this.getClass() != obj.getClass()) { 
			return false;
		}
		
		return compare((Length)obj); 
	}

	public static void main(String args[]) {
		Length length1 = new Length(1.0, LengthUnit.FEET);
		Length length2 = new Length(12.0, LengthUnit.INCHES);
		
		System.out.println("Are lengths equal? " + length1.equals(length2)); 
	}
}
