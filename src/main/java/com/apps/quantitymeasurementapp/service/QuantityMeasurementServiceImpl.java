package com.apps.quantitymeasurementapp.service;

import com.apps.quantitymeasurementapp.entity.QuantityDTO;
import com.apps.quantitymeasurementapp.entity.QuantityMeasurementEntity;
import com.apps.quantitymeasurementapp.entity.QuantityModel;
import com.apps.quantitymeasurementapp.quantity.Quantity;
import com.apps.quantitymeasurementapp.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurementapp.unit.IMeasurable;
import com.apps.quantitymeasurementapp.unit.*;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

	private IQuantityMeasurementRepository repository;
	
	public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
		this.repository = repository;
	}

	private enum Operation {
		COMPARISON, CONVERSION, ADD_WITHOUT_TARGET, ADD_WITH_TARGET, SUBTRACT_WITHOUT_TARGET, SUBTRACT_WITH_TARGET, DIVIDE; 
	}
		
	
	@Override
	public boolean compare(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
	    // Step 1 : Validate QuantityDTO objects
	    validate(thisQuantityDTO, thatQuantityDTO);

	    // Step 2 : Convert DTOs to Quantity objects
	    Quantity<IMeasurable> q1 = convertToQuantity(thisQuantityDTO);
	    Quantity<IMeasurable> q2 = convertToQuantity(thatQuantityDTO);

	    // Step 3 : Compare and return result
	    return q1.equals(q2);
	}

	@Override
	public QuantityDTO convert(QuantityDTO thisQuantityDTO, QuantityDTO targetDTO) {
	    // Step 1 : Validate QuantityDTO objects
	    validate(thisQuantityDTO, targetDTO);

	    // Step 2 : Convert DTOs to Quantity objects
	    Quantity<IMeasurable> q1 = convertToQuantity(thisQuantityDTO);
	    Quantity<IMeasurable> targetQuantity = convertToQuantity(targetDTO);

	    // Step 3 : Convert q1 into target unit
	    Quantity<IMeasurable> result = q1.convertTo(targetQuantity.getUnit());

	    // Step 4 : Return result as DTO
	    return convertToDTO(result);
	}

	@Override
	public QuantityDTO add(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
		// Step 1 : Validate QuantityDTO object
		validate(thisQuantityDTO, thatQuantityDTO);

		// Step 2 : Convert to Quantity Objects
	    Quantity<IMeasurable> q1 = convertToQuantity(thisQuantityDTO);
	    Quantity<IMeasurable> q2 = convertToQuantity(thatQuantityDTO);

		// Step 3 : Perform Add operation -> get result 
	    Quantity<IMeasurable> result = q1.add(q2);

		// Step 4 : save value and unit to repository
	    saveOperation(thisQuantityDTO, thatQuantityDTO, Operation.ADD_WITHOUT_TARGET, result);

		// Step 5 : Return QunatityDTO object to controller
	    return convertToDTO(result); 
	}

	@Override
	public QuantityDTO add(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO, QuantityDTO targetDTO) {
		// Step 1 : Validate QuantityDTO object
		validate(thisQuantityDTO, thatQuantityDTO, targetDTO); 
		
		// Step 2 : Convert to Quantity Objects
		Quantity<IMeasurable> q1 = convertToQuantity(thisQuantityDTO);
		Quantity<IMeasurable> q2 = convertToQuantity(thatQuantityDTO);

		// Step 3 : Perform Add operation -> get result 
		QuantityModel<IMeasurable> targetModel = convertToQuantityModel(targetDTO);
		Quantity<IMeasurable> result = q1.add(q2, targetModel.getUnit());
		
		// Step 4 : save value and unit to repository
		saveOperation(thisQuantityDTO, thatQuantityDTO, Operation.ADD_WITH_TARGET, result);
		
		// Step 5 : Return QunatityDTO object to controller
		 return convertToDTO(result); 
	} 

	@Override
	public QuantityDTO subtract(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
	    validate(thisQuantityDTO, thatQuantityDTO);

	    Quantity<IMeasurable> q1 = convertToQuantity(thisQuantityDTO);
	    Quantity<IMeasurable> q2 = convertToQuantity(thatQuantityDTO);

	    Quantity<IMeasurable> result = q1.subtract(q2);

	    saveOperation(thisQuantityDTO, thatQuantityDTO, Operation.SUBTRACT_WITHOUT_TARGET, result);

	    return convertToDTO(result);
	}

	@Override
	public QuantityDTO subtract(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO, QuantityDTO targetDTO) {
	    validate(thisQuantityDTO, thatQuantityDTO, targetDTO);

	    Quantity<IMeasurable> q1 = convertToQuantity(thisQuantityDTO);
	    Quantity<IMeasurable> q2 = convertToQuantity(thatQuantityDTO);
	    Quantity<IMeasurable> targetQuantity = convertToQuantity(targetDTO);

	    Quantity<IMeasurable> result = q1.subtract(q2, targetQuantity.getUnit());

	    saveOperation(thisQuantityDTO, thatQuantityDTO, Operation.SUBTRACT_WITH_TARGET, result);

	    return convertToDTO(result);
	}

	@Override
	public QuantityDTO divide(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
	    validate(thisQuantityDTO, thatQuantityDTO);

	    Quantity<IMeasurable> q1 = convertToQuantity(thisQuantityDTO);
	    Quantity<IMeasurable> q2 = convertToQuantity(thatQuantityDTO);

	    double result = q1.divide(q2);
	    
	    

	    return new QuantityDTO(result, q1.getUnit());
	}

	
	// Helper method for addition
	private void validate(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
		if(thisQuantityDTO == null || thatQuantityDTO == null) {
			throw new IllegalArgumentException("QuantityDTO objects can't be null!!!");
		}
		
		if(!thisQuantityDTO.getMeasurementType().equals(thatQuantityDTO.getMeasurementType())) {
			throw new IllegalArgumentException("Operation not possible for two different measurement types!!!");
		}
		
		if(!Double.isFinite(thisQuantityDTO.getValue()) || !Double.isFinite(thatQuantityDTO.getValue())) {
			throw new IllegalArgumentException("Values can't be null!!!");
		}
	}
	
	private void validate(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO, QuantityDTO targetDTO) {
		if(thisQuantityDTO == null || thatQuantityDTO == null || targetDTO == null) {
			throw new IllegalArgumentException("QuantityDTO objects can't be null!!!");
		}
		
		if((!thisQuantityDTO.getMeasurementType().equals(thatQuantityDTO.getMeasurementType())) || (!thisQuantityDTO.getMeasurementType().equals(targetDTO.getMeasurementType()))) {
			throw new IllegalArgumentException("Operation not possible n two different measurement types!!!");
		}
		
		if(!Double.isFinite(thisQuantityDTO.getValue()) || !Double.isFinite(thatQuantityDTO.getValue()) || !Double.isFinite(targetDTO.getValue())) {
			throw new IllegalArgumentException("Values can't be null!!!"); 
		}
	}
	
	// Helper method to convert QuantityDTO object to QuantityModel object
	public QuantityModel<IMeasurable> convertToQuantityModel(QuantityDTO dto) {
		String measurementType = dto.getMeasurementType();
		String unitName = dto.getUnit();
		IMeasurable unit;
		
		switch(measurementType) {
			case "LengthUnit":
				unit = LengthUnit.valueOf(unitName);
				break;
				
			case "WeightUnit":
				unit = WeightUnit.valueOf(unitName);
				break;
				
			case "VolumeUnit":
				unit = VolumeUnit.valueOf(unitName);
				break;
				
			case "TemperatureUnit":
				unit = TemperatureUnit.valueOf(unitName);
				break;
				 
			default:
				throw new IllegalArgumentException("Invalid measurement type or unit!!!");
		}
		
		return new QuantityModel<IMeasurable>(dto.getValue(), unit); 
	}
	
	private Quantity<IMeasurable> convertToQuantity(QuantityDTO dto) {
	    QuantityModel<IMeasurable> model = convertToQuantityModel(dto);
	    return new Quantity<>(model.getValue(), model.getUnit());
	}
	
	private void saveOperation(QuantityDTO dto1, QuantityDTO dto2, Operation arithmetic, Quantity<IMeasurable> result) {
	    QuantityModel<IMeasurable> resultModel = new QuantityModel<>(result.getValue(), result.getUnit());

	    QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
	            convertToQuantityModel(dto1),
	            convertToQuantityModel(dto2),
	            arithmetic.name(),
	            resultModel
	    );

	    repository.save(entity);
	}
	
	private QuantityDTO convertToDTO(Quantity<IMeasurable> result) {
	    return new QuantityDTO(result.getValue(), result.getUnit());
	}
}