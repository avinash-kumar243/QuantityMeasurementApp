package com.apps.quantitymeasurementapp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.apps.quantitymeasurementapp.QuantityMeasurementApp.Feet;
import com.apps.quantitymeasurementapp.QuantityMeasurementApp.Inches;

public class QuantityMeasurementAppTest {
	
	Feet feet1 = new Feet(1.0);
	Feet feet2 = new Feet(1.0);
	Feet feet3 = new Feet(2.0);
	
	Inches inch1 = new Inches(1.0);
	Inches inch2 = new Inches(1.0);
	Inches inch3 = new Inches(2.0);
	
		
	@Test
	public void testFeetEquality_SameValue() {
		assertEquals(true, feet1.equals(feet2));
	}
	
	@Test
	public void testFeetEquality_DifferentValue() {
		assertEquals(false, feet2.equals(feet3));;
	}
	
	@Test
	public void testFeetEquality_NullComparision() {
		assertEquals(false, feet1.equals(null)); 
	}
	
	@Test
	public void testFeetEquality_NonNumericInput() {
		String non_numeric = "3.0";
		assertFalse(feet1.equals(non_numeric));
	}
	
	@Test
	public void testFeetEquality_SameReference() {
		assertTrue(feet1.equals(feet1));
	}
	
	@Test
	public void testInchesEquality_SameValue() {
		assertTrue(inch1.equals(inch2)); 
	}
	
	@Test
	public void testInchesEquality_DifferentValue() {
		assertFalse(inch1.equals(inch3));
	}
	
	@Test
	public void testInchesEquality_NullComparision() {
		assertFalse(inch1.equals(null));
	}
	
	@Test
	public void testInchesEquality_NonNumericInput() {
		String inch4 = "4.0";
		assertFalse(inch1.equals(inch4));
	}
	
	@Test
	public void testInchesEquality_SameReference() {
		assertTrue(inch1.equals(inch1)); 
	}
}