package com.app.quantitymeasurementapp.entity;

import java.io.Serializable;

import com.app.quantitymeasurementapp.unit.IMeasurable;

public class QuantityMeasurementEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final double EPSILON = 0.0001;
	
	public double thisValue;
	public String thisUnit;
	public String thisMeasurementType;
	public double thatValue;
	public String thatUnit;
	public String thatMeasurementType;
	
	public String operation;
	public double resultValue;
	public String resultUnit;
	public String resultMeasurementType;
	
	public String resultString;
	public String errorMessage;
	public boolean isError;
	
	 public QuantityModel<IMeasurable> thisQuantity;
	 public QuantityModel<IMeasurable> thatQuantity;
	
	
	// 3
	public QuantityMeasurementEntity(QuantityModel<IMeasurable> thisQuantity, QuantityModel<IMeasurable> thatQuantity, String operation) {
		this.thisQuantity = thisQuantity;
		this.thatQuantity = thatQuantity;
		this.operation = operation;
		
		
	}
	
	// Single Operand 4
	public QuantityMeasurementEntity(QuantityModel<IMeasurable> thisQuantity, QuantityModel<IMeasurable> thatQuantity, String operation, String result) { 
		this(thisQuantity, thatQuantity, operation);
		this.resultString = result;
	}
	
	// Single Operand 4
	public QuantityMeasurementEntity(QuantityModel<IMeasurable> thisQuantity, QuantityModel<IMeasurable> thatQuantity, String operation, double resultValue) { 
		this(thisQuantity, thatQuantity, operation);
		this.resultValue = resultValue;
	}
	 
	// 4
	public QuantityMeasurementEntity(QuantityModel<IMeasurable> thisQuantity, QuantityModel<IMeasurable> thatQuantity, String operation, QuantityModel<IMeasurable> result) {
		this(thisQuantity, thatQuantity, operation);
		this.resultValue = result.getValue();
		this.resultUnit = result.getUnit().getUnitName();
		this.resultMeasurementType = result.getUnit().getMeasurementType();
	}
	
	// 5
	public QuantityMeasurementEntity(QuantityModel<IMeasurable> thisQuantity, QuantityModel<IMeasurable> thatQuantity, String operation, String errorMessage, boolean isError) {
		this(thisQuantity, thatQuantity, operation);
		this.errorMessage = errorMessage;
		this.isError = isError;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		
		if(obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		
		QuantityModel<?> thatQuantity = (QuantityModel<?>) obj; 
		
		 if(!this.thisUnit.getClass().equals(thatQuantity.getUnit().getClass())) {
		     return false;
		 }
		
		double thisBase = thisQuantity.unit.convertToBaseUnit(thisValue);
		double thatBase = thatQuantity.unit.convertToBaseUnit(thatQuantity.value); 
		 
		return Math.abs(thisBase - thatBase) < EPSILON; 
	}
	
	@Override
	public String toString() {
		return thisValue + " " + thisUnit; 
	}
	
	
	public static void main(String[] args) {
		System.out.println("Quantity Measurement Entity class"); 
	}
}