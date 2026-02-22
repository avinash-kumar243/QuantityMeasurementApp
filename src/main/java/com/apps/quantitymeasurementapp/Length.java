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
		INCHES(1.0),
		YARDS(36.0),
		CENTIMETERS(0.393701);
		
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
	
	// Convert the length value to any other unit
	public double convertTo(LengthUnit targetUnit) {
		double valueInInch = this.convertToBaseUnit();
		return valueInInch / targetUnit.getConversionFactor(); 
	}
	
	// Compare two length object for equality based on their values in the base unit
	private static final double EPSILON = 0.0001;
	public boolean compare(Length thatLength) {
		if(thatLength == null) {
			return false;
		}
		
		return Math.abs(this.convertToBaseUnit() - thatLength.convertToBaseUnit()) < EPSILON; 
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
		
		Length length3 = new Length(1.0, LengthUnit.YARDS);
		Length length4 = new Length(36.0, LengthUnit.INCHES);
		System.out.println("Are lengths equal? " + length3.equals(length4)); 
		
		Length length5 = new Length(100.0, LengthUnit.CENTIMETERS);
		Length length6 = new Length(39.3701, LengthUnit.INCHES);
		System.out.println("Are lengths equal? " + length5.equals(length6)); 
	}
}
