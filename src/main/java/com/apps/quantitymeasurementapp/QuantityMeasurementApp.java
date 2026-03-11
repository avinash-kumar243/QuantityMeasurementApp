package com.apps.quantitymeasurementapp;

import com.apps.quantitymeasurementapp.controller.QuantityMeasurementController;
import com.apps.quantitymeasurementapp.entity.QuantityDTO;
import com.apps.quantitymeasurementapp.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurementapp.repository.QuantityMeasurementCacheRepository;
import com.apps.quantitymeasurementapp.service.QuantityMeasurementServiceImpl;
import com.apps.quantitymeasurementapp.unit.LengthUnit;
import com.apps.quantitymeasurementapp.unit.VolumeUnit;
import com.apps.quantitymeasurementapp.unit.WeightUnit;

public class QuantityMeasurementApp {
	
	// Singleton instance
	private static QuantityMeasurementApp instance;
	
	public QuantityMeasurementController controller;
	public IQuantityMeasurementRepository repository;
	public QuantityMeasurementServiceImpl service;

	private QuantityMeasurementApp() {
		this.repository = QuantityMeasurementCacheRepository.getInstance();
		this.service = new QuantityMeasurementServiceImpl(this.repository);
		this.controller = new QuantityMeasurementController(service); 
	}
	
	// Return a single entity of QuantityMeasurementApp
	public static QuantityMeasurementApp getInstance() {
		if(instance == null) {
			instance = new QuantityMeasurementApp();
		}
		return instance;
	}
		
	
	public static void main(String[] args) {
		QuantityMeasurementApp app = QuantityMeasurementApp.getInstance();
		
		// Addition (LengthUnit)
		System.out.println("\n---------------- Addition ----------------");
		QuantityDTO q1 = new QuantityDTO(1.0, LengthUnit.FEET.getUnitName(), LengthUnit.FEET.getMeasurementType());
		QuantityDTO q2 = new QuantityDTO(24.0, LengthUnit.INCHES.getUnitName(), LengthUnit.INCHES.getMeasurementType());
		
		QuantityDTO result = app.controller.performAddition(q1, q2);
		app.controller.handleResult(q1, q2, result, "+"); 
		
		 
		// Subtraction (WeightUnit)
		System.out.println("\n---------------- Subtraction ----------------");
		QuantityDTO q3 = new QuantityDTO(5.0, WeightUnit.KILOGRAM.getUnitName(), WeightUnit.KILOGRAM.getMeasurementType());
		QuantityDTO q4 = new QuantityDTO(2200.0, WeightUnit.GRAM.getUnitName(), WeightUnit.GRAM.getMeasurementType());
		
		QuantityDTO result2 = app.controller.performSubtraction(q3, q4);
		app.controller.handleResult(q3, q4, result2, "-"); 
		
		
		// Division	(VolumeUnit) 
		System.out.println("\n---------------- Division ----------------");
		QuantityDTO q5 = new QuantityDTO(6.0, VolumeUnit.LITRE.getUnitName(), VolumeUnit.LITRE.getMeasurementType());
		QuantityDTO q6 = new QuantityDTO(2000.0, VolumeUnit.MILLILITRE.getUnitName(), VolumeUnit.MILLILITRE.getMeasurementType());
		
		QuantityDTO result3 = app.controller.performDivision(q5, q6); 
		app.controller.handleResult(q5, q6, result3, "/"); 
	}
}