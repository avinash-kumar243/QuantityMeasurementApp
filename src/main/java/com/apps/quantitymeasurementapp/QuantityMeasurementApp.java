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

		System.out.println("Feet-Inches Comparison: " + demonstrateLengthEquality(length1, length2));
	}
	
	public static void main(String args[]) {
		demonstrateFeetEquality();
		demonstrateInchEquality();
		demonstrateFeetInchesComparison();
	}
}
