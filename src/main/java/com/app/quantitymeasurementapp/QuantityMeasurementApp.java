package com.app.quantitymeasurementapp;

import java.util.logging.Logger;

import com.app.quantitymeasurementapp.controller.QuantityMeasurementController;
import com.app.quantitymeasurementapp.entity.QuantityDTO;
import com.app.quantitymeasurementapp.repository.IQuantityMeasurementRepository;
import com.app.quantitymeasurementapp.repository.QuantityMeasurementCacheRepository;
import com.app.quantitymeasurementapp.service.QuantityMeasurementServiceImpl;
import com.app.quantitymeasurementapp.unit.LengthUnit;

public class QuantityMeasurementApp {
	
	// Singleton instance
	private static QuantityMeasurementApp instance;
	
	public QuantityMeasurementController controller;
	public IQuantityMeasurementRepository repository;
	public QuantityMeasurementServiceImpl service;
	
	private static final Logger logger = Logger.getLogger(QuantityMeasurementApp.class.getName());

	private QuantityMeasurementApp() {
		this.repository = QuantityMeasurementCacheRepository.getInstance();
		this.service = new QuantityMeasurementServiceImpl(this.repository);
		this.controller = new QuantityMeasurementController(service);
		logger.info("Quantity Measurement Application initialized with " + "Cache Repository");
	}
		
	// Return a single entity of QuantityMeasurementApp
	public static QuantityMeasurementApp getInstance() {
		if(instance == null) {
			instance = new QuantityMeasurementApp();
		}
		return instance;
	}
		
	
	public static void main(String[] args) {
		
		QuantityMeasurementController controller = getInstance().controller;
		
		// Testing Comparisons of Two QuantityDTOs
		QuantityDTO q1 = new QuantityDTO(2.0, LengthUnit.FEET.getUnitName(), LengthUnit.FEET.getMeasurementType());
		QuantityDTO q2 = new QuantityDTO(24.0, LengthUnit.INCHES.getUnitName(), LengthUnit.INCHES.getMeasurementType());
		
		boolean comparisonResult = controller.performComparison(q1, q2);
		logger.info("Comparison result: " + comparisonResult);
	}
}