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
	}
}
