package com.apps.quantitymeasurementapp;

import com.apps.quantitymeasurementapp.Length.LengthUnit;

public class QuantityMeasurementApp {
	
	// Create a generic method to demonstrate Length equality check
	public static boolean demonstrateLengthEquality(Length length1, Length length2) {
		if(length1 == null || length2 == null) {
			return false;
		}
		
		return length1.equals(length2); 
	}
	
	
	// Create a static method to demonstrate Feet equality check
	public static void demonstrateFeetEquality() {
		Length length1 = new Length(2.0, LengthUnit.FEET); 
		Length length2 = new Length(2.0, LengthUnit.FEET); 
		
		System.out.println("Feet Equality: " + demonstrateLengthEquality(length1, length2));
	} 
	
	
	// Create a static method to demonstrate Inch equality check
	public static void demonstrateInchEquality() {
		Length length1 = new Length(12.0, LengthUnit.INCHES);
		Length length2 = new Length(12.0, LengthUnit.INCHES);
		
		System.out.println("Inches Equality: " + demonstrateLengthEquality(length1, length2));
	}
	
	
	// Create a static method to demonstrate Feet and Inches comparison
	public static void demonstrateFeetInchesComparison() {
		Length length1 = new Length(2.0, LengthUnit.FEET); 
		Length length2 = new Length(24.0, LengthUnit.INCHES);

		System.out.println("Feet-Inches Comparison: " + demonstrateLengthEquality(length1, length2) + "\n");
	}
	
	
	// Create a static method to demonstrate length comparison
	public static void demonstrateLengthComparison(double value1, LengthUnit unit1, double value2, LengthUnit unit2) {
		Length length1 = new Length(value1, unit1);
		Length length2 = new Length(value2, unit2);		
		
		System.out.println("Does " + value1 + " " + unit1 + " = " + value2 + " " + unit2 + "? " + length1.equals(length2));
	}
	
	
	// Demonstrate Length Comparison between two Length Instances
	public static boolean demonstrateLengthComparison(Length length1, Length length2) throws InvalidUnitMeasurementException {
		if(length1 == null || length2 == null) {
			throw new InvalidUnitMeasurementException("Length can't be null");
		}
		
		return length1.compareTo(length2) > 0; 
	}
	
	
	// Demonstrate Length Conversion from one Quantity Length instance to another Length unit
	public static Length demonstrateLengthConversion(Length length, LengthUnit toUnit) throws InvalidUnitMeasurementException {
		if(length == null) {
			throw new InvalidUnitMeasurementException("Length can't be null");
		}
		
		if(toUnit == null) {
			throw new InvalidUnitMeasurementException("Target unit can't be null");
		}
		
		return length.convertTo(toUnit); 
	}
	
	
	// Demonstrate length conversion from one QuantityLength instance to another unit
	public static Length demonstrateLengthConversion(Double val, LengthUnit unit1, LengthUnit unit2) throws InvalidUnitMeasurementException {
		
		if(val == null) {
			throw new InvalidUnitMeasurementException("Value can't be null");
		}
		if(unit1 == null || unit2 == null) {
			throw new InvalidUnitMeasurementException("UnitLength can't be null");
		}
		
		Length length = new Length(val, unit1);
		
		return length.convertTo(unit2); 
	}
	
	
	// Demonstrate addition of second QuantityLength to first QuantityLength
	public static Length demonstrateLengthAddition(Length length1, Length length2) {
		return length1.add(length2); 
	}
	
