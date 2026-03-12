package com.apps.quantitymeasurementapp;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.apps.quantitymeasurementapp.controller.QuantityMeasurementController;
import com.apps.quantitymeasurementapp.entity.QuantityDTO;
import com.apps.quantitymeasurementapp.entity.QuantityMeasurementEntity;
import com.apps.quantitymeasurementapp.entity.QuantityModel;
import com.apps.quantitymeasurementapp.exception.InvalidUnitMeasurementException;
import com.apps.quantitymeasurementapp.quantity.Quantity;
import com.apps.quantitymeasurementapp.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurementapp.repository.QuantityMeasurementCacheRepository;
import com.apps.quantitymeasurementapp.service.IQuantityMeasurementService;
import com.apps.quantitymeasurementapp.service.QuantityMeasurementServiceImpl;
import com.apps.quantitymeasurementapp.unit.*;

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
		Quantity<LengthUnit> lengthInInches = new Quantity<>(3.0, LengthUnit.FEET).convertTo(LengthUnit.INCHES);
		Quantity<LengthUnit> expectedLength = new Quantity<>(36.0, LengthUnit.INCHES);
		
		assertTrue(lengthInInches.equals(expectedLength)); 
	}
	
	@Test
	public void convertYardsToInchesUsingOverloadedMethod() throws InvalidUnitMeasurementException {
		Quantity<LengthUnit> lengthInYards = new Quantity<>(2.0, LengthUnit.YARDS);
		Quantity<LengthUnit> lengthInInches = lengthInYards.convertTo(LengthUnit.INCHES);
		
		Quantity<LengthUnit> expectedInches = new Quantity<>(72.0, LengthUnit.INCHES);
		assertTrue(lengthInInches.equals(expectedInches)); 
	}
	
	
	
	
	@Test
	public void testAddition_SameUnit_FeetPlusFeet() {
		Quantity<LengthUnit> feet1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> feet2 = new Quantity<>(2.0, LengthUnit.FEET);
		Quantity<LengthUnit> expectedFeet = new Quantity<>(3.0, LengthUnit.FEET);
		
		assertTrue(feet1.add(feet2).equals(expectedFeet)); 
	}
	
	@Test
	public void testAddition_SameUnit_InchPlusInch() {
		Quantity<LengthUnit> inch1 = new Quantity<>(6.0, LengthUnit.INCHES);
		Quantity<LengthUnit> inch2 = new Quantity<>(6.0, LengthUnit.INCHES);
		Quantity<LengthUnit> expectedInch = new Quantity<>(12.0, LengthUnit.INCHES);
		
		assertTrue(inch1.add(inch2).equals(expectedInch));
	}
	
	@Test
	public void testAddition_CrossUnit_FeetPlusInches() {
		Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> inch = new Quantity<>(12.0, LengthUnit.INCHES);
		Quantity<LengthUnit> expectedFeet = new Quantity<>(2.0, LengthUnit.FEET);
		
		assertTrue(feet.add(inch).equals(expectedFeet)); 
	}
	
	@Test
	public void testAddition_CrossUnit_InchPlusFeet() {
		Quantity<LengthUnit> inch = new Quantity<>(12.0, LengthUnit.INCHES);
		Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);	
		Quantity<LengthUnit> expectedInch = new Quantity<>(24.0, LengthUnit.INCHES);
		
		assertTrue(inch.add(feet).equals(expectedInch)); 
	}
	
	@Test
	public void testAddition_CrossUnit_YardPlusFeet() {
		Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARDS);
		Quantity<LengthUnit> feet = new Quantity<>(3.0, LengthUnit.FEET);
		Quantity<LengthUnit> expectedYard = new Quantity<>(2.0, LengthUnit.YARDS);
		
		assertTrue(yard.add(feet).equals(expectedYard)); 
	}
	
	@Test
	public void testAddition_CrossUnit_CentimeterPlusInch() {
		Quantity<LengthUnit> centimeter = new Quantity<>(2.54, LengthUnit.CENTIMETERS);
		Quantity<LengthUnit> inch = new Quantity<>(1.0, LengthUnit.INCHES);
		Quantity<LengthUnit> expectedCentimeter = new Quantity<>(5.07, LengthUnit.CENTIMETERS);
		
		assertTrue(centimeter.add(inch).equals(expectedCentimeter)); 
	}
	
	@Test
	public void testAddition_Commutativity() {
		Quantity<LengthUnit> inch1 = new Quantity<>(1.0, LengthUnit.INCHES);
		Quantity<LengthUnit> inch2 = new Quantity<>(2.0, LengthUnit.INCHES);
		Quantity<LengthUnit> expectedInch = new Quantity<>(3.0, LengthUnit.INCHES);
		
		assertTrue(inch1.add(inch2).equals(expectedInch)); 
	}
	
	@Test
	public void testAddition_WithZero() {
		Quantity<LengthUnit> feet = new Quantity<>(5.0, LengthUnit.FEET);
		Quantity<LengthUnit> inch = new Quantity<>(0.0, LengthUnit.INCHES);
		Quantity<LengthUnit> expectedFeet = new Quantity<>(5.0, LengthUnit.FEET);
		
		assertTrue(feet.add(inch).equals(expectedFeet)); 
	}
	
	@Test
	public void testAddition_NegativeValues() {
		Quantity<LengthUnit> feet1 = new Quantity<>(5.0, LengthUnit.FEET);
		Quantity<LengthUnit> feet2 = new Quantity<>(-2.0, LengthUnit.FEET);
		Quantity<LengthUnit> expectedFeet = new Quantity<>(3.0, LengthUnit.FEET);
		
		assertTrue(feet1.add(feet2).equals(expectedFeet)); 
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
		
		assertTrue(feet1.add(feet2).equals(expectedFeet)); 
	}
	
	@Test
	public void testAddition_SmallValues() {
		Quantity<LengthUnit> feet1 = new Quantity<>(0.001, LengthUnit.FEET);
		Quantity<LengthUnit> feet2 = new Quantity<>(0.002, LengthUnit.FEET);
		Quantity<LengthUnit> expectedFeet = new Quantity<>(0.003, LengthUnit.FEET);
		
		Quantity<LengthUnit> ouput = feet1.add(feet2);
		assertTrue(expectedFeet.equals(ouput));  
	}
	
	
	@Test
	public void testAddition_ExplicitTargetUnit_Feet() {
		Quantity<LengthUnit> length1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> length2 = new Quantity<>(12.0, LengthUnit.INCHES);
		Quantity<LengthUnit> expectedResult = new Quantity<>(2.0, LengthUnit.FEET);

		assertEquals(length1.add(length2), expectedResult);
	}
	
	@Test
	public void testAddition_ExplicitTargetUnit_Inches() {
		Quantity<LengthUnit> length1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> length2 = new Quantity<>(12.0, LengthUnit.INCHES);
		Quantity<LengthUnit> expectedResult = new Quantity<>(24.0, LengthUnit.INCHES);

		assertEquals(length1.add(length2, LengthUnit.INCHES), expectedResult);
	}
	
	@Test
	public void testAddition_ExplicitTargetUnit_Yards() {
		Quantity<LengthUnit> length1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> length2 = new Quantity<>(12.0, LengthUnit.INCHES);
		Quantity<LengthUnit> expectedResult = new Quantity<>(0.667, LengthUnit.YARDS);

		assertEquals(length1.add(length2, LengthUnit.YARDS), expectedResult);
	}
	
	@Test
	public void testAddition_ExplicitTargetUnit_Centimeters() {
		Quantity<LengthUnit> length1 = new Quantity<>(1.0, LengthUnit.INCHES);
		Quantity<LengthUnit> length2 = new Quantity<>(1.0, LengthUnit.INCHES);
		Quantity<LengthUnit> expectedResult = new Quantity<>(5.07, LengthUnit.CENTIMETERS);

		assertEquals(length1.add(length2, LengthUnit.CENTIMETERS), expectedResult);
	}
	
	@Test
	public void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {
		Quantity<LengthUnit> length1 = new Quantity<>(2.0, LengthUnit.YARDS);
		Quantity<LengthUnit> length2 = new Quantity<>(3.0, LengthUnit.FEET);
		Quantity<LengthUnit> expectedResult = new Quantity<>(3.0, LengthUnit.YARDS);
 
		assertEquals(length1.add(length2), expectedResult);
	}
	
	@Test
	public void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {
		Quantity<LengthUnit> length1 = new Quantity<>(2.0, LengthUnit.YARDS);
		Quantity<LengthUnit> length2 = new Quantity<>(3.0, LengthUnit.FEET);
		Quantity<LengthUnit> expectedResult = new Quantity<>(9.0, LengthUnit.FEET);

		assertEquals(length1.add(length2), expectedResult);
	}
	
	@Test
	public void testAddition_ExplicitTargetUnit_Commutativity() {
		Quantity<LengthUnit> length1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> length2 = new Quantity<>(12.0, LengthUnit.INCHES);
		Quantity<LengthUnit> result1 = length1.add(length2, LengthUnit.YARDS);

		Quantity<LengthUnit> length3 = new Quantity<>(12.0, LengthUnit.INCHES);
		Quantity<LengthUnit> length4 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> result2 = length3.add(length4, LengthUnit.YARDS);

		assertEquals(result1, result2);
	}
	
	@Test
	public void testAddition_ExplicitTargetUnit_WithZero() {
		Quantity<LengthUnit> length1 = new Quantity<>(5.0, LengthUnit.FEET);
		Quantity<LengthUnit> length2 = new Quantity<>(0.0, LengthUnit.INCHES);
		Quantity<LengthUnit> expectedResult = new Quantity<>(1.667, LengthUnit.YARDS);

		assertEquals((length1.add(length2, LengthUnit.YARDS)), expectedResult);
	}
	
	@Test
	public void testAddition_ExplicitTargetUnit_NegativeValues() {
		Quantity<LengthUnit> length1 = new Quantity<>(5.0, LengthUnit.FEET);
		Quantity<LengthUnit> length2 = new Quantity<>(-2.0, LengthUnit.FEET);
		Quantity<LengthUnit> expectedResult = new Quantity<>(36.0, LengthUnit.INCHES);

		assertEquals((length1.add(length2, LengthUnit.INCHES)), expectedResult);
	}
	
	@Test
	public void testAddition_ExplicitTargetUnit_NullTargetUnit() {
		Quantity<LengthUnit> length1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> length2 = new Quantity<>(12.0, LengthUnit.INCHES);

		assertThrows(IllegalArgumentException.class, () -> {
			length1.add(length2, null);
		});
	}
	
	@Test
	public void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
		Quantity<LengthUnit> length1 = new Quantity<>(1000.0, LengthUnit.FEET);
		Quantity<LengthUnit> length2 = new Quantity<>(500.0, LengthUnit.FEET);
		Quantity<LengthUnit> expectedResult = new Quantity<>(18000.0, LengthUnit.INCHES);

		assertEquals((length1.add(length2, LengthUnit.INCHES)), expectedResult);
	}
	
	@Test
	public void testAddition_ExplicitTargetUnit_SmallToLargeScale() {
		Quantity<LengthUnit> length1 = new Quantity<>(12.0, LengthUnit.INCHES);
		Quantity<LengthUnit> length2 = new Quantity<>(12.0, LengthUnit.INCHES);
		Quantity<LengthUnit> expectedResult = new Quantity<>(0.667, LengthUnit.YARDS);

		assertEquals((length1.add(length2, LengthUnit.YARDS)), expectedResult); 
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

		assertTrue(kilogram1.equals(kilogram2));
	}
	
	@Test
	public void testEquality_KilogramToKilogram_DifferentValue() {
		Quantity<WeightUnit> kilogram1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> kilogram2 = new Quantity<>(2.0, WeightUnit.KILOGRAM);

		assertFalse(kilogram1.equals(kilogram2));
	}
	
	@Test
	public void testEquality_KilogramToKilogram_EquivalentValue() {
		Quantity<WeightUnit> kilogram = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> gram = new Quantity<>(1000.0, WeightUnit.GRAM);

		assertTrue(kilogram.equals(gram));
	}
	
	@Test
	public void testEquality_GramToKilogram_EquivalentValue() {
		Quantity<WeightUnit> gram = new Quantity<>(1000.0, WeightUnit.GRAM);
		Quantity<WeightUnit> kilogram = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		assertTrue(gram.equals(kilogram));
	}
		
	@Test
	public void testEquality_NullComparison() {
		Quantity<WeightUnit> kilogram = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		assertFalse(kilogram.equals(null));
		 
	}
	
	@Test
	public void testEquality_SameReference() {
		Quantity<WeightUnit> kilogram = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		assertTrue(kilogram.equals(kilogram));
	}
	
	@Test
	public void testEquality_NullUnit() {
		Quantity<WeightUnit> kilogram = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		assertFalse(kilogram.equals(null));
	}
	
	@Test
	public void testEquality_TransitiveProperty() {
		Quantity<WeightUnit> kilogram1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> gram = new Quantity<>(1000.0, WeightUnit.GRAM);
		Quantity<WeightUnit> kilogram2 = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		assertTrue(kilogram1.equals(gram));
		assertTrue(gram.equals(kilogram2));
		assertTrue(kilogram2.equals(kilogram1)); 	
	}
	
	@Test
	public void testEquality_ZeroValue() {
		Quantity<WeightUnit> kilogram = new Quantity<>(0.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> gram = new Quantity<>(0.0, WeightUnit.GRAM);
		assertTrue(kilogram.equals(gram));
	}
	
	@Test
	public void testEquality_NegativeWeight() {
		Quantity<WeightUnit> kilogram = new Quantity<>(-1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> gram = new Quantity<>(-1000.0, WeightUnit.GRAM);

		assertTrue(kilogram.equals(gram)); 
	}
	
	@Test
	public void testEquality_LargeWeightValue() {
		Quantity<WeightUnit> gram = new Quantity<>(1000000.0, WeightUnit.GRAM);
		Quantity<WeightUnit> kilogram = new Quantity<>(1000.0, WeightUnit.KILOGRAM);

		assertTrue(gram.equals(kilogram));
	}
	
	@Test
	public void testEquality_SmallWeightValue() {
		Quantity<WeightUnit> kilogram = new Quantity<>(0.001, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> gram = new Quantity<>(1.0, WeightUnit.GRAM);

		assertTrue(kilogram.equals(gram)); 
	}
	
	
	@Test
    public void testConversion_PoundToKilogram() {
		Quantity<WeightUnit> result = new Quantity<>(2.20462, WeightUnit.POUND).convertTo(WeightUnit.KILOGRAM); 
        assertEquals(result, new Quantity<>(1.0, WeightUnit.KILOGRAM));
    }

    @Test
    public void testConversion_KilogramToPound() {
    	Quantity<WeightUnit> result = new Quantity<>(1.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.POUND); 
        assertEquals(result, new Quantity<>(2.20462, WeightUnit.POUND));
    }

    @Test
    public void testConversion_SameUnit() {
    	Quantity<WeightUnit> result = new Quantity<>(5.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.KILOGRAM); 
    	Quantity<WeightUnit> expected = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertEquals(expected, result);
    }

    @Test
    public void testConversion_ZeroValue() {
    	Quantity<WeightUnit> result = new Quantity<>(0.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.GRAM); 
    	Quantity<WeightUnit> expected = new Quantity<>(0.0, WeightUnit.GRAM);

        assertEquals(expected, result);
    }

    @Test
    public void testConversion_NegativeValue() {
    	Quantity<WeightUnit> result = new Quantity<>(-1.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.GRAM);
    	Quantity<WeightUnit> expected = new Quantity<>(-1000.0, WeightUnit.GRAM);

        assertEquals(expected, result);
    }

    @Test
    public void testConversion_RoundTrip() {
    	Quantity<WeightUnit> result = new Quantity<>(1.5, WeightUnit.KILOGRAM).convertTo(WeightUnit.GRAM).convertTo(WeightUnit.KILOGRAM);
    	Quantity<WeightUnit> expected = new Quantity<>(1.5, WeightUnit.KILOGRAM); 

        assertEquals(expected, result);
    } 

    // ------------------ ADDITION TESTS ------------------

    @Test
    public void testAddition_SameUnit_KilogramPlusKilogram() {
    	Quantity<WeightUnit> result = new Quantity<>(1.0, WeightUnit.KILOGRAM).add(new Quantity<>(2.0, WeightUnit.KILOGRAM));
    	Quantity<WeightUnit> expected = new Quantity<>(3.0, WeightUnit.KILOGRAM);

        assertEquals(expected, result);
    }

    @Test
    public void testAddition_CrossUnit_KilogramPlusGram() {
    	Quantity<WeightUnit> result = new Quantity<>(1.0, WeightUnit.KILOGRAM).add(new Quantity<>(1000.0, WeightUnit.GRAM));
    	Quantity<WeightUnit> expected = new Quantity<>(2.0, WeightUnit.KILOGRAM);

        assertEquals(expected, result);
    }

    @Test
    public void testAddition_CrossUnit_PoundPlusKilogram() {
    	Quantity<WeightUnit> result = new Quantity<>(2.20462, WeightUnit.POUND).add(new Quantity<>(1.0, WeightUnit.KILOGRAM));
    	Quantity<WeightUnit> expected = new Quantity<>(4.40924, WeightUnit.POUND);

        assertEquals(expected, result);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Kilogram() {
    	Quantity<WeightUnit> result = new Quantity<>(1.0, WeightUnit.KILOGRAM).add(new Quantity<>(1000.0, WeightUnit.GRAM), WeightUnit.GRAM);
    	Quantity<WeightUnit> expected = new Quantity<>(2000.0, WeightUnit.GRAM);

        assertEquals(expected, result); 
    }

    @Test
    public void testAddition_Commutativity2() {
    	Quantity<WeightUnit> result1 = new Quantity<>(1.0, WeightUnit.KILOGRAM).add(new Quantity<>(1000.0, WeightUnit.GRAM));
    	Quantity<WeightUnit> result2 = new Quantity<>(1000.0, WeightUnit.GRAM).add(new Quantity<>(1.0, WeightUnit.KILOGRAM));

        assertEquals(result1, result2.convertTo(result1.getUnit()));
    }

    @Test
    public void testAddition_WithZero2() {
    	Quantity<WeightUnit> result = new Quantity<>(5.0, WeightUnit.KILOGRAM).add(new Quantity<>(0.0, WeightUnit.GRAM));

    	Quantity<WeightUnit> expected = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertEquals(expected, result);
    }

    @Test
    public void testAddition_NegativeValues2() {
    	Quantity<WeightUnit> result = new Quantity<>(5.0, WeightUnit.KILOGRAM).add(new Quantity<>(-2000.0, WeightUnit.GRAM));

    	Quantity<WeightUnit> expected = new Quantity<>(3.0, WeightUnit.KILOGRAM);

        assertEquals(expected, result);
    }

    @Test
    public void testAddition_LargeValues2() {
    	Quantity<WeightUnit> result = new Quantity<>(1e6, WeightUnit.KILOGRAM).add(new Quantity<>(1e6, WeightUnit.KILOGRAM));
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

    
    
    
    
    @Test
    void testSubtraction_SameUnit_FeetMinusFeet() {
        Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(5.0, LengthUnit.FEET));

        assertEquals(new Quantity<>(5.0, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_SameUnit_LitreMinusLitre() {
    	Quantity<VolumeUnit> result = new Quantity<>(10.0, VolumeUnit.LITRE)
                .subtract(new Quantity<>(3.0, VolumeUnit.LITRE));

        assertEquals(new Quantity<>(7.0, VolumeUnit.LITRE), result);
    }

    @Test
    void testSubtraction_CrossUnit_FeetMinusInches() {
    	Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(6.0, LengthUnit.INCHES));

        assertEquals(new Quantity<>(9.5, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_CrossUnit_InchesMinusFeet() {
    	Quantity<LengthUnit> result = new Quantity<>(120.0, LengthUnit.INCHES)
                .subtract(new Quantity<>(5.0, LengthUnit.FEET));

        assertEquals(new Quantity<>(60.0, LengthUnit.INCHES), result);
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Feet() {
    	Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(6.0, LengthUnit.INCHES), LengthUnit.FEET);

        assertEquals(new Quantity<>(9.5, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Inches() {
    	Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(6.0, LengthUnit.INCHES), LengthUnit.INCHES);

        assertEquals(new Quantity<>(114.0, LengthUnit.INCHES), result);
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Millilitre() {
    	Quantity<VolumeUnit> result = new Quantity<>(5.0, VolumeUnit.LITRE)
                .subtract(new Quantity<>(2.0, VolumeUnit.LITRE), VolumeUnit.MILLILITRE);

        assertEquals(new Quantity<>(3000.0, VolumeUnit.MILLILITRE), result);
    }

    @Test
    void testSubtraction_ResultingInNegative() {
    	Quantity<LengthUnit> result = new Quantity<>(5.0, LengthUnit.FEET)
                .subtract(new Quantity<>(10.0, LengthUnit.FEET));

        assertEquals(new Quantity<>(-5.0, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_ResultingInZero() {
    	Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(120.0, LengthUnit.INCHES));

        assertEquals(new Quantity<>(0.0, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_WithZeroOperand() {
    	Quantity<LengthUnit> result = new Quantity<>(5.0, LengthUnit.FEET)
                .subtract(new Quantity<>(0.0, LengthUnit.INCHES));

        assertEquals(new Quantity<>(5.0, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_WithNegativeValues() {
    	Quantity<LengthUnit> result = new Quantity<>(5.0, LengthUnit.FEET)
                .subtract(new Quantity<>(-2.0, LengthUnit.FEET));

        assertEquals(new Quantity<>(7.0, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_NonCommutative() {
    	Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
    	Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);

    	Quantity<LengthUnit> result1 = a.subtract(b);
    	Quantity<LengthUnit> result2 = b.subtract(a);

        assertNotEquals(result1, result2);
    }

    @Test
    void testSubtraction_WithLargeValues() {
    	Quantity<WeightUnit> result = new Quantity<>(1e6, WeightUnit.KILOGRAM)
                .subtract(new Quantity<>(5e5, WeightUnit.KILOGRAM));

        assertEquals(new Quantity<>(5e5, WeightUnit.KILOGRAM), result);
    }
    
    @Test
    void testSubtraction_NullOperand() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity<>(10.0, LengthUnit.FEET).subtract(null);
        });
    }

    @Test
    void testSubtraction_NullTargetUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity<>(10.0, LengthUnit.FEET)
                    .subtract(new Quantity<>(5.0, LengthUnit.FEET), null);
        });
    }

    @Test
    void testSubtraction_ChainedOperations() {
    	Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(2.0, LengthUnit.FEET))
                .subtract(new Quantity<>(1.0, LengthUnit.FEET));

        assertEquals(new Quantity<>(7.0, LengthUnit.FEET), result);
    }

    @Test
    void testDivision_SameUnit_FeetDividedByFeet() {
        double result = new Quantity<>(10.0, LengthUnit.FEET)
                .divide(new Quantity<>(2.0, LengthUnit.FEET));

        assertEquals(5.0, result);
    }

    @Test
    void testDivision_SameUnit_LitreDividedByLitre() {
        double result = new Quantity<>(10.0, VolumeUnit.LITRE)
                .divide(new Quantity<>(5.0, VolumeUnit.LITRE));

        assertEquals(2.0, result);
    }

    @Test
    void testDivision_CrossUnit_FeetDividedByInches() {
        double result = new Quantity<>(24.0, LengthUnit.INCHES)
                .divide(new Quantity<>(2.0, LengthUnit.FEET));

        assertEquals(1.0, result);
    }

    @Test
    void testDivision_RatioLessThanOne() {
        double result = new Quantity<>(5.0, LengthUnit.FEET)
                .divide(new Quantity<>(10.0, LengthUnit.FEET));

        assertEquals(0.5, result);
    }

    @Test
    void testDivision_RatioEqualToOne() {
        double result = new Quantity<>(10.0, LengthUnit.FEET)
                .divide(new Quantity<>(10.0, LengthUnit.FEET));

        assertEquals(1.0, result); 
    }

    @Test
    void testDivision_ByZero() {
        assertThrows(ArithmeticException.class, () -> {
            new Quantity<>(10.0, LengthUnit.FEET)
                    .divide(new Quantity<>(0.0, LengthUnit.FEET));
        });
    }

    @Test
    void testDivision_NullOperand() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity<>(10.0, LengthUnit.FEET).divide(null);
        });
    }

    @Test
    void testDivision_Immutability() {
    	Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
    	Quantity<LengthUnit> b = new Quantity<>(2.0, LengthUnit.FEET);

        a.divide(b);

        assertEquals(10.0, a.getValue());
        assertEquals(2.0, b.getValue());
    }
    
    
     
    
    @Test
    void testRefactoring_Add_DelegatesViaHelper() {
        Quantity<LengthUnit> q1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.add(q2);

        assertEquals(2.0, result.getValue());
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testRefactoring_Subtract_DelegatesViaHelper() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.subtract(q2);

        assertEquals(9.5, result.getValue());
    }

    @Test
    void testRefactoring_Divide_DelegatesViaHelper() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2, LengthUnit.FEET);

        double result = q1.divide(q2);

        assertEquals(5.0, result);
    }

    @Test
    void testValidation_NullOperand_ConsistentAcrossOperations() {
        Quantity<LengthUnit> q = new Quantity<>(10, LengthUnit.FEET);

        Exception e1 = assertThrows(IllegalArgumentException.class, () -> q.add(null));
        Exception e2 = assertThrows(IllegalArgumentException.class, () -> q.subtract(null));
        Exception e3 = assertThrows(IllegalArgumentException.class, () -> q.divide(null));

        assertEquals(e1.getMessage(), e2.getMessage());
        assertEquals(e2.getMessage(), e3.getMessage());
    }

   
    
//    @Test
//    void testArithmeticOperation_Add_EnumComputation() {
//        double result = ArithmeticOperation.ADD.compute(10, 5);
//        assertEquals(15.0, result);
//    }
//
//    @Test
//    void testArithmeticOperation_Subtract_EnumComputation() {
//        double result = ArithmeticOperation.SUBTRACT.compute(10, 5);
//        assertEquals(5.0, result);
//    }
//
//    @Test
//    void testArithmeticOperation_Divide_EnumComputation() {
//        double result = ArithmeticOperation.DIVIDE.compute(10, 5);
//        assertEquals(2.0, result);
//    }
//
//    @Test
//    void testArithmeticOperation_DivideByZero_EnumThrows() {
//        assertThrows(ArithmeticException.class,
//                () -> ArithmeticOperation.DIVIDE.compute(10, 0));
//    }

    @Test
    void testAdd_UC12_BehaviorPreserved() {
        Quantity<LengthUnit> q1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.add(q2);

        assertEquals(2.0, result.getValue());
    }

    @Test
    void testSubtract_UC12_BehaviorPreserved() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.subtract(q2);

        assertEquals(9.5, result.getValue());
    }

    @Test
    void testDivide_UC12_BehaviorPreserved() {
        Quantity<LengthUnit> q1 = new Quantity<>(24, LengthUnit.INCHES);
        Quantity<LengthUnit> q2 = new Quantity<>(2, LengthUnit.FEET);

        double result = q1.divide(q2);

        assertEquals(1.0, result);
    }

    @Test
    void testRounding_AddSubtract_TwoDecimalPlaces() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.123, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(1.123, LengthUnit.FEET);

        Quantity<LengthUnit> result = q1.add(q2);

        assertEquals(2.246, result.getValue());
    }

    @Test
    void testRounding_Divide_NoRounding() {
        Quantity<LengthUnit> q1 = new Quantity<>(5, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(3, LengthUnit.FEET);

        double result = q1.divide(q2);

        assertEquals(1.6666666666666667, result);
    }

    @Test
    void testImplicitTargetUnit_AddSubtract() {
        Quantity<LengthUnit> q1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.add(q2);

        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testExplicitTargetUnit_AddSubtract_Overrides() {
        Quantity<LengthUnit> q1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.add(q2, LengthUnit.INCHES);

        assertEquals(24.0, result.getValue());
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    void testImmutability_AfterAdd_ViaCentralizedHelper() {
        Quantity<LengthUnit> q1 = new Quantity<>(5, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5, LengthUnit.FEET);

        q1.add(q2);

        assertEquals(5, q1.getValue());
        assertEquals(5, q2.getValue());
    }

    @Test
    void testImmutability_AfterSubtract_ViaCentralizedHelper() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5, LengthUnit.FEET);

        q1.subtract(q2);

        assertEquals(10, q1.getValue());
        assertEquals(5, q2.getValue());
    }

    @Test
    void testImmutability_AfterDivide_ViaCentralizedHelper() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2, LengthUnit.FEET);

        q1.divide(q2);

        assertEquals(10, q1.getValue());
        assertEquals(2, q2.getValue());
    }

    @Test
    void testArithmetic_Chain_Operations() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5, LengthUnit.FEET);
        Quantity<LengthUnit> q3 = new Quantity<>(3, LengthUnit.FEET);

        double result = q1.add(q2).subtract(q3).divide(q2);

        assertEquals(2.4, result);
    }
    
    
    private QuantityMeasurementServiceImpl service;
    private QuantityMeasurementCacheRepository repository;

    @BeforeEach
    void setUp() {
        repository = new QuantityMeasurementCacheRepository();
        service = new QuantityMeasurementServiceImpl(repository);
    }

    // ----------------------------------------------------
    // DTO TESTS
    // ----------------------------------------------------

    @Test
    void testQuantityDTO_Constructor_WithIMeasurable() {
        QuantityDTO dto = new QuantityDTO(1.0, LengthUnit.FEET);

        assertEquals(1.0, dto.getValue());
        assertEquals("FEET", dto.getUnit());
        assertEquals("LengthUnit", dto.getMeasurementType());
    }

    @Test
    void testQuantityDTO_Constructor_WithStrings() {
        QuantityDTO dto = new QuantityDTO(2.0, "GRAM", "WeightUnit");

        assertEquals(2.0, dto.getValue());
        assertEquals("GRAM", dto.getUnit());
        assertEquals("WeightUnit", dto.getMeasurementType());
    }

    @Test
    void testQuantityDTO_ToString() {
        QuantityDTO dto = new QuantityDTO(1.0, "LITRE", "VolumeUnit");
        String text = dto.toString();

        assertTrue(text.contains("1.0"));
        assertTrue(text.contains("LITRE"));
        assertTrue(text.contains("VolumeUnit"));
    }

    // ----------------------------------------------------
    // MODEL TESTS
    // ----------------------------------------------------

    @Test
    void testQuantityModel_Construction() {
        QuantityModel<IMeasurable> model = new QuantityModel<>(5.0, LengthUnit.INCHES);

        assertEquals(5.0, model.getValue());
        assertEquals(LengthUnit.INCHES, model.getUnit());
    }

    @Test
    void testQuantityModel_ToString() {
        QuantityModel<IMeasurable> model = new QuantityModel<>(10.0, WeightUnit.KILOGRAM);
        String text = model.toString();

        assertTrue(text.contains("10.0"));
        assertTrue(text.contains("KILOGRAM"));
    }

    // ----------------------------------------------------
    // SERVICE - COMPARE
    // ----------------------------------------------------

    @Test
    void testService_CompareEquality_SameUnit_Success() {
        QuantityDTO q1 = new QuantityDTO(1.0, LengthUnit.FEET);
        QuantityDTO q2 = new QuantityDTO(1.0, LengthUnit.FEET);

        boolean result = service.compare(q1, q2);

        assertTrue(result);
    }

    @Test
    void testService_CompareEquality_DifferentUnit_Success() {
        QuantityDTO q1 = new QuantityDTO(1.0, LengthUnit.FEET);
        QuantityDTO q2 = new QuantityDTO(12.0, LengthUnit.INCHES);

        boolean result = service.compare(q1, q2);

        assertTrue(result);
    }

    @Test
    void testService_CompareEquality_CrossCategory_Error() {
        QuantityDTO q1 = new QuantityDTO(1.0, LengthUnit.FEET);
        QuantityDTO q2 = new QuantityDTO(1.0, VolumeUnit.LITRE);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> service.compare(q1, q2));

        assertEquals("Operation not possible for two different measurement types!!!", ex.getMessage());
    }

    // ----------------------------------------------------
    // SERVICE - CONVERT
    // ----------------------------------------------------

    @Test
    void testService_Convert_Success() {
        QuantityDTO source = new QuantityDTO(1.0, LengthUnit.FEET);
        QuantityDTO target = new QuantityDTO(0.0, LengthUnit.INCHES);

        QuantityDTO result = service.convert(source, target);

        assertEquals(12.0, result.getValue(), 0.01);
        assertEquals("INCHES", result.getUnit());
        assertEquals("LengthUnit", result.getMeasurementType());
    }

    // ----------------------------------------------------
    // SERVICE - ADD
    // ----------------------------------------------------


    @Test
    void testService_Add_WithTargetUnit_Success() {
        QuantityDTO q1 = new QuantityDTO(1.0, LengthUnit.FEET);
        QuantityDTO q2 = new QuantityDTO(12.0, LengthUnit.INCHES);
        QuantityDTO target = new QuantityDTO(0.0, LengthUnit.INCHES);

        QuantityDTO result = service.add(q1, q2, target);

        assertEquals(24.0, result.getValue(), 0.01);
        assertEquals("INCHES", result.getUnit());
        assertEquals("LengthUnit", result.getMeasurementType());
    }

    @Test
    void testService_Add_CrossCategory_Error() {
        QuantityDTO q1 = new QuantityDTO(1.0, LengthUnit.FEET);
        QuantityDTO q2 = new QuantityDTO(1.0, WeightUnit.KILOGRAM);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> service.add(q1, q2));

        assertEquals("Operation not possible for two different measurement types!!!", ex.getMessage());
    }

    // ----------------------------------------------------
    // SERVICE - SUBTRACT
    // ----------------------------------------------------


    @Test
    void testService_Subtract_WithTargetUnit_Success() {
        QuantityDTO q1 = new QuantityDTO(5.0, WeightUnit.KILOGRAM);
        QuantityDTO q2 = new QuantityDTO(2000.0, WeightUnit.GRAM);
        QuantityDTO target = new QuantityDTO(0.0, WeightUnit.GRAM);

        QuantityDTO result = service.subtract(q1, q2, target);

        assertEquals(3000.0, result.getValue(), 0.01);
        assertEquals("GRAM", result.getUnit());
        assertEquals("WeightUnit", result.getMeasurementType());
    }

    // ----------------------------------------------------
    // SERVICE - DIVIDE
    // ----------------------------------------------------

    @Test
    void testService_Divide_Success() {
        QuantityDTO q1 = new QuantityDTO(2.0, VolumeUnit.LITRE);
        QuantityDTO q2 = new QuantityDTO(1000.0, VolumeUnit.MILLILITRE);

        QuantityDTO result = service.divide(q1, q2);

        assertEquals(2.0, result.getValue(), 0.01);
        assertEquals("LITRE", result.getUnit());
        assertEquals("VolumeUnit", result.getMeasurementType());
    }

    @Test
    void testService_Divide_ByZero_Error() {
        QuantityDTO q1 = new QuantityDTO(10.0, LengthUnit.FEET);
        QuantityDTO q2 = new QuantityDTO(0.0, LengthUnit.FEET);

        ArithmeticException ex = assertThrows(ArithmeticException.class,
                () -> service.divide(q1, q2));

        assertEquals("Cannot divide by zero!!!", ex.getMessage());
    }

    // ----------------------------------------------------
    // SERVICE - VALIDATION
    // ----------------------------------------------------

    @Test
    void testService_NullEntity_Rejection() {
        QuantityDTO q = new QuantityDTO(1.0, LengthUnit.FEET);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> service.add(null, q));

        assertEquals("QuantityDTO objects can't be null!!!", ex.getMessage());
    }

    @Test
    void testService_ValidationConsistency() {
        QuantityDTO q = new QuantityDTO(1.0, LengthUnit.FEET);

        Exception ex1 = assertThrows(IllegalArgumentException.class, () -> service.add(null, q));
        Exception ex2 = assertThrows(IllegalArgumentException.class, () -> service.subtract(null, q));
        Exception ex3 = assertThrows(IllegalArgumentException.class, () -> service.divide(null, q));

        assertEquals(ex1.getMessage(), ex2.getMessage());
        assertEquals(ex2.getMessage(), ex3.getMessage());
    }

    // ----------------------------------------------------
    // SERVICE - ALL CURRENT MEASUREMENT CATEGORIES
    // ----------------------------------------------------

    @Test
    void testService_AllMeasurementCategories_CurrentlySupported() {
        assertTrue(service.compare(
                new QuantityDTO(1.0, LengthUnit.FEET),
                new QuantityDTO(12.0, LengthUnit.INCHES)));

        assertTrue(service.compare(
                new QuantityDTO(1.0, WeightUnit.KILOGRAM),
                new QuantityDTO(1000.0, WeightUnit.GRAM)));

        assertTrue(service.compare(
                new QuantityDTO(1.0, VolumeUnit.LITRE),
                new QuantityDTO(1000.0, VolumeUnit.MILLILITRE)));
    }

    @Test
    void testService_Temperature_CurrentlyNotSupportedInService() {
        QuantityDTO t1 = new QuantityDTO(0.0, "CELSIUS", "TemperatureUnit");
        QuantityDTO t2 = new QuantityDTO(32.0, "FAHRENHEIT", "TemperatureUnit");

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> service.compare(t1, t2));

        assertEquals("Invalid measurement type or unit!!!", ex.getMessage());
    }
}
