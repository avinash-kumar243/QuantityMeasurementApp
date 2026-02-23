package com.apps.quantitymeasurementapp;

public class Length {
	private double value; // Length value
	private LengthUnit unit;
	
	public Length(double value, LengthUnit unit) {
		if(Double.isNaN(value)) {
			throw new IllegalArgumentException("throw");
		}
		if(unit==null) {
			throw new IllegalArgumentException("throw");
		}
		this.value = value;
		this.unit = unit;
	}
	
	public double getValue() {
		return value; 
	}
	public LengthUnit getUnit() {
		return unit;
	}
	
		
	// Create LengthUnit ENUM
	public enum LengthUnit {
		FEET(1.0),
		INCHES(1.0 / 12.0),
		YARDS(3.0),
		CENTIMETERS(1.0 / 30.48);
		
		private final double conversionFactor;
		
		LengthUnit(double conversionFactor) {
			this.conversionFactor = conversionFactor; 
		}
		
		public double getConversionFactor() {
			return conversionFactor;
		}
	}
	
	
	// Convert the length value to base unit (feet)
	public double convertToBaseUnit() {
		return this.value * this.unit.getConversionFactor(); 
	}
	
	
	// Convert the length value to any other unit
	public Length convertTo(LengthUnit targetUnit) {
		if(targetUnit == null) {
			throw new IllegalArgumentException("Target unit can't be null");
		}
		
		// Step 1 :- convert current value to base unit (feet)
		double valueInFeet = this.convertToBaseUnit();
		
		// Step 2 :- Convert inches to target unit
		double convertedValue = valueInFeet / targetUnit.getConversionFactor();
		
		// Step 3 :- Return new Length Object
		return new Length(convertedValue, targetUnit); 
	}
	
	
	// Compare two length object for equality based on their values in the base unit
	private static final double EPSILON = 0.01;
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
	
	
	public int compareTo(Length length) {
		if(length == null) {
			throw new IllegalArgumentException("Length can't be null");
		}
		
		double currentValue = this.convertToBaseUnit(); 
		double otherValue = length.convertToBaseUnit();
		
		if(Math.abs(currentValue - otherValue) < EPSILON) {
			return 0;
		}
		return currentValue < otherValue ? -1 : 1; 
	}
	
	
	@Override
	public String toString() {
		return value + " " + unit;
	}
	
	
	// A method to add two Length of same category
	public Length add(Length newLength) {
		return addAndConvert(newLength, this.unit); 
	}
	
	
	// A method to perform addition then conversion on base unit value
	public Length add(Length length, LengthUnit targetUnit) {
		return addAndConvert(length, targetUnit); 
	}
	
	
	// A private method to perform addition conversion on base unit value
	private Length addAndConvert(Length length, LengthUnit targetUnit) {
		if(targetUnit == null) {
			throw new IllegalArgumentException("Target Unit can't be null");
		}
		
		Length currLengthToBaseUnit = this.convertTo(targetUnit);
		Length newLengthToBaseUnit = length.convertTo(targetUnit);
		
		double result = ((int)((currLengthToBaseUnit.getValue() + newLengthToBaseUnit.getValue()) * 1000.0) / 1000.0);
		
		return new Length(result, targetUnit); 
	}

	
	// A method to convert a length value from the base unit to a specific target unit
	private double convertFromBaseToTargetUnit(double lengthInFeet, LengthUnit targetUnit) {
		return lengthInFeet / targetUnit.getConversionFactor(); 
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
