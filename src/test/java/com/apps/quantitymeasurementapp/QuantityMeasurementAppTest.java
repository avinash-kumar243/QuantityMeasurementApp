package com.apps.quantitymeasurementapp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.apps.quantitymeasurementapp.Length.LengthUnit;

public class QuantityMeasurementAppTest {
			
	@Test
	public void testFeetEquality_SameValue() {
		Length feet1 = new Length(1.0, LengthUnit.FEET);
		Length feet2 = new Length(1.0, LengthUnit.FEET);
		assertTrue(feet1.equals(feet2));
	}
	
	@Test
	public void testFeetEquality_DifferentValue() {
		Length feet1 = new Length(1.0, LengthUnit.FEET);
		Length feet2 = new Length(2.0, LengthUnit.FEET);
		assertFalse(feet1.equals(feet2));;
	}
	
	@Test
	public void testFeetEquality_NullComparison() {
		Length feet1 = new Length(1.0, LengthUnit.FEET);
		assertFalse(feet1.equals(null)); 
	}
	
	@Test
	public void testFeetEquality_NonNumericInput() {
		Length feet1 = new Length(1.0, LengthUnit.FEET);
		assertFalse(feet1.equals("3.0"));
	}
	
	@Test
	public void testFeetEquality_SameReference() {
		Length feet1 = new Length(1.0, LengthUnit.FEET);
		assertTrue(feet1.equals(feet1));
	}
	
	@Test
	public void testInchesEquality_SameValue() {
		Length inch1 = new Length(12.0, LengthUnit.INCHES);
		Length inch2 = new Length(12.0, LengthUnit.INCHES);
		assertTrue(inch1.equals(inch2)); 
	}
	
	@Test
	public void testInchesEquality_DifferentValue() {
		Length inch1 = new Length(12.0, LengthUnit.INCHES);
		Length inch2 = new Length(24.0, LengthUnit.INCHES);
		assertFalse(inch1.equals(inch2));
	}
	
	@Test
	public void testInchesEquality_NullComparison() {
		Length inch1 = new Length(24.0, LengthUnit.INCHES);
		assertFalse(inch1.equals(null));
	}
	
	@Test
	public void testInchesEquality_NonNumericInput() {
		Length inch1 = new Length(24.0, LengthUnit.INCHES);
		assertFalse(inch1.equals("4.0"));
	}
	
	@Test
	public void testInchesEquality_SameReference() {
		Length inch1 = new Length(24.0, LengthUnit.INCHES);
		assertTrue(inch1.equals(inch1)); 
	}
	
	
	@Test
	public void testFeetInchesComparison() {
		Length feet1 = new Length(2.0, LengthUnit.FEET);
		Length inch1 = new Length(24.0, LengthUnit.INCHES);
		assertTrue(feet1.equals(inch1)); 
	}
	
	@Test
	public void testFeetInequality() {
		Length feet1 = new Length(1.0, LengthUnit.FEET);
		Length feet2 = new Length(2.0, LengthUnit.FEET);
		assertFalse(feet1.equals(feet2));
	}
	
	@Test
	public void testInchesInequality() {
		Length inch1 = new Length(12.0, LengthUnit.INCHES);
		Length inch2 = new Length(24.0, LengthUnit.INCHES);
		assertFalse(inch1.equals(inch2)); 
	}
	
	@Test
	public void testCrossUnitInequality() {
		Length feet1 = new Length(2.0, LengthUnit.FEET);
		Length inch1 = new Length(20.0, LengthUnit.INCHES);
		assertFalse(feet1.equals(inch1));
	}
	
	
//	--- Yards and Centimeters ----
	@Test
	public void yardEquals36Inches() {
		assertTrue(new Length(1.0, LengthUnit.YARDS).equals(new Length(36.0, LengthUnit.INCHES)));
	}
	
	@Test
	public void centimeterEquals39Point3701Inches() {
		assertTrue(new Length(100.0, LengthUnit.CENTIMETERS).equals(new Length(39.3701, LengthUnit.INCHES)));
	}
	
	@Test
	public void threeFeetEqualsOneYard() {
		assertTrue(new Length(3.0, LengthUnit.FEET).equals(new Length(1.0, LengthUnit.YARDS)));
	}
	
	@Test
	public void thirtyPoint48CmEqualsOneFoot() {
		assertTrue(new Length(30.48, LengthUnit.CENTIMETERS).equals(new Length(1.0, LengthUnit.FEET)));
	}
	
	@Test
	public void yardNotEqualToInches() {
		assertFalse(new Length(1, LengthUnit.YARDS).equals(new Length(24, LengthUnit.INCHES)));
	}
	
	@Test
	public void referenceEqualitySameObject() {
		Length length = new Length(12.0, LengthUnit.INCHES);
		assertTrue(length.equals(length));
	}
	
	@Test
	public void equalReturnsFalseForNull() {
		assertFalse(new Length(2.0, LengthUnit.CENTIMETERS).equals(null));
	}
	
	@Test
	public void reflexiveSymmetricAndTransitiveProperty() {
		Length length1 = new Length(1.0, LengthUnit.YARDS);
		Length length2 = new Length(3.0, LengthUnit.FEET);
		Length length3 = new Length(36.0, LengthUnit.INCHES);
		
		// Reflexive Property
		assertTrue(length1.equals(length2));
		
		// Symmetric Property
		assertTrue(length1.equals(length2));
		assertTrue(length2.equals(length1));
		
		// Transitive Property
		assertTrue(length1.equals(length2));
		assertTrue(length2.equals(length3));
		assertTrue(length3.equals(length1)); 		
	}
	
	@Test
	public void differentValuesSameUnitNotEqual() {
		// Feet
		assertFalse(new Length(5, LengthUnit.CENTIMETERS).equals(new Length(10, LengthUnit.CENTIMETERS))); 
		
		// Yards
		assertFalse(new Length(1, LengthUnit.YARDS).equals(new Length(2, LengthUnit.YARDS)));
		
		// Centimeters
		assertFalse(new Length(100, LengthUnit.CENTIMETERS).equals(new Length(200, LengthUnit.CENTIMETERS)));
	}
	
	@Test
	public void crossUnitEqualityDemonstrateMethod() {
		// Yards and Inches
		assertTrue(new Length(1, LengthUnit.YARDS).equals(new Length(36, LengthUnit.INCHES)));
		
		// Feet and Inches
		assertTrue(new Length(1, LengthUnit.FEET).equals(new Length(12, LengthUnit.INCHES)));

		// Inches and Centimeters
		assertTrue(new Length(1, LengthUnit.INCHES).equals(new Length(2.54, LengthUnit.CENTIMETERS)));

		// Yards and Centimeters
		assertTrue(new Length(1, LengthUnit.YARDS).equals(new Length(91.44, LengthUnit.CENTIMETERS)));
	}
}
