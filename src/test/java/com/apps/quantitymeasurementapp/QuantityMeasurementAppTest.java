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
}
