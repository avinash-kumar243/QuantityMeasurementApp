package com.apps.quantitymeasurementapp;

public class Length {
	private double value; // Length value
	private LengthUnit unit;
	
	public Length(double value, LengthUnit unit) {
		if(Double.isNaN(value)) {
			throw new IllegalArgumentException("value can't be null");
		}
		if(unit==null) {
			throw new IllegalArgumentException("unit can't be null");
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

	
	// Convert the length value to any other unit
	public Length convertTo(LengthUnit targetUnit) {
		if(targetUnit == null) {
			throw new IllegalArgumentException("Target unit can't be null");
		}
		
		// Step 1 :- convert current value to base unit (feet)
		double valueInFeet = unit.convertToBaseUnit(value);
		
		// Step 2 :- Convert inches to target unit
		double convertedValue = targetUnit.convertFromBaseUnit(valueInFeet);
		
		// Step 3 :- Return new Length Object
		return new Length(convertedValue, targetUnit); 
	}
	
	
	// Compare two length object for equality based on their values in the base unit
	private static final double EPSILON = 0.001;
	public boolean compare(Length thatLength) {
		if(thatLength == null) {
			return false;
		}
		
		return Math.abs(this.unit.convertToBaseUnit(this.value) - thatLength.unit.convertToBaseUnit(thatLength.value)) < EPSILON; 
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
		
		double currentValue = unit.convertToBaseUnit(this.value); 
		double otherValue = length.unit.convertToBaseUnit(length.value); 
		
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
		if(length == null) {
			throw new IllegalArgumentException("Length can't be null");
		}
		
		double thisBaseVal = this.unit.convertToBaseUnit(this.value);
		double thatBaseVal = length.unit.convertToBaseUnit(length.value);
		
		double result = targetUnit.convertFromBaseUnit(thisBaseVal + thatBaseVal);
		result = (int)(result * 1000.0) / 1000.0;
		
		return new Length(result, targetUnit); 
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
