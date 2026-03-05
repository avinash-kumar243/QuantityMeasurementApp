package com.apps.quantitymeasurementapp;

import java.util.function.DoubleBinaryOperator;

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
		validateArithmeticOperands(other, this.unit, true); 
		return new Quantity<>(performArithmetic(other, this.unit, ArithmeticOperation.ADD), this.unit);  
	}
	
	// add (., .)
	public Quantity<U> add(Quantity<U> other, U targetUnit) {
		validateArithmeticOperands(other, targetUnit, true); 
		return new Quantity<>(performArithmetic(other, targetUnit, ArithmeticOperation.ADD), targetUnit);  
	}
	
	
	@Override
	public String toString() {
		return value + " " + unit;
	}
	
	
	
	// --------------- subtract() ------------------
	public Quantity<U> subtract(Quantity<U> other) {
		validateArithmeticOperands(other, this.unit, true); 
		return new Quantity<>(performArithmetic(other, this.unit, ArithmeticOperation.SUBTRACT), this.unit);  
	}
	
	
	public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
		validateArithmeticOperands(other, targetUnit, true); 
		return new Quantity<>(performArithmetic(other, targetUnit, ArithmeticOperation.SUBTRACT), targetUnit);  
	}
	
	
	// ------------------ Divide() -------------------
	public double divide(Quantity<U> other) {
		validateArithmeticOperands(other, this.unit, true); 
		return performArithmetic(other, this.unit, ArithmeticOperation.DIVIDE);  
	}
	
	public double divide(Quantity<U> other, U targetUnit) { 
		if(other.getValue() == 0.0) {
			throw new ArithmeticException("Divisor must be not zero!!!");
		}
		
		
		validateArithmeticOperands(other, targetUnit, true); 
		return performArithmetic(other, targetUnit, ArithmeticOperation.DIVIDE);  
	} 
	 
		
	private void validateArithmeticOperands(Quantity<U> other, U targetUnit, boolean targetUnitRequired) {
		if(other == null) {
	        throw new IllegalArgumentException("Other quantity cannot be null");
	    }

	    if(this.unit == null || other.unit == null) {
	        throw new IllegalArgumentException("Unit cannot be null"); 
	    }

	    if(!this.unit.getClass().equals(other.unit.getClass())) {
	        throw new IllegalArgumentException("Unit types are not compatible");
	    }

	    if(!Double.isFinite(this.value) || !Double.isFinite(other.value)) {
	        throw new IllegalArgumentException("Values must be finite numbers");
	    }

	    if(targetUnitRequired && targetUnit == null) {
	        throw new IllegalArgumentException("Target unit is required");
	    }

	    if(targetUnit != null && !this.unit.getClass().equals(targetUnit.getClass())) {
	        throw new IllegalArgumentException("Target unit type is not compatible");
	    }
	}
	
	enum ArithmeticOperation {
		
		ADD((a, b) -> a + b),
		SUBTRACT((a, b) -> a - b),
		DIVIDE((a, b) -> {
			if(b == 0.0) throw new ArithmeticException("Cannot divide by zero!!!");
			return a / b;
		});
		
		private final DoubleBinaryOperator operation;
		
		ArithmeticOperation(DoubleBinaryOperator operation) {
			this.operation = operation;
		}
		
		public double compute(double a, double b) {
			return operation.applyAsDouble(a, b); 
		}
	}
	
	private double performArithmetic(Quantity<U> other, U targetUnit, ArithmeticOperation operation) {
		boolean targetUnitRequired = operation != ArithmeticOperation.DIVIDE;

	    validateArithmeticOperands(other, targetUnit, targetUnitRequired);

	    double thisBase = this.unit.convertToBaseUnit(this.value);
	    double thatBase = other.unit.convertToBaseUnit(other.value);

	    double resultBase = operation.compute(thisBase, thatBase);

	    if(operation == ArithmeticOperation.DIVIDE) {
	        return resultBase;
	    }

	    return targetUnit.convertFromBaseUnit(resultBase);
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
		
		
		// Subtraction and division
		Quantity<WeightUnit> weight1 = new Quantity<WeightUnit>(2000, WeightUnit.GRAM);
		Quantity<WeightUnit> weight2 = new Quantity<WeightUnit>(3000, WeightUnit.GRAM);
		Quantity<VolumeUnit> volume1 = new Quantity<VolumeUnit>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> volume2 = new Quantity<VolumeUnit>(2.0, VolumeUnit.LITRE);

		
		System.out.println(weight2.subtract(weight1));
		System.out.println(weight1.divide(weight2));
		System.out.println(volume2.subtract(volume1));
		System.out.println(volume2.divide(volume1));

		
	}
}

