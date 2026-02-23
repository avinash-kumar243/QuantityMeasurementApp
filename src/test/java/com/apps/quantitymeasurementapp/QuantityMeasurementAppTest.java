package com.apps.quantitymeasurementapp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.apps.quantitymeasurementapp.Length.LengthUnit;

public class QuantityMeasurementAppTest {
	
//	------ Feet Equality ------
			
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
	
	
//	------ Inches Equality ------
	
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
	
	
//	------ Feet & Inches InEquality ------
	
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
	
	@Test  
	public void convertFeetToInches() throws InvalidUnitMeasurementException {
		Length lengthInInches = QuantityMeasurementApp.demonstrateLengthConversion(3.0, LengthUnit.FEET, LengthUnit.INCHES);
		Length expectedLength = new Length(36.0, LengthUnit.INCHES);
		
		assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(lengthInInches, expectedLength)); 
	}
	
	@Test
	public void convertYardsToInchesUsingOverloadedMethod() throws InvalidUnitMeasurementException {
		Length lengthInYards = new Length(2.0, LengthUnit.YARDS);
		Length lengthInInches = QuantityMeasurementApp.demonstrateLengthConversion(lengthInYards, LengthUnit.INCHES);
		
		Length expectedInches = new Length(72.0, LengthUnit.INCHES);
		assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(lengthInInches, expectedInches)); 
	}
	
	
	
	
	@Test
	public void testAddition_SameUnit_FeetPlusFeet() {
		Length feet1 = new Length(1.0, LengthUnit.FEET);
		Length feet2 = new Length(2.0, LengthUnit.FEET);
		Length expectedFeet = new Length(3.0, LengthUnit.FEET);
		
		assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(feet1.add(feet2), expectedFeet)); 
	}
	
	@Test
	public void testAddition_SameUnit_InchPlusInch() {
		Length inch1 = new Length(6.0, LengthUnit.INCHES);
		Length inch2 = new Length(6.0, LengthUnit.INCHES);
		Length expectedInch = new Length(12.0, LengthUnit.INCHES);
		
		assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(inch1.add(inch2), expectedInch));
	}
	
	@Test
	public void testAddition_CrossUnit_FeetPlusInches() {
		Length feet = new Length(1.0, LengthUnit.FEET);
		Length inch = new Length(12.0, LengthUnit.INCHES);
		Length expectedFeet = new Length(2.0, LengthUnit.FEET);
		
		assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(feet.add(inch), expectedFeet)); 
	}
	
	@Test
	public void testAddition_CrossUnit_InchPlusFeet() {
		Length inch = new Length(12.0, LengthUnit.INCHES);
		Length feet = new Length(1.0, LengthUnit.FEET);	
		Length expectedInch = new Length(24.0, LengthUnit.INCHES);
		
		assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(inch.add(feet), expectedInch)); 
	}
	
	@Test
	public void testAddition_CrossUnit_YardPlusFeet() {
		Length yard = new Length(1.0, LengthUnit.YARDS);
		Length feet = new Length(3.0, LengthUnit.FEET);
		Length expectedYard = new Length(2.0, LengthUnit.YARDS);
		
		assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(yard.add(feet), expectedYard)); 
	}
	
	@Test
	public void testAddition_CrossUnit_CentimeterPlusInch() {
		Length centimeter = new Length(2.54, LengthUnit.CENTIMETERS);
		Length inch = new Length(1.0, LengthUnit.INCHES);
		Length expectedCentimeter = new Length(5.07, LengthUnit.CENTIMETERS);
		
		assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(centimeter.add(inch), expectedCentimeter)); 
	}
	
	@Test
	public void testAddition_Commutativity() {
		Length inch1 = new Length(1.0, LengthUnit.INCHES);
		Length inch2 = new Length(2.0, LengthUnit.INCHES);
		Length expectedInch = new Length(3.0, LengthUnit.INCHES);
		
		assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(inch1.add(inch2), expectedInch)); 
	}
	
	@Test
	public void testAddition_WithZero() {
		Length feet = new Length(5.0, LengthUnit.FEET);
		Length inch = new Length(0.0, LengthUnit.INCHES);
		Length expectedFeet = new Length(5.0, LengthUnit.FEET);
		
		assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(feet.add(inch), expectedFeet)); 
	}
	
	@Test
	public void testAddition_NegativeValues() {
		Length feet1 = new Length(5.0, LengthUnit.FEET);
		Length feet2 = new Length(-2.0, LengthUnit.FEET);
		Length expectedFeet = new Length(3.0, LengthUnit.FEET);
		
		assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(feet1.add(feet2), expectedFeet)); 
	}
	
	@Test
	public void testAddition_NullSecondOperand() {
		assertThrows(IllegalArgumentException.class, ()-> {
			Length feet2 = new Length(2.0, null);
		});
	}
	
	@Test
	public void testAddition_LargeValues() {
		Length feet1 = new Length(1e6, LengthUnit.FEET);
		Length feet2 = new Length(1e6, LengthUnit.FEET);
		Length expectedFeet = new Length(2e6, LengthUnit.FEET);
		
		assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(feet1.add(feet2), expectedFeet)); 
	}
	
	@Test
	public void testAddition_SmallValues() {
		Length feet1 = new Length(0.001, LengthUnit.FEET);
		Length feet2 = new Length(0.002, LengthUnit.FEET);
		Length expectedFeet = new Length(0.03, LengthUnit.FEET);
		
		Length ouput = QuantityMeasurementApp.demonstrateLengthAddition(feet2, expectedFeet);
		assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(expectedFeet, ouput));  
	}
}
