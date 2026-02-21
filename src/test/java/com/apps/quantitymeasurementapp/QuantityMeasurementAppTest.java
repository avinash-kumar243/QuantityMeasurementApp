package com.apps.quantitymeasurementapp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.apps.quantitymeasurementapp.QuantityMeasurementApp.Feet;

public class QuantityMeasurementAppTest {
	
	Feet feet1 = new Feet(1.0);
	Feet feet2 = new Feet(1.0);
	Feet feet3 = new Feet(2.0);
	
	
	@Test
	public void testFeetEquality_sameValue() {
		assertEquals(true, feet1.equals(feet2));
	}
	
	@Test
	public void estEquality_DifferentValue() {
		assertEquals(false, feet2.equals(feet3));;
	}
	
	@Test
	public void testEquality_NullComparision() {
		assertEquals(false, feet1.equals(null)); 
	}
	
	@Test
	public void testEquality_NonNumericInput() {
		String non_numeric = "3.0";
	
		assertFalse(feet1.equals(non_numeric));
	}
	
	@Test
	public void testEquality_SameReference() {
		assertTrue(feet1.equals(feet1));
	}
}