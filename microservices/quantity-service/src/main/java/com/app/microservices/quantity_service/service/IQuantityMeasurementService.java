package com.app.microservices.quantity_service.service;

import java.util.List;

import com.app.microservices.quantity_service.dto.QuantityDTO;
import com.app.microservices.quantity_service.dto.QuantityMeasurementDTO;

public interface IQuantityMeasurementService {
	
	public QuantityMeasurementDTO compare(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO);
	
	public QuantityMeasurementDTO convert(QuantityDTO thisQuantityDTO, QuantityDTO thaQuantityDTO);
	
	public QuantityMeasurementDTO add(QuantityDTO thiQuantityDTO, QuantityDTO tahtQuantityDTO);
	
	public QuantityMeasurementDTO add(QuantityDTO thisQuantityDTO, QuantityDTO thaQuantityDTO, QuantityDTO targetUnitDTO);
	
	public QuantityMeasurementDTO subtract(QuantityDTO thiQuantityDTO, QuantityDTO thaQuantityDTO);
	
	public QuantityMeasurementDTO subtract(QuantityDTO thisQuantityDTO, QuantityDTO thaQuantityDTO, QuantityDTO targetUnitDTO);

	public QuantityMeasurementDTO divide(QuantityDTO thiQuantityDTO, QuantityDTO thaQuantityDTO);

	
	List<QuantityMeasurementDTO> getOperationHistory(String operation);
	
	List<QuantityMeasurementDTO> getMeasurementsByType(String type);
	
	long getOperationCount(String operation);
	 
	List<QuantityMeasurementDTO> getErrorHistory();
	
	List<QuantityMeasurementDTO> getAllHistory();
}   