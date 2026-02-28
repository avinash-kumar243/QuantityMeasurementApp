package com.apps.quantitymeasurementapp;

public class QuantityMeasurementApp {
	
	public static <U extends IMeasurable> boolean demonstrateEquality(Quantity<U> quantity1, Quantity<U> quantity2) {
		if(quantity1 == null || quantity2 == null) {
			throw new IllegalArgumentException("Quantity can't be null");
		}
		return quantity1.equals(quantity2);
	}
	
	public static <U extends IMeasurable> Quantity<U> demonstrateConversion(Quantity<U> quantity, U targetUnit) {
		if(quantity == null) {
			throw new IllegalArgumentException("Quantity can't be null");
		}
		return quantity.convertTo(targetUnit);
	}
	
	public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2) {
		if(quantity1 == null || quantity2 == null) {
			throw new IllegalArgumentException("Quantity can't be null");
		}
		return quantity1.add(quantity2); 
	}
	
	public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit) {
		if(quantity1 == null || quantity2 == null ) {
			throw new IllegalArgumentException("Quantity can't be null");
		} else if(targetUnit == null) {
			throw new IllegalArgumentException("Target unit can't be null");
		}
		return quantity1.add(quantity2, targetUnit);
	}
	
	
	public static <U extends IMeasurable> Quantity<U> demonstrateSubtraction(Quantity<U> quantity1, Quantity<U> quantity2) {
		if(quantity1 == null || quantity2 == null) {
			throw new IllegalArgumentException("Quantity can't be null");
		}
		return quantity1.subtract(quantity2); 
	}
	
	public static <U extends IMeasurable> Quantity<U> demonstrateSubtraction(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit) {
		if(quantity1 == null || quantity2 == null ) {
			throw new IllegalArgumentException("Quantity can't be null");
		} else if(targetUnit == null) {
			throw new IllegalArgumentException("Target unit can't be null");
		}
		return quantity1.subtract(quantity2, targetUnit);
	}
	
	public static <U extends IMeasurable> double demonstrateDivision(Quantity<U> quantity1, Quantity<U> quantity2) {
		if(quantity1 == null || quantity2 == null) {
			throw new IllegalArgumentException("Quantity can't be null");
		}
		return quantity1.divide(quantity2); 
	}
	
	
	
	public static void main(String args[]) {
	
		// --------------- Length Equality Comparison -----------------
		System.out.println(demonstrateEquality(new Quantity<>(1, LengthUnit.FEET), new Quantity<>(12, LengthUnit.INCHES)));
		
		System.out.println(demonstrateEquality(new Quantity<>(1, LengthUnit.YARDS), new Quantity<>(36, LengthUnit.INCHES)));

		System.out.println(demonstrateEquality(new Quantity<>(1, LengthUnit.YARDS), new Quantity<>(3, LengthUnit.FEET)));
 
		System.out.println(demonstrateEquality(new Quantity<>(2, LengthUnit.FEET), new Quantity<>(24, LengthUnit.INCHES)));
		
		// --------------- Weight Equality Comparison -----------------
		System.out.println("\n-------------- Weight Equality Comparison -------------");
		System.out.println(demonstrateEquality(new Quantity<>(1.0, WeightUnit.KILOGRAM), new Quantity<>(1.0, WeightUnit.KILOGRAM)));
		
		System.out.println(demonstrateEquality(new Quantity<>(1.0, WeightUnit.KILOGRAM), new Quantity<>(1000.0, WeightUnit.GRAM)));

		System.out.println(demonstrateEquality(new Quantity<>(2.0, WeightUnit.POUND), new Quantity<>(2.0, WeightUnit.POUND)));

		System.out.println(demonstrateEquality(new Quantity<>(1.0, WeightUnit.KILOGRAM), new Quantity<>(2.20462, WeightUnit.POUND)));
		

		
		// --------------- Length Unit Conversion ---------------
		
		System.out.println("\nConvert 1.0 Feet to Inches: " + demonstrateConversion(new Quantity<>(1.0, LengthUnit.FEET), LengthUnit.INCHES).getValue());

		System.out.println("Convert 3.0 Yards to Feet: " + demonstrateConversion(new Quantity<>(3.0, LengthUnit.YARDS), LengthUnit.FEET).getValue());

		System.out.println("Convert 1000.0 Grams to Kilogram: " + demonstrateConversion(new Quantity<>(1000.0, WeightUnit.GRAM), WeightUnit.KILOGRAM).getValue());

		System.out.println("Convert 1.0 Pound to Kilogram: " + demonstrateConversion(new Quantity<>(1.0, WeightUnit.POUND), WeightUnit.KILOGRAM).getValue());
					
		// --------------- Weight Unit Conversion -----------------		
		System.out.println("\n" + demonstrateConversion(new Quantity<>(1.0, WeightUnit.KILOGRAM), WeightUnit.GRAM).getValue()); 

		System.out.println(demonstrateConversion(new Quantity<>(2.0, WeightUnit.POUND), WeightUnit.KILOGRAM).getValue()); 
		
		System.out.println(demonstrateConversion(new Quantity<>(500.0, WeightUnit.GRAM), WeightUnit.POUND).getValue()); 

		System.out.println(demonstrateConversion(new Quantity<>(0.0, WeightUnit.KILOGRAM), WeightUnit.GRAM).getValue()); 
				
				
		
		// -------------- Add Length ---------------
		System.out.println("\n------------- Addition of two Length ------------");
		
		Quantity<LengthUnit> addTwoFeet = demonstrateAddition(new Quantity<>(1.0, LengthUnit.FEET), new Quantity<>(2.0, LengthUnit.FEET)); 
		System.out.println("Adding 1.0 Feet and 2.0 Feet -> " + addTwoFeet);

		Quantity<LengthUnit> addInchToFeet = demonstrateAddition(new Quantity(1.0, LengthUnit.FEET), new Quantity(12.0, LengthUnit.INCHES)); 
		System.out.println("Adding 1.0 Feet and 12.0 Inches -> " + addInchToFeet);
		
		Quantity<LengthUnit> addFeetToInches = demonstrateAddition(new Quantity(12.0, LengthUnit.INCHES), new Quantity(1.0, LengthUnit.FEET)); 
		System.out.println("Adding 12.0 Inches and 1.0 Feet -> " + addFeetToInches);
		
		Quantity<LengthUnit> addFeetToYards = demonstrateAddition(new Quantity(1.0, LengthUnit.YARDS), new Quantity(3.0, LengthUnit.FEET)); 
		System.out.println("Adding 1.0 Yards and 3.0 Feet -> " + addFeetToYards);
		
		Quantity<LengthUnit> addInchesToYards = demonstrateAddition(new Quantity(36.0, LengthUnit.INCHES), new Quantity(1.0, LengthUnit.YARDS)); 
		System.out.println("Adding 36.0 Inches and 1.0 Yards -> " + addInchesToYards);
		
		Quantity<LengthUnit> addInchesToCentimeters = demonstrateAddition(new Quantity(2.54, LengthUnit.CENTIMETERS), new Quantity(1.0, LengthUnit.INCHES)); 
		System.out.println("Adding 2.54 Centimeters and 1.0 Inches -> " + addInchesToCentimeters);
		
		
		// -------------- Add Length and return to another Length Type ---------------
		System.out.println("\n----------- Addition of two Length and return Another Length Type ----------");
		
		Quantity<LengthUnit> addTwoFeetIntoFeet = demonstrateAddition(new Quantity(1.0, LengthUnit.FEET), new Quantity(12.0, LengthUnit.INCHES), LengthUnit.FEET); 
		System.out.println("Adding 1.0 Feet and 12.0 Inches Into Feet -> " + addTwoFeetIntoFeet);
		
		Quantity<LengthUnit> addFeetAndInchIntoFeet = demonstrateAddition(new Quantity(1.0, LengthUnit.FEET), new Quantity(12.0, LengthUnit.INCHES), LengthUnit.INCHES); 
		System.out.println("Adding 1.0 Feet and 12.0 Inches Into Inches -> " + addFeetAndInchIntoFeet);
		
		Quantity<LengthUnit> addFeetAndInchIntoYards = demonstrateAddition(new Quantity(1.0, LengthUnit.FEET), new Quantity(12.0, LengthUnit.INCHES), LengthUnit.YARDS); 
		System.out.println("Adding 1.0 Feet and 12.0 Inches Into Yards -> " + addFeetAndInchIntoYards);
		
		Quantity<LengthUnit> addYardsAndFeetIntoYards = demonstrateAddition(new Quantity(1.0, LengthUnit.YARDS), new Quantity(3.0, LengthUnit.FEET), LengthUnit.YARDS); 
		System.out.println("Adding 1.0 Yards and 3.0 Feet Into Yards -> " + addYardsAndFeetIntoYards);
		
		Quantity<LengthUnit> addInchesAndYardsIntoFeet = demonstrateAddition(new Quantity(36.0, LengthUnit.INCHES), new Quantity(1.0, LengthUnit.YARDS), LengthUnit.FEET); 
		System.out.println("Adding 36.0 Inches and 1.0 Yards Into Feet -> " + addInchesAndYardsIntoFeet);
		
		Quantity<LengthUnit> addCentimetersAndInchesIntoFeet = demonstrateAddition(new Quantity(2.54, LengthUnit.CENTIMETERS), new Quantity(1.0, LengthUnit.INCHES), LengthUnit.CENTIMETERS); 
		System.out.println("Adding 2.54 Centimeters and 1.0 Inches Into Centimeters -> " + addCentimetersAndInchesIntoFeet);
		
		Quantity<LengthUnit> addFeetAndInchesIntoYards = demonstrateAddition(new Quantity(5.0, LengthUnit.FEET), new Quantity(0.0, LengthUnit.INCHES), LengthUnit.YARDS); 
		System.out.println("Adding 5.0 Feet and 0.0 Inches Into Yards -> " + addFeetAndInchesIntoYards);
		
		Quantity<LengthUnit> addTwoFeetIntoInches = demonstrateAddition(new Quantity(5.0, LengthUnit.FEET), new Quantity(-2.0, LengthUnit.FEET), LengthUnit.INCHES); 
		System.out.println("Adding 5.0 Feet and -2.0 Feet Into Inches -> " + addTwoFeetIntoInches);
		

		
		// --------------- Addition Weight Operations ----------------
		System.out.println("\n--------------- Addition of Weights ---------------");
		
		Quantity<WeightUnit> addTwoKilogram = demonstrateAddition(new Quantity<>(1.0, WeightUnit.KILOGRAM), new Quantity<>(2.0, WeightUnit.KILOGRAM));
		System.out.println("Addition of 1.0 Kilogram and 2.0 Kilogram -> " + addTwoKilogram);
		
		Quantity<WeightUnit> addKilogramWithGram = demonstrateAddition(new Quantity<>(1.0, WeightUnit.KILOGRAM), new Quantity<>(1000.0, WeightUnit.GRAM));
		System.out.println("Addition of 1.0 Kilogram and 1000.0 Gram -> " + addKilogramWithGram);
		
		Quantity<WeightUnit> addGramWithKilogram = demonstrateAddition(new Quantity<>(500.0, WeightUnit.GRAM), new Quantity<>(0.5, WeightUnit.KILOGRAM));
		System.out.println("Addition of 500.0 Gram and 0.5 Kilogram -> " + addGramWithKilogram);
		
		
		// Getting error
		Quantity<WeightUnit> addKilogramWithGram2 = demonstrateAddition(new Quantity<>(1.0, WeightUnit.KILOGRAM), new Quantity<>(1000.0, WeightUnit.GRAM), WeightUnit.GRAM);
		System.out.println("Addition of 1.0 Kilogram and 1000.0 Gram -> " + addKilogramWithGram2);
		
		Quantity<WeightUnit> addPoundWithGram = demonstrateAddition(new Quantity<>(1.0, WeightUnit.POUND), new Quantity<>(453.592, WeightUnit.GRAM), WeightUnit.POUND);
		System.out.println("Addition of 1.0 Pound and 453.592 Gram -> " + addPoundWithGram);
		
		Quantity<WeightUnit> addKilometerWithPound = demonstrateAddition(new Quantity<>(2.0, WeightUnit.KILOGRAM), new Quantity<>(4.0, WeightUnit.POUND));
		System.out.println("Addition of 2.0 Kilogram and 4.0 Pound -> " + addKilometerWithPound);		
		
		
		
		// ------------- Volume Instances -------------
		Quantity<VolumeUnit> volume1 = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> volume2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
		Quantity<VolumeUnit> volume3 = new Quantity<>(1.0, VolumeUnit.GALLON);
		
		
		// ---------------- Volume Equality ----------------
		System.out.println("\n------------- Volume Equality ---------------");

		System.out.println(new Quantity<>(1.0, VolumeUnit.LITRE).equals(new Quantity<>(1.0, VolumeUnit.LITRE)));

		System.out.println(new Quantity<>(1.0, VolumeUnit.LITRE).equals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)));

		System.out.println(new Quantity<>(1.0, VolumeUnit.GALLON).equals(new Quantity<>(1.0, VolumeUnit.GALLON)));

		System.out.println(new Quantity<>(1.0, VolumeUnit.LITRE).equals(new Quantity<>(0.264172, VolumeUnit.GALLON)));

		System.out.println(new Quantity<>(500.0, VolumeUnit.MILLILITRE).equals(new Quantity<>(0.5, VolumeUnit.LITRE)));

		System.out.println(new Quantity<>(3.78541, VolumeUnit.LITRE).equals(new Quantity<>(1.0, VolumeUnit.GALLON)));
		
		
		
		// ---------------- Volume Conversion ----------------
		System.out.println("\n------------- Volume Conversion ---------------");

		System.out.println(volume1.convertTo(VolumeUnit.MILLILITRE)); 
		
		System.out.println(volume3.convertTo(VolumeUnit.LITRE));
		
		System.out.println(volume2.convertTo(VolumeUnit.GALLON));
		
		
		// -------------------- Volume Conversions --------------------
		System.out.println("\n-------------- Volume Conversions ---------------");

		System.out.println(new Quantity<>(1.0, VolumeUnit.LITRE).convertTo(VolumeUnit.MILLILITRE));

		System.out.println(new Quantity<>(2.0, VolumeUnit.GALLON).convertTo(VolumeUnit.LITRE));

		System.out.println(new Quantity<>(500.0, VolumeUnit.MILLILITRE).convertTo(VolumeUnit.GALLON));

		System.out.println(new Quantity<>(0.0, VolumeUnit.LITRE).convertTo(VolumeUnit.MILLILITRE));

		System.out.println(new Quantity<>(1.0, VolumeUnit.LITRE).convertTo(VolumeUnit.LITRE));
		
		
		// -------------------- Addition (Implicit Target Unit) --------------------
		System.out.println("\n---------------- Addition (Implicit Target Unit) --------------");

		System.out.println(new Quantity<>(1.0, VolumeUnit.LITRE).add(new Quantity<>(2.0, VolumeUnit.LITRE)));

		System.out.println(new Quantity<>(1.0, VolumeUnit.LITRE).add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)));

		System.out.println(new Quantity<>(500.0, VolumeUnit.MILLILITRE).add(new Quantity<>(0.5, VolumeUnit.LITRE)));

		System.out.println(new Quantity<>(2.0, VolumeUnit.GALLON).add(new Quantity<>(3.78541, VolumeUnit.LITRE)));
		
		
		// -------------------- Addition (Explicit Target Unit) --------------------
		System.out.println("\n------------- Addition (Explicit Target Unit) --------------");

		System.out.println(new Quantity<>(1.0, VolumeUnit.LITRE).add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE), VolumeUnit.MILLILITRE));

		System.out.println(new Quantity<>(1.0, VolumeUnit.GALLON).add(new Quantity<>(3.78541, VolumeUnit.LITRE), VolumeUnit.GALLON));

		System.out.println(new Quantity<>(500.0, VolumeUnit.MILLILITRE).add(new Quantity<>(1.0, VolumeUnit.LITRE), VolumeUnit.GALLON));

		System.out.println(new Quantity<>(2.0, VolumeUnit.LITRE).add(new Quantity<>(4.0, VolumeUnit.GALLON), VolumeUnit.LITRE));
		
		
		// -------------------- Category Incompatibility --------------------
		System.out.println("\n---------------- Category Incompatibility -----------------");

		System.out.println(new Quantity<>(1.0, VolumeUnit.LITRE).equals(new Quantity<>(1.0, LengthUnit.FEET)));

		System.out.println(new Quantity<>(1.0, VolumeUnit.LITRE).equals(new Quantity<>(1.0, WeightUnit.KILOGRAM)));
		
		
		
		System.out.println("\n--------------- Subtraction ----------------");

		// -------- Subtraction with Implicit Target Unit --------
		System.out.println("\n--- Implicit Target Unit ---");

		System.out.println(demonstrateSubtraction(new Quantity<>(10.0, LengthUnit.FEET), new Quantity<>(6.0, LengthUnit.INCHES)));

		System.out.println(demonstrateSubtraction(new Quantity<>(10.0, WeightUnit.KILOGRAM), new Quantity<>(5000.0, WeightUnit.GRAM)));

		System.out.println(demonstrateSubtraction(new Quantity<>(5.0, VolumeUnit.LITRE), new Quantity<>(500.0, VolumeUnit.MILLILITRE)));


		// -------- Subtraction with Explicit Target Unit --------
		System.out.println("\n--- Explicit Target Unit ---");

		System.out.println(
		        demonstrateSubtraction(new Quantity<>(10.0, LengthUnit.FEET), new Quantity<>(6.0, LengthUnit.INCHES), LengthUnit.INCHES));

		System.out.println(demonstrateSubtraction(new Quantity<>(10.0, WeightUnit.KILOGRAM), new Quantity<>(5000.0, WeightUnit.GRAM), WeightUnit.GRAM));

		System.out.println(demonstrateSubtraction(new Quantity<>(5.0, VolumeUnit.LITRE), new Quantity<>(2.0, VolumeUnit.LITRE), VolumeUnit.MILLILITRE));


		// -------- Subtraction Resulting in Negative --------
		System.out.println("\n--- Negative Results ---");

		System.out.println(demonstrateSubtraction(new Quantity<>(5.0, LengthUnit.FEET), new Quantity<>(10.0, LengthUnit.FEET)));

		System.out.println(demonstrateSubtraction(new Quantity<>(2.0, WeightUnit.KILOGRAM), new Quantity<>(5.0, WeightUnit.KILOGRAM)));


		// -------- Subtraction Resulting in Zero --------
		System.out.println("\n--- Zero Results ---");

		System.out.println(demonstrateSubtraction(new Quantity<>(10.0, LengthUnit.FEET),new Quantity<>(120.0, LengthUnit.INCHES)));

		System.out.println(demonstrateSubtraction(new Quantity<>(1.0, VolumeUnit.LITRE), new Quantity<>(1000.0, VolumeUnit.MILLILITRE)));


		// =======================================================
		// ---------------- DIVISION ------------------------------
		// =======================================================

		System.out.println("\n------------- DIVISION ---------------");

		System.out.println(demonstrateDivision(new Quantity<>(10.0, LengthUnit.FEET), new Quantity<>(2.0, LengthUnit.FEET)));

		System.out.println(demonstrateDivision(new Quantity<>(10.0, LengthUnit.FEET), new Quantity<>(5.0, LengthUnit.FEET)));

		System.out.println(demonstrateDivision(new Quantity<>(24.0, LengthUnit.INCHES), new Quantity<>(2.0, LengthUnit.FEET)));

		System.out.println(demonstrateDivision(new Quantity<>(10.0, WeightUnit.KILOGRAM), new Quantity<>(5.0, WeightUnit.KILOGRAM)));

		System.out.println(
		        demonstrateDivision(
		                new Quantity<>(5.0, VolumeUnit.LITRE),
		                new Quantity<>(10.0, VolumeUnit.LITRE)
		        )
		);


		// -------- Division with Different Units (Same Category) --------

		System.out.println("\n--- Division Cross Units ---");

		System.out.println(
		        demonstrateDivision(
		                new Quantity<>(12.0, LengthUnit.INCHES),
		                new Quantity<>(1.0, LengthUnit.FEET)
		        )
		);

		System.out.println(
		        demonstrateDivision(
		                new Quantity<>(2000.0, WeightUnit.GRAM),
		                new Quantity<>(1.0, WeightUnit.KILOGRAM)
		        )
		);

		System.out.println(
		        demonstrateDivision(
		                new Quantity<>(1000.0, VolumeUnit.MILLILITRE),
		                new Quantity<>(1.0, VolumeUnit.LITRE)
		        )
		);
	} 
}
