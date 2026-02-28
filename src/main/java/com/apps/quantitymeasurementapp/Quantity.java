package com.apps.quantitymeasurementapp;

public class Quantity<U extends IMeasurable> {
	private final double value;
	private final U unit;
	private static final double EPSILON = 0.0001;
	
	public Quantity(double value, U unit) {
		if(unit == null) {
			throw new IllegalArgumentException("Unit can't be null");
		}
		if(Double.isNaN(value)) {
			throw new IllegalArgumentException("Value must be finite");
		}
		
		this.value = value;
		this.unit = unit;
	}
	
	public double getValue() {
		return value;
	}
	public U getUnit() {
		return unit;
	}

	
	// equals()
	public boolean equals(Object obj) {
		if(this == obj) return true;
		
		if(obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		
		Quantity<?> thatObj = (Quantity<?>) obj; 
		
		 if(!this.unit.getClass().equals(thatObj.unit.getClass())) {
		     return false;
		 }
		
		double thisBase = unit.convertToBaseUnit(value);
		double thatBase = thatObj.unit.convertToBaseUnit(thatObj.value);
		 
		return Math.abs(thisBase - thatBase) < EPSILON;  
	}
	
	// ConvertTo()
	public Quantity<U> convertTo(U targetUnit) {
		if(targetUnit == null) { 
			throw new IllegalArgumentException("Target Unit can't be null!!!");
		}
		if(!this.unit.getClass().equals(targetUnit.getClass())) {
			throw new IllegalArgumentException("Unit types must be not null!!!");
		}
		
		double thisBase = unit.convertToBaseUnit(value);
		double thatBase = targetUnit.convertFromBaseUnit(thisBase);
		
		return new Quantity<>(thatBase, targetUnit);  
	}
	
	// add()
	public Quantity<U> add(Quantity<U> other) {
		return addAndConvert(other, this.unit);
	}
	
	// add (., .)
	public Quantity<U> add(Quantity<U> other, U targetUnit) {
		if(targetUnit == null) {
			throw new IllegalArgumentException("Target unit can't be null");
		}
		return addAndConvert(other, targetUnit); 
	}
	
	// Helper method to add Quantity
	private Quantity<U> addAndConvert(Quantity<U> other, U targetUnit) {
		if(this.unit == null || other.unit == null) {
			throw new IllegalArgumentException("Quantity unit can't be null!!!");
		}

	    if(!this.unit.getClass().equals(other.unit.getClass())) {
	        throw new IllegalArgumentException("Cannot add different unit categories");
	    }
		
		double thisBase = unit.convertToBaseUnit(value);
		double thatBase = other.unit.convertToBaseUnit(other.value);
		
		double sum = thisBase + thatBase;
		
		double result = targetUnit.convertFromBaseUnit(sum);
		
		return new Quantity<U>(result, targetUnit); 
	}
	
	@Override
	public String toString() {
		return value + " " + unit;
	}

	public static void main(String[] args) {
		
		// Length Equality
		Quantity<LengthUnit> lengthInFeet = new Quantity<>(10.0, LengthUnit.FEET);
		Quantity<LengthUnit> lengthInInches = new Quantity<>(120.0, LengthUnit.INCHES);
		
		boolean isEqual = lengthInFeet.equals(lengthInInches);
		System.out.println("Are 10.0 feet and 120.0 inhes equals? " + isEqual);
		
		
		// Weight Equality
		Quantity<WeightUnit> weightInKilograms = new Quantity<>(2, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> weightInGrams = new Quantity<WeightUnit>(2000, WeightUnit.GRAM);
		
		boolean isEqual2 = weightInKilograms.equals(weightInGrams);
		System.out.println("Are 2.0 kg and 2000.0 g equals? " + isEqual2);
		
		
		// Conversion
		Quantity<LengthUnit> lengthInYards = lengthInFeet.convertTo(LengthUnit.YARDS);
		System.out.println("10.0 Feet in Yards: " + lengthInYards.value);
		
		
		// Length Addition
		Quantity<LengthUnit> totalLength = lengthInFeet.add(lengthInInches, LengthUnit.INCHES);
		System.out.println("Addition of 10.0 Feet and 120.0 Inches are: " + totalLength.value + " " + totalLength.unit);
		
		
		// Weight Addition
		Quantity<WeightUnit> totalWeight = weightInKilograms.add(weightInGrams, WeightUnit.GRAM);
		System.out.println("Addition of 2.0 Kg and 2000.0 g is: " + totalWeight.value + " " + totalWeight.unit); 		
	}
}