	public static void main(String args[]) {
		demonstrateFeetEquality();
		demonstrateInchEquality();
		demonstrateFeetInchesComparison();
		
		// Demonstrate Feet and Inches Comparison
		demonstrateLengthComparison(2.0, LengthUnit.FEET, 24.0, LengthUnit.INCHES);
		
		// Demonstrate Yards and Inches Comparison
		demonstrateLengthComparison(1.0, LengthUnit.YARDS, 36.0, LengthUnit.INCHES);

		// Demonstrate Centimeters and Inches Comparison
		demonstrateLengthComparison(100.0, LengthUnit.CENTIMETERS, 39.3701, LengthUnit.INCHES);
		
		// Demonstrate Feet and Yards Comparison
		demonstrateLengthComparison(6.0, LengthUnit.FEET, 2.0, LengthUnit.YARDS);
		
		// Demonstrate Centimeters and Feet Comparison
		demonstrateLengthEquality(new Length(100.0, LengthUnit.CENTIMETERS), new Length(3.28084, LengthUnit.FEET));
		
		
		// ------------- Length Conversion ------------
		
		try {
			// Convert FEET to INCHES
			System.out.println("\nConvert 1.0 Feet to Inches: " + demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES).getValue());
			
			// Convert YARDS to FEET
			System.out.println("Convert 3.0 Yards to Feet: " + demonstrateLengthConversion(3.0, LengthUnit.YARDS, LengthUnit.FEET).getValue());
			
			// Convert INCHES to YARDS
			System.out.println("Convert 36.0 Inches to Yards: " + demonstrateLengthConversion(36.0, LengthUnit.INCHES, LengthUnit.YARDS).getValue());
			
			//Convert CENTIMETERS to INCHES
			System.out.println("Convert 1.0 Centimeters to Inches: " + demonstrateLengthConversion(1.0, LengthUnit.CENTIMETERS, LengthUnit.INCHES).getValue());
			
			// Convert FEET to INCHES
			System.out.println("Convert 0.0 Feet to Inches: " + demonstrateLengthConversion(0.0, LengthUnit.FEET, LengthUnit.INCHES).getValue());
		} catch(InvalidUnitMeasurementException e) {
			System.out.println(e.getMessage());
		}
		
		
		// -------------- Add Length ---------------
		System.out.println("\n------------- Addition of two Length ------------");
		
		Length addTwoFeet = demonstrateLengthAddition(new Length(1.0, LengthUnit.FEET), new Length(2.0, LengthUnit.FEET)); 
		System.out.println("Adding 1.0 Feet and 2.0 Feet -> " + addTwoFeet);

		Length addInchToFeet = demonstrateLengthAddition(new Length(1.0, LengthUnit.FEET), new Length(12.0, LengthUnit.INCHES)); 
		System.out.println("Adding 1.0 Feet and 12.0 Inches -> " + addInchToFeet);
		
		Length addFeetToInches = demonstrateLengthAddition(new Length(12.0, LengthUnit.INCHES), new Length(1.0, LengthUnit.FEET)); 
		System.out.println("Adding 12.0 Inches and 1.0 Feet -> " + addFeetToInches);
		
		Length addFeetToYards = demonstrateLengthAddition(new Length(1.0, LengthUnit.YARDS), new Length(3.0, LengthUnit.FEET)); 
		System.out.println("Adding 1.0 Yards and 3.0 Feet -> " + addFeetToYards);
		
		Length addInchesToYards = demonstrateLengthAddition(new Length(36.0, LengthUnit.INCHES), new Length(1.0, LengthUnit.YARDS)); 
		System.out.println("Adding 36.0 Inches and 1.0 Yards -> " + addInchesToYards);
		
		Length addInchesToCentimeters = demonstrateLengthAddition(new Length(2.54, LengthUnit.CENTIMETERS), new Length(1.0, LengthUnit.INCHES)); 
		System.out.println("Adding 2.54 Centimeters and 1.0 Inches -> " + addInchesToCentimeters);
		
		Length addInchesToFeet = demonstrateLengthAddition(new Length(5.0, LengthUnit.FEET), new Length(0.0, LengthUnit.INCHES)); 
		System.out.println("Adding 5.0 Feet and 0.0 Inches -> " + addInchesToFeet);
		
		Length addTwoFeet2 = demonstrateLengthAddition(new Length(5.0, LengthUnit.FEET), new Length(-2.0, LengthUnit.FEET)); 
		System.out.println("Adding 5.0 Feet and -2.0 Feet -> " + addTwoFeet2); 		
	} 
}
