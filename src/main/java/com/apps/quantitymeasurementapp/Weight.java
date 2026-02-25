package com.apps.quantitymeasurementapp;

public class Weight {
	private final double value;
	private final WeightUnit unit;
	private static final double EPSILON = 0.000001;
	
	public Weight(double value, WeightUnit unit) {
		if(unit == null) {
			throw new IllegalArgumentException("Unit can't be null");
		}
		if(Double.isNaN(value)) {
			throw new IllegalArgumentException("Value can't be null");
		}
		
		this.value = value;
		this.unit = unit;
	}

	public double getValue() {
		return value;
	}
	public WeightUnit getUnit() {
		return unit;
	}
	
	@Override
	public String toString() {
		return value + " " + unit;
	} 
	 
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		
		return this.compare((Weight)obj); 
	}
	
	public boolean compare(Weight weight) {
		double thisWeightToBaseUnit = this.unit.convertToBaseUnit(this.value);
		double newWeightToBaseUnit = weight.unit.convertToBaseUnit(weight.value);
		
		return Math.abs(thisWeightToBaseUnit - newWeightToBaseUnit) < EPSILON;
	}
	
	public Weight convertTo(WeightUnit unit) {
		double converted = ((this.value * this.unit.getConversionFactor()) / unit.getConversionFactor());
		return new Weight(converted,unit); 
	}
	
	public Weight add(Weight thatWeight) {
		return addAndConvert(thatWeight, this.unit); 
	}
	
	public Weight add(Weight weight, WeightUnit thatUnit) {
		return addAndConvert(weight, thatUnit); 
	}
	
	public Weight addAndConvert(Weight weight, WeightUnit thatUnit) {
		if(weight == null) {
			throw new IllegalArgumentException("Weight can't be null");
		}
		if(thatUnit == null) {
			throw new IllegalArgumentException("Weight unit can't be null");
		}
		
		double baseValue = this.unit.convertToBaseUnit(this.value);
		double thatValue = weight.unit.convertToBaseUnit(weight.value);
		
		double result = thatUnit.convertFromBaseUnit(baseValue + thatValue);
		result = (int)(result * 100.0) / 100.0;
		
		return new Weight(result, thatUnit);
	}

	public double convertFromBaseToTargetUnit(double weightInGrams, WeightUnit targetUnit) {
		return weightInGrams / targetUnit.getConversionFactor(); 
	}
	
	public static void main (String args[]) {
		Weight weight1 = new Weight(12.0, WeightUnit.KILOGRAM);
		Weight weight2 = new Weight(12000.0, WeightUnit.GRAM);

		System.out.println(weight1.equals(weight2));
		
		System.out.println(weight1.convertTo(WeightUnit.GRAM));
		
		System.out.println(weight2.add(weight1));
		
		System.out.println(weight1.add(weight2, WeightUnit.MILLIGRAM));
	}
}