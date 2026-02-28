package com.apps.quantitymeasurementapp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {
	
//	------ Feet Equality ------
			
	@Test
	public void testFeetEquality_SameValue() {
		Quantity<LengthUnit> feet1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> feet2 = new Quantity<>(1.0, LengthUnit.FEET);
		assertTrue(feet1.equals(feet2));
	}
	
	@Test
	public void testFeetEquality_DifferentValue() {
		Quantity<LengthUnit> feet1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> feet2 = new Quantity<>(2.0, LengthUnit.FEET);
		assertFalse(feet1.equals(feet2));;
	}
	
	@Test
	public void testFeetEquality_NullComparison() {
		Quantity<LengthUnit> feet1 = new Quantity<>(1.0, LengthUnit.FEET);
		assertFalse(feet1.equals(null)); 
	}
	
	@Test
	public void testFeetEquality_NonNumericInput() {
		Quantity<LengthUnit> feet1 = new Quantity<>(1.0, LengthUnit.FEET);
		assertFalse(feet1.equals("3.0"));
	}
	
	@Test
	public void testFeetEquality_SameReference() {
		Quantity<LengthUnit> feet1 = new Quantity<>(1.0, LengthUnit.FEET);
		assertTrue(feet1.equals(feet1));
	}
	
	
//	------ Inches Equality ------
	
	@Test
	public void testInchesEquality_SameValue() {
		Quantity<LengthUnit> inch1 = new Quantity<>(12.0, LengthUnit.INCHES);
		Quantity<LengthUnit> inch2 = new Quantity<>(12.0, LengthUnit.INCHES);
		assertTrue(inch1.equals(inch2)); 
	}
	
	@Test
	public void testInchesEquality_DifferentValue() {
		Quantity<LengthUnit> inch1 = new Quantity<>(12.0, LengthUnit.INCHES);
		Quantity<LengthUnit> inch2 = new Quantity<>(24.0, LengthUnit.INCHES);
		assertFalse(inch1.equals(inch2));
	}
	
	@Test
	public void testInchesEquality_NullComparison() {
		Quantity<LengthUnit> inch1 = new Quantity<>(24.0, LengthUnit.INCHES);
		assertFalse(inch1.equals(null));
	}
	
	@Test
	public void testInchesEquality_NonNumericInput() {
		Quantity<LengthUnit> inch1 = new Quantity<>(24.0, LengthUnit.INCHES);
		assertFalse(inch1.equals("4.0"));
	}
	
	@Test
	public void testInchesEquality_SameReference() {
		Quantity<LengthUnit> inch1 = new Quantity<>(24.0, LengthUnit.INCHES);
		assertTrue(inch1.equals(inch1)); 
	}
	
	
//	------ Feet & Inches InEquality ------
	
	@Test
	public void testFeetInchesComparison() {
		Quantity<LengthUnit> feet1 = new Quantity<>(2.0, LengthUnit.FEET);
		Quantity<LengthUnit> inch1 = new Quantity<>(24.0, LengthUnit.INCHES);
		assertTrue(feet1.equals(inch1)); 
	}
	
	@Test
	public void testFeetInequality() {
		Quantity<LengthUnit> feet1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> feet2 = new Quantity<>(2.0, LengthUnit.FEET);
		assertFalse(feet1.equals(feet2));
	}
	
	@Test
	public void testInchesInequality() {
		Quantity<LengthUnit> inch1 = new Quantity<>(12.0, LengthUnit.INCHES);
		Quantity<LengthUnit> inch2 = new Quantity<>(24.0, LengthUnit.INCHES);
		assertFalse(inch1.equals(inch2)); 
	}
	
	@Test
	public void testCrossUnitInequality() {
		Quantity<LengthUnit> feet1 = new Quantity<>(2.0, LengthUnit.FEET);
		Quantity<LengthUnit> inch1 = new Quantity<>(20.0, LengthUnit.INCHES);
		assertFalse(feet1.equals(inch1));
	}
	
	
//	--- Yards and Centimeters ----
	@Test
	public void yardEquals36Inches() {
		assertTrue(new Quantity<LengthUnit>(1.0, LengthUnit.YARDS).equals(new Quantity<>(36.0, LengthUnit.INCHES)));
	}
	
	@Test
	public void centimeterEquals39Point3701Inches() {
		assertTrue(new Quantity<LengthUnit>(100.0, LengthUnit.CENTIMETERS).equals(new Quantity<>(39.3701, LengthUnit.INCHES)));
	}
	
	@Test
	public void threeFeetEqualsOneYard() {
		assertTrue(new Quantity<LengthUnit>(3.0, LengthUnit.FEET).equals(new Quantity<>(1.0, LengthUnit.YARDS)));
	}
	
	@Test
	public void thirtyPoint48CmEqualsOneFoot() {
		assertTrue(new Quantity<LengthUnit>(30.48, LengthUnit.CENTIMETERS).equals(new Quantity<>(1.0, LengthUnit.FEET)));
	}
	
	@Test
	public void yardNotEqualToInches() {
		assertFalse(new Quantity<>(1, LengthUnit.YARDS).equals(new Quantity<>(24, LengthUnit.INCHES)));
	}
	
	@Test
	public void referenceEqualitySameObject() {
		Quantity<LengthUnit> length = new Quantity<>(12.0, LengthUnit.INCHES);
		assertTrue(length.equals(length));
	}
	
	@Test
	public void equalReturnsFalseForNull() {
		assertFalse(new Quantity<>(2.0, LengthUnit.CENTIMETERS).equals(null));
	}
	
	@Test
	public void reflexiveSymmetricAndTransitiveProperty() {
		Quantity<LengthUnit> length1 = new Quantity<>(1.0, LengthUnit.YARDS);
		Quantity<LengthUnit> length2 = new Quantity<>(3.0, LengthUnit.FEET);
		Quantity<LengthUnit> length3 = new Quantity<>(36.0, LengthUnit.INCHES);
		
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
		assertFalse(new Quantity<>(5, LengthUnit.CENTIMETERS).equals(new Quantity<>(10, LengthUnit.CENTIMETERS))); 
		
		// Yards
		assertFalse(new Quantity<>(1, LengthUnit.YARDS).equals(new Quantity<>(2, LengthUnit.YARDS)));
		
		// Centimeters
		assertFalse(new Quantity<>(100, LengthUnit.CENTIMETERS).equals(new Quantity<>(200, LengthUnit.CENTIMETERS)));
	}
	
	@Test
	public void crossUnitEqualityDemonstrateMethod() {
		// Yards and Inches
		assertTrue(new Quantity<>(1, LengthUnit.YARDS).equals(new Quantity<>(36, LengthUnit.INCHES)));
		
		// Feet and Inches
		assertTrue(new Quantity<>(1, LengthUnit.FEET).equals(new Quantity<>(12, LengthUnit.INCHES)));

		// Inches and Centimeters
		assertTrue(new Quantity<>(1, LengthUnit.INCHES).equals(new Quantity<>(2.54, LengthUnit.CENTIMETERS)));

		// Yards and Centimeters
		assertTrue(new Quantity<>(1, LengthUnit.YARDS).equals(new Quantity<>(91.44, LengthUnit.CENTIMETERS)));
	}
	
	@Test  
	public void convertFeetToInches() throws InvalidUnitMeasurementException {
		Quantity<LengthUnit> lengthInInches = QuantityMeasurementApp.demonstrateConversion(new Quantity<>(3.0, LengthUnit.FEET), LengthUnit.INCHES);
		Quantity<LengthUnit> expectedLength = new Quantity<>(36.0, LengthUnit.INCHES);
		
		assertTrue(QuantityMeasurementApp.demonstrateEquality(lengthInInches, expectedLength)); 
	}
	
	@Test
	public void convertYardsToInchesUsingOverloadedMethod() throws InvalidUnitMeasurementException {
		Quantity<LengthUnit> lengthInYards = new Quantity<>(2.0, LengthUnit.YARDS);
		Quantity<LengthUnit> lengthInInches = QuantityMeasurementApp.demonstrateConversion(lengthInYards, LengthUnit.INCHES);
		
		Quantity<LengthUnit> expectedInches = new Quantity<>(72.0, LengthUnit.INCHES);
		assertTrue(QuantityMeasurementApp.demonstrateEquality(lengthInInches, expectedInches)); 
	}
	
	
	
	
	@Test
	public void testAddition_SameUnit_FeetPlusFeet() {
		Quantity<LengthUnit> feet1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> feet2 = new Quantity<>(2.0, LengthUnit.FEET);
		Quantity<LengthUnit> expectedFeet = new Quantity<>(3.0, LengthUnit.FEET);
		
		assertTrue(QuantityMeasurementApp.demonstrateEquality(feet1.add(feet2), expectedFeet)); 
	}
	
	@Test
	public void testAddition_SameUnit_InchPlusInch() {
		Quantity<LengthUnit> inch1 = new Quantity<>(6.0, LengthUnit.INCHES);
		Quantity<LengthUnit> inch2 = new Quantity<>(6.0, LengthUnit.INCHES);
		Quantity<LengthUnit> expectedInch = new Quantity<>(12.0, LengthUnit.INCHES);
		
		assertTrue(QuantityMeasurementApp.demonstrateEquality(inch1.add(inch2), expectedInch));
	}
	
	@Test
	public void testAddition_CrossUnit_FeetPlusInches() {
		Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> inch = new Quantity<>(12.0, LengthUnit.INCHES);
		Quantity<LengthUnit> expectedFeet = new Quantity<>(2.0, LengthUnit.FEET);
		
		assertTrue(QuantityMeasurementApp.demonstrateEquality(feet.add(inch), expectedFeet)); 
	}
	
	@Test
	public void testAddition_CrossUnit_InchPlusFeet() {
		Quantity<LengthUnit> inch = new Quantity<>(12.0, LengthUnit.INCHES);
		Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);	
		Quantity<LengthUnit> expectedInch = new Quantity<>(24.0, LengthUnit.INCHES);
		
		assertTrue(QuantityMeasurementApp.demonstrateEquality(inch.add(feet), expectedInch)); 
	}
	
	@Test
	public void testAddition_CrossUnit_YardPlusFeet() {
		Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARDS);
		Quantity<LengthUnit> feet = new Quantity<>(3.0, LengthUnit.FEET);
		Quantity<LengthUnit> expectedYard = new Quantity<>(2.0, LengthUnit.YARDS);
		
		assertTrue(QuantityMeasurementApp.demonstrateEquality(yard.add(feet), expectedYard)); 
	}
	
	@Test
	public void testAddition_CrossUnit_CentimeterPlusInch() {
		Quantity<LengthUnit> centimeter = new Quantity<>(2.54, LengthUnit.CENTIMETERS);
		Quantity<LengthUnit> inch = new Quantity<>(1.0, LengthUnit.INCHES);
		Quantity<LengthUnit> expectedCentimeter = new Quantity<>(5.07, LengthUnit.CENTIMETERS);
		
		assertTrue(QuantityMeasurementApp.demonstrateEquality(centimeter.add(inch), expectedCentimeter)); 
	}
	
	@Test
	public void testAddition_Commutativity() {
		Quantity<LengthUnit> inch1 = new Quantity<>(1.0, LengthUnit.INCHES);
		Quantity<LengthUnit> inch2 = new Quantity<>(2.0, LengthUnit.INCHES);
		Quantity<LengthUnit> expectedInch = new Quantity<>(3.0, LengthUnit.INCHES);
		
		assertTrue(QuantityMeasurementApp.demonstrateEquality(inch1.add(inch2), expectedInch)); 
	}
	
	@Test
	public void testAddition_WithZero() {
		Quantity<LengthUnit> feet = new Quantity<>(5.0, LengthUnit.FEET);
		Quantity<LengthUnit> inch = new Quantity<>(0.0, LengthUnit.INCHES);
		Quantity<LengthUnit> expectedFeet = new Quantity<>(5.0, LengthUnit.FEET);
		
		assertTrue(QuantityMeasurementApp.demonstrateEquality(feet.add(inch), expectedFeet)); 
	}
	
	@Test
	public void testAddition_NegativeValues() {
		Quantity<LengthUnit> feet1 = new Quantity<>(5.0, LengthUnit.FEET);
		Quantity<LengthUnit> feet2 = new Quantity<>(-2.0, LengthUnit.FEET);
		Quantity<LengthUnit> expectedFeet = new Quantity<>(3.0, LengthUnit.FEET);
		
		assertTrue(QuantityMeasurementApp.demonstrateEquality(feet1.add(feet2), expectedFeet)); 
	}
	
	@Test
	public void testAddition_NullSecondOperand() {
		assertThrows(IllegalArgumentException.class, ()-> {
			Quantity<LengthUnit> feet2 = new Quantity<>(2.0, null);
		});
	}
	
	@Test
	public void testAddition_LargeValues() {
		Quantity<LengthUnit> feet1 = new Quantity<>(1e6, LengthUnit.FEET);
		Quantity<LengthUnit> feet2 = new Quantity<>(1e6, LengthUnit.FEET);
		Quantity<LengthUnit> expectedFeet = new Quantity<>(2e6, LengthUnit.FEET);
		
		assertTrue(QuantityMeasurementApp.demonstrateEquality(feet1.add(feet2), expectedFeet)); 
	}
	
	@Test
	public void testAddition_SmallValues() {
		Quantity<LengthUnit> feet1 = new Quantity<>(0.001, LengthUnit.FEET);
		Quantity<LengthUnit> feet2 = new Quantity<>(0.002, LengthUnit.FEET);
		Quantity<LengthUnit> expectedFeet = new Quantity<>(0.003, LengthUnit.FEET);
		
		Quantity<LengthUnit> ouput = QuantityMeasurementApp.demonstrateAddition(feet1, feet2);
		assertTrue(QuantityMeasurementApp.demonstrateEquality(expectedFeet, ouput));  
	}
	
	
	@Test
	public void testAddition_ExplicitTargetUnit_Feet() {
		Quantity<LengthUnit> length1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> length2 = new Quantity<>(12.0, LengthUnit.INCHES);
		Quantity<LengthUnit> expectedResult = new Quantity<>(2.0, LengthUnit.FEET);

		assertEquals(QuantityMeasurementApp.demonstrateAddition(length1, length2), expectedResult);
	}
	
	@Test
	public void testAddition_ExplicitTargetUnit_Inches() {
		Quantity<LengthUnit> length1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> length2 = new Quantity<>(12.0, LengthUnit.INCHES);
		Quantity<LengthUnit> expectedResult = new Quantity<>(24.0, LengthUnit.INCHES);

		assertEquals(QuantityMeasurementApp.demonstrateAddition(length1, length2), expectedResult);
	}
	
	@Test
	public void testAddition_ExplicitTargetUnit_Yards() {
		Quantity<LengthUnit> length1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> length2 = new Quantity<>(12.0, LengthUnit.INCHES);
		Quantity<LengthUnit> expectedResult = new Quantity<>(0.667, LengthUnit.YARDS);

		assertEquals(QuantityMeasurementApp.demonstrateAddition(length1, length2, LengthUnit.YARDS), expectedResult);
	}
	
	@Test
	public void testAddition_ExplicitTargetUnit_Centimeters() {
		Quantity<LengthUnit> length1 = new Quantity<>(1.0, LengthUnit.INCHES);
		Quantity<LengthUnit> length2 = new Quantity<>(1.0, LengthUnit.INCHES);
		Quantity<LengthUnit> expectedResult = new Quantity<>(5.07, LengthUnit.CENTIMETERS);

		assertEquals(QuantityMeasurementApp.demonstrateAddition(length1, length2), expectedResult);
	}
	
	@Test
	public void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {
		Quantity<LengthUnit> length1 = new Quantity<>(2.0, LengthUnit.YARDS);
		Quantity<LengthUnit> length2 = new Quantity<>(3.0, LengthUnit.FEET);
		Quantity<LengthUnit> expectedResult = new Quantity<>(3.0, LengthUnit.YARDS);
 
		assertEquals(QuantityMeasurementApp.demonstrateAddition(length1, length2), expectedResult);
	}
	
	@Test
	public void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {
		Quantity<LengthUnit> length1 = new Quantity<>(2.0, LengthUnit.YARDS);
		Quantity<LengthUnit> length2 = new Quantity<>(3.0, LengthUnit.FEET);
		Quantity<LengthUnit> expectedResult = new Quantity<>(9.0, LengthUnit.FEET);

		assertEquals(QuantityMeasurementApp.demonstrateAddition(length1, length2), expectedResult);
	}
	
	@Test
	public void testAddition_ExplicitTargetUnit_Commutativity() {
		Quantity<LengthUnit> length1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> length2 = new Quantity<>(12.0, LengthUnit.INCHES);
		Quantity<LengthUnit> result1 = QuantityMeasurementApp.demonstrateAddition(length1, length2, LengthUnit.YARDS);

		Quantity<LengthUnit> length3 = new Quantity<>(12.0, LengthUnit.INCHES);
		Quantity<LengthUnit> length4 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> result2 = QuantityMeasurementApp.demonstrateAddition(length3, length4, LengthUnit.YARDS);

		assertEquals(result1, result2);
	}
	
	@Test
	public void testAddition_ExplicitTargetUnit_WithZero() {
		Quantity<LengthUnit> length1 = new Quantity<>(5.0, LengthUnit.FEET);
		Quantity<LengthUnit> length2 = new Quantity<>(0.0, LengthUnit.INCHES);
		Quantity<LengthUnit> expectedResult = new Quantity<>(1.667, LengthUnit.YARDS);

		assertEquals(QuantityMeasurementApp.demonstrateAddition(length1, length2, LengthUnit.YARDS), expectedResult);
	}
	
	@Test
	public void testAddition_ExplicitTargetUnit_NegativeValues() {
		Quantity<LengthUnit> length1 = new Quantity<>(5.0, LengthUnit.FEET);
		Quantity<LengthUnit> length2 = new Quantity<>(-2.0, LengthUnit.FEET);
		Quantity<LengthUnit> expectedResult = new Quantity<>(36.0, LengthUnit.INCHES);

		assertEquals(QuantityMeasurementApp.demonstrateAddition(length1, length2, LengthUnit.INCHES), expectedResult);
	}
	
	@Test
	public void testAddition_ExplicitTargetUnit_NullTargetUnit() {
		Quantity<LengthUnit> length1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> length2 = new Quantity<>(12.0, LengthUnit.INCHES);

		assertThrows(IllegalArgumentException.class, () -> {
			QuantityMeasurementApp.demonstrateAddition(length1, length2, null);
		});
	}
	
	@Test
	public void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
		Quantity<LengthUnit> length1 = new Quantity<>(1000.0, LengthUnit.FEET);
		Quantity<LengthUnit> length2 = new Quantity<>(500.0, LengthUnit.FEET);
		Quantity<LengthUnit> expectedResult = new Quantity<>(18000.0, LengthUnit.INCHES);

		assertEquals(QuantityMeasurementApp.demonstrateAddition(length1, length2, LengthUnit.INCHES), expectedResult);
	}
	
	@Test
	public void testAddition_ExplicitTargetUnit_SmallToLargeScale() {
		Quantity<LengthUnit> length1 = new Quantity<>(12.0, LengthUnit.INCHES);
		Quantity<LengthUnit> length2 = new Quantity<>(12.0, LengthUnit.INCHES);
		Quantity<LengthUnit> expectedResult = new Quantity<>(0.667, LengthUnit.YARDS);

		assertEquals(QuantityMeasurementApp.demonstrateAddition(length1, length2, LengthUnit.YARDS), expectedResult);
	}
	
	
	// ------------- UC8 Test Cases ---------------
	
	@Test
	public void testLengthUnitEnum_FeetConstant() {
		assertTrue(LengthUnit.FEET.getConversionFactor() == 1.0);
	}

	@Test
	public void testLengthUnitEnum_InchesConstant() {
		assertTrue(LengthUnit.INCHES.getConversionFactor() == 0.0833);
	}

	@Test
	public void testLengthUnitEnum_YardsConstant() {
		assertTrue(LengthUnit.YARDS.getConversionFactor() == 3.0);
	}

	@Test
	public void testLengthUnitEnum_CentimetersConstant() {
		assertTrue(LengthUnit.CENTIMETERS.getConversionFactor() == 0.0328);
	}

	@Test
	public void testConvertToBaseUnit_FeetToFeet() {
		assertTrue(LengthUnit.FEET.convertToBaseUnit(5.0) == 5.0);
	}

	@Test
	public void testConvertToBaseUnit_InchesToFeet() {
		assertTrue(LengthUnit.INCHES.convertToBaseUnit(12.0) == 1.0);
	}

	@Test
	public void testConvertToBaseUnit_YardsToFeet() {
		assertTrue(LengthUnit.YARDS.convertToBaseUnit(1.0) == 3.0);
	}

	@Test
	public void testConvertToBaseUnit_CentimetersToFeet() {
		assertTrue(LengthUnit.CENTIMETERS.convertToBaseUnit(30.48) == 1.0);
	}	

	@Test
	public void testConvertFromBaseUnit_FeetToFeet() {
		assertTrue(LengthUnit.FEET.convertFromBaseUnit(2.0) == 2.0);
	}
	
	@Test
	public void testConvertFromBaseUnit_FeetToInches() {
		assertTrue(LengthUnit.INCHES.convertFromBaseUnit(1.0) == 12.0);
	}

	@Test
	public void testConvertFromBaseUnit_FeetToYards() {
		assertTrue(LengthUnit.YARDS.convertFromBaseUnit(3.0) == 1.0);
	}

	@Test
	public void testConvertFromBaseUnit_FeetToCentimeters() {
		assertTrue(LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0) == 30.48);
	}

	@Test
	public void testQuantityLengthRefactored_Equality() {
		assertTrue(new Quantity<>(1.0, LengthUnit.FEET).equals(new Quantity<>(12.0, LengthUnit.INCHES)));
	}

	@Test
	public void testQuantityLengthRefactored_ConvertTo() {
		Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> expectedResult = new Quantity<>(1.0, LengthUnit.FEET);

		assertEquals(length.convertTo(LengthUnit.INCHES), expectedResult);
	}

	@Test
	public void testQuantityLengthRefactored_Add() {
		Quantity<LengthUnit> length1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> length2 = new Quantity<>(12.0, LengthUnit.INCHES);
		Quantity<LengthUnit> expectedResult = new Quantity<>(2.0, LengthUnit.FEET);

		assertEquals(length1.add(length2), expectedResult);
	}

	@Test
	public void testQuantityLengthRefactored_AddWithTargetUnit() {
		Quantity<LengthUnit> length1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> length2 = new Quantity<>(12.0, LengthUnit.INCHES);
		Quantity<LengthUnit> expectedResult = new Quantity<>(0.667, LengthUnit.YARDS);

		assertEquals(length1.add(length2, LengthUnit.YARDS), expectedResult);
	}
	
	@Test
	public void testQuantityLengthRefactored_NullUnit() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Quantity<>(1.0, null);
		});
	}

	@Test
	public void testQuantityLengthRefactored_InvalidValue() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Quantity<>(Double.NaN, LengthUnit.FEET);
		});
	}

	@Test
	public void testQuantityLengthRefactored_UC1EqualityTests() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Quantity<>(Double.NaN, LengthUnit.FEET);
		});
	}
	
	
	
	// ------------------ UC9 Test Cases -------------------
	
	@Test
	public void testEquality_KilogramToKilogram_SameValue() {
		Quantity<WeightUnit> kilogram1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> kilogram2 = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		assertTrue(QuantityMeasurementApp.demonstrateEquality(kilogram1, kilogram2));
	}
	
	@Test
	public void testEquality_KilogramToKilogram_DifferentValue() {
		Quantity<WeightUnit> kilogram1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> kilogram2 = new Quantity<>(2.0, WeightUnit.KILOGRAM);

		assertFalse(QuantityMeasurementApp.demonstrateEquality(kilogram1, kilogram2));
	}
	
	@Test
	public void testEquality_KilogramToKilogram_EquivalentValue() {
		Quantity<WeightUnit> kilogram = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> gram = new Quantity<>(1000.0, WeightUnit.GRAM);

		assertTrue(QuantityMeasurementApp.demonstrateEquality(kilogram, gram));
	}
	
	@Test
	public void testEquality_GramToKilogram_EquivalentValue() {
		Quantity<WeightUnit> gram = new Quantity<>(1000.0, WeightUnit.GRAM);
		Quantity<WeightUnit> kilogram = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		assertTrue(QuantityMeasurementApp.demonstrateEquality(gram, kilogram));
	}
		
	@Test
	public void testEquality_NullComparison() {
		Quantity<WeightUnit> kilogram = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		assertThrows(IllegalArgumentException.class, () -> {
			QuantityMeasurementApp.demonstrateEquality(kilogram, null);
		});
		 
	}
	
	@Test
	public void testEquality_SameReference() {
		Quantity<WeightUnit> kilogram = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		assertTrue(QuantityMeasurementApp.demonstrateEquality(kilogram, kilogram));
	}
	
	@Test
	public void testEquality_NullUnit() {
		assertThrows(IllegalArgumentException.class, () -> {
			Quantity<WeightUnit> kilogram = new Quantity<>(1.0, null);
		});
	}
	
	@Test
	public void testEquality_TransitiveProperty() {
		Quantity<WeightUnit> kilogram1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> gram = new Quantity<>(1000.0, WeightUnit.GRAM);
		Quantity<WeightUnit> kilogram2 = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		assertTrue(QuantityMeasurementApp.demonstrateEquality(kilogram1, gram));
		assertTrue(QuantityMeasurementApp.demonstrateEquality(gram, kilogram2));
		assertTrue(QuantityMeasurementApp.demonstrateEquality(kilogram2, kilogram1));		
	}
	
	@Test
	public void testEquality_ZeroValue() {
		Quantity<WeightUnit> kilogram = new Quantity<>(0.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> gram = new Quantity<>(0.0, WeightUnit.GRAM);
		assertTrue(QuantityMeasurementApp.demonstrateEquality(kilogram, gram));
	}
	
	@Test
	public void testEquality_NegativeWeight() {
		Quantity<WeightUnit> kilogram = new Quantity<>(-1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> gram = new Quantity<>(-1000.0, WeightUnit.GRAM);

		assertTrue(QuantityMeasurementApp.demonstrateEquality(kilogram, gram));
	}
	
	@Test
	public void testEquality_LargeWeightValue() {
		Quantity<WeightUnit> gram = new Quantity<>(1000000.0, WeightUnit.GRAM);
		Quantity<WeightUnit> kilogram = new Quantity<>(1000.0, WeightUnit.KILOGRAM);

		assertTrue(QuantityMeasurementApp.demonstrateEquality(gram, kilogram));
	}
	
	@Test
	public void testEquality_SmallWeightValue() {
		Quantity<WeightUnit> kilogram = new Quantity<>(0.001, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> gram = new Quantity<>(1.0, WeightUnit.GRAM);

		assertTrue(QuantityMeasurementApp.demonstrateEquality(kilogram, gram));
	}
	
	
	
	
	
	@Test
    public void testConversion_PoundToKilogram() {
		Quantity<WeightUnit> result = QuantityMeasurementApp.demonstrateConversion(new Quantity<>(2.20462, WeightUnit.POUND), WeightUnit.KILOGRAM);
        assertEquals(result, new Quantity<>(1.0, WeightUnit.KILOGRAM));
    }

    @Test
    public void testConversion_KilogramToPound() {
    	Quantity<WeightUnit> result = QuantityMeasurementApp.demonstrateConversion(new Quantity<>(1.0, WeightUnit.KILOGRAM), WeightUnit.POUND);
        assertEquals(result, new Quantity<>(2.20462, WeightUnit.POUND));
    }

    @Test
    public void testConversion_SameUnit() {
    	Quantity<WeightUnit> result = QuantityMeasurementApp.demonstrateConversion(new Quantity<>(5.0, WeightUnit.KILOGRAM), WeightUnit.KILOGRAM);
    	Quantity<WeightUnit> expected = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertEquals(expected, result);
    }

    @Test
    public void testConversion_ZeroValue() {
    	Quantity<WeightUnit> result = QuantityMeasurementApp.demonstrateConversion(new Quantity<>(0.0, WeightUnit.KILOGRAM), WeightUnit.GRAM);
    	Quantity<WeightUnit> expected = new Quantity<>(0.0, WeightUnit.GRAM);

        assertEquals(expected, result);
    }

    @Test
    public void testConversion_NegativeValue() {
    	Quantity<WeightUnit> result = QuantityMeasurementApp.demonstrateConversion(new Quantity<>(-1.0, WeightUnit.KILOGRAM), WeightUnit.GRAM);
    	Quantity<WeightUnit> expected = new Quantity<>(-1000.0, WeightUnit.GRAM);

        assertEquals(expected, result);
    }

    @Test
    public void testConversion_RoundTrip() {
    	Quantity<WeightUnit> result = QuantityMeasurementApp.demonstrateConversion(new Quantity<>(1.5, WeightUnit.KILOGRAM), WeightUnit.GRAM).convertTo(WeightUnit.KILOGRAM);
    	Quantity<WeightUnit> expected = new Quantity<>(1.5, WeightUnit.KILOGRAM);

        assertEquals(expected, result);
    } 

    // ------------------ ADDITION TESTS ------------------

    @Test
    public void testAddition_SameUnit_KilogramPlusKilogram() {
    	Quantity<WeightUnit> result = QuantityMeasurementApp.demonstrateAddition(
                new Quantity<>(1.0, WeightUnit.KILOGRAM),
                new Quantity<>(2.0, WeightUnit.KILOGRAM)
        );

    	Quantity<WeightUnit> expected = new Quantity<>(3.0, WeightUnit.KILOGRAM);

        assertEquals(expected, result);
    }

    @Test
    public void testAddition_CrossUnit_KilogramPlusGram() {
    	Quantity<WeightUnit> result = QuantityMeasurementApp.demonstrateAddition(
                new Quantity<>(1.0, WeightUnit.KILOGRAM),
                new Quantity<>(1000.0, WeightUnit.GRAM)
        );

    	Quantity<WeightUnit> expected = new Quantity<>(2.0, WeightUnit.KILOGRAM);

        assertEquals(expected, result);
    }

    @Test
    public void testAddition_CrossUnit_PoundPlusKilogram() {
    	Quantity<WeightUnit> result = QuantityMeasurementApp.demonstrateAddition(
                new Quantity<>(2.20462, WeightUnit.POUND),
                new Quantity<>(1.0, WeightUnit.KILOGRAM)
        );

    	Quantity<WeightUnit> expected = new Quantity<>(4.40924, WeightUnit.POUND);

        assertEquals(expected, result);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Kilogram() {
    	Quantity<WeightUnit> result = QuantityMeasurementApp.demonstrateAddition(
                new Quantity<>(1.0, WeightUnit.KILOGRAM),
                new Quantity<>(1000.0, WeightUnit.GRAM),
                WeightUnit.GRAM
        );

    	Quantity<WeightUnit> expected = new Quantity<>(2000.0, WeightUnit.GRAM);

        assertEquals(expected, result); 
    }

    @Test
    public void testAddition_Commutativity2() {
    	Quantity<WeightUnit> result1 = QuantityMeasurementApp.demonstrateAddition(
                new Quantity<>(1.0, WeightUnit.KILOGRAM),
                new Quantity<>(1000.0, WeightUnit.GRAM)
        );

    	Quantity<WeightUnit> result2 = QuantityMeasurementApp.demonstrateAddition(
                new Quantity<>(1000.0, WeightUnit.GRAM),
                new Quantity<>(1.0, WeightUnit.KILOGRAM)
        );

        assertEquals(result1, result2.convertTo(result1.getUnit()));
    }

    @Test
    public void testAddition_WithZero2() {
    	Quantity<WeightUnit> result = QuantityMeasurementApp.demonstrateAddition(
                new Quantity<>(5.0, WeightUnit.KILOGRAM),
                new Quantity<>(0.0, WeightUnit.GRAM)
        );

    	Quantity<WeightUnit> expected = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertEquals(expected, result);
    }

    @Test
    public void testAddition_NegativeValues2() {
    	Quantity<WeightUnit> result = QuantityMeasurementApp.demonstrateAddition(
                new Quantity<>(5.0, WeightUnit.KILOGRAM),
                new Quantity<>(-2000.0, WeightUnit.GRAM)
        );

    	Quantity<WeightUnit> expected = new Quantity<>(3.0, WeightUnit.KILOGRAM);

        assertEquals(expected, result);
    }

    @Test
    public void testAddition_LargeValues2() {
    	Quantity<WeightUnit> result = QuantityMeasurementApp.demonstrateAddition(
                new Quantity<>(1e6, WeightUnit.KILOGRAM),
                new Quantity<>(1e6, WeightUnit.KILOGRAM)
        );

    	Quantity<WeightUnit> expected = new Quantity<>(2e6, WeightUnit.KILOGRAM);

        assertEquals(expected, result);
    }
    
    
    
    // ------------------ Volume Quantity Test Cases ------------------
    
    
    // =====================================================
    // Equality Tests
    // =====================================================

    @Test
    @DisplayName("Litre equals Litre (same value)")
    void testEquality_LitreToLitre_SameValue() {
        assertTrue(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(1.0, VolumeUnit.LITRE)));
    }

    @Test
    void testEquality_LitreToLitre_DifferentValue() {
        assertFalse(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(2.0, VolumeUnit.LITRE)));
    }

    @Test
    void testEquality_LitreToMillilitre_EquivalentValue() {
        assertTrue(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)));
    }

    @Test
    void testEquality_MillilitreToLitre_EquivalentValue() {
        assertTrue(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                .equals(new Quantity<>(1.0, VolumeUnit.LITRE)));
    }

    @Test
    void testEquality_LitreToGallon_EquivalentValue() {
        assertTrue(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(0.264172, VolumeUnit.GALLON)));
    }

    @Test
    void testEquality_GallonToLitre_EquivalentValue() {
        assertTrue(new Quantity<>(1.0, VolumeUnit.GALLON)
                .equals(new Quantity<>(3.78541, VolumeUnit.LITRE)));
    }

    @Test
    void testEquality_VolumeVsLength_Incompatible() {
        assertFalse(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(1.0, LengthUnit.FEET)));
    }

    @Test
    void testEquality_VolumeVsWeight_Incompatible() {
        assertFalse(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(1.0, WeightUnit.KILOGRAM)));
    }

    @Test
    void testEquality_NullComparisonLitre() {
        assertFalse(new Quantity<>(1.0, VolumeUnit.LITRE).equals(null));
    }

    @Test
    void testEquality_SameReferenceVolume() {
        Quantity<VolumeUnit> q = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertTrue(q.equals(q));
    }

    @Test
    void testEquality_NullUnit_Volume() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null));
    }

    @Test
    void testEquality_ZeroValue_Volume() {
        assertTrue(new Quantity<>(0.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(0.0, VolumeUnit.MILLILITRE)));
    }

    @Test
    void testEquality_NegativeVolume() {
        assertTrue(new Quantity<>(-1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(-1000.0, VolumeUnit.MILLILITRE)));
    }

    @Test
    void testEquality_LargeVolumeValue() {
        assertTrue(new Quantity<>(1_000_000.0, VolumeUnit.MILLILITRE)
                .equals(new Quantity<>(1000.0, VolumeUnit.LITRE)));
    }

    @Test
    void testEquality_SmallVolumeValue() {
        assertTrue(new Quantity<>(0.001, VolumeUnit.LITRE)
                .equals(new Quantity<>(1.0, VolumeUnit.MILLILITRE)));
    }

    // =====================================================
    // Conversion Tests
    // =====================================================

    @Test
    void testConversion_LitreToMillilitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .convertTo(VolumeUnit.MILLILITRE);

        assertEquals(1000.0, result.getValue());
    }

    @Test
    void testConversion_GallonToLitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.GALLON)
                        .convertTo(VolumeUnit.LITRE);

        assertEquals(3.78541, result.getValue());
    }

    @Test
    void testConversion_RoundTrip_Volume() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.5, VolumeUnit.LITRE)
                        .convertTo(VolumeUnit.MILLILITRE)
                        .convertTo(VolumeUnit.LITRE);

        assertEquals(1.5, result.getValue());
    }

    // =====================================================
    // Addition Tests
    // =====================================================

    @Test
    void testAddition_SameUnit_LitrePlusLitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(2.0, VolumeUnit.LITRE));

        assertEquals(3.0, result.getValue());
    }

    @Test
    void testAddition_CrossUnit_LitrePlusMillilitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE));

        assertEquals(2.0, result.getValue());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Millilitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE),
                                VolumeUnit.MILLILITRE);

        assertEquals(2000.0, result.getValue());
    }

    @Test
    void testAddition_WithZero_Volume() {
        Quantity<VolumeUnit> result =
                new Quantity<>(5.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(0.0, VolumeUnit.MILLILITRE));

        assertEquals(5.0, result.getValue());
    }

    @Test
    void testAddition_NegativeValues_Volume() {
        Quantity<VolumeUnit> result =
                new Quantity<>(5.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(-2000.0, VolumeUnit.MILLILITRE));

        assertEquals(3.0, result.getValue());
    }

    // =====================================================
    // Enum Tests
    // =====================================================

    @Test
    void testVolumeUnitEnum_LitreConstant() {
        assertEquals(1.0, VolumeUnit.LITRE.getConversionFactor());
    }

    @Test
    void testVolumeUnitEnum_MillilitreConstant() {
        assertEquals(0.001, VolumeUnit.MILLILITRE.getConversionFactor());
    }

    @Test
    void testVolumeUnitEnum_GallonConstant() {
        assertEquals(3.7854, VolumeUnit.GALLON.getConversionFactor());
    }

}
