package com.app.quantitymeasurementapp.service;

import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.quantitymeasurementapp.dto.QuantityDTO;
import com.app.quantitymeasurementapp.dto.QuantityMeasurementDTO;
import com.app.quantitymeasurementapp.exception.CategoryMismatchException;
import com.app.quantitymeasurementapp.exception.QuantityMeasurementException;
import com.app.quantitymeasurementapp.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurementapp.entity.QuantityModel;
import com.app.quantitymeasurementapp.quantity.Quantity;
import com.app.quantitymeasurementapp.repository.QuantityMeasurementRepository;
import com.app.quantitymeasurementapp.unit.IMeasurable;
import com.app.quantitymeasurementapp.unit.*;


@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {
	
	// Private Logger for logging information in the controller
	private static final Logger logger = Logger.getLogger(QuantityMeasurementServiceImpl.class.getName());
 
	
	@Autowired
	private QuantityMeasurementRepository repository;


	@Override
	public QuantityMeasurementDTO compare(QuantityDTO dto1, QuantityDTO dto2) {

        QuantityModel<IMeasurable> model1 = convertDtoToModel(dto1);
        QuantityModel<IMeasurable> model2 = convertDtoToModel(dto2);

        validateQuanntityModels(model1, model2, model1);
        
        Quantity<IMeasurable> quantity1 = convertModelToQuantity(model1);
        Quantity<IMeasurable> quantity2 = convertModelToQuantity(model2);

        double val1 = quantity1.getUnit().convertToBaseUnit(quantity1.getValue());
        double val2 = quantity2.getUnit().convertToBaseUnit(quantity2.getValue());
        
        boolean isEqual = Double.compare(val1, val2) == 0;
        
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
    			dto1.getValue(),
    			dto1.getUnit(),
    			dto1.getMeasurementType(),
    			dto2.getValue(),
    			dto2.getUnit(),
    			dto2.getMeasurementType(),
    			ArithmeticOperation.COMPARE.name(),
    			isEqual ? 1.0 : 0.0,
    			dto1.unit,
    			dto1.measurementType,
    			"null",
    			false,
    			"null"
    		);
            repository.save(entity);
            return new QuantityMeasurementDTO().from(entity);
	}
	
	
	@Override
	public QuantityMeasurementDTO convert(QuantityDTO dto1, QuantityDTO targetDTO) {
		
		QuantityModel<IMeasurable> model = convertDtoToModel(dto1);
        QuantityModel<IMeasurable> targetModel = convertDtoToModel(targetDTO);

        validateQuanntityModels(model, targetModel, model);
        
        Quantity<IMeasurable> quantity1 = convertModelToQuantity(model);
        
        Quantity<IMeasurable> result = quantity1.convertTo(targetModel.getUnit());
		
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
			dto1.getValue(),
			dto1.getUnit(),
			dto1.getMeasurementType(),
			targetDTO.getValue(),
			targetDTO.getUnit(),
			targetDTO.getMeasurementType(),
			ArithmeticOperation.CONVERT.name(),
			result.getValue(),
			targetDTO.getUnit(),
			dto1.measurementType,
			"null",
			false,
			"null"
		);
        repository.save(entity);
        return new QuantityMeasurementDTO().from(entity);
	}

	@Override
	public QuantityMeasurementDTO add(QuantityDTO dto1, QuantityDTO dto2) {
		return add(dto1, dto2, dto1); 
	}

	@Override
	public QuantityMeasurementDTO add(QuantityDTO dto1, QuantityDTO dto2, QuantityDTO targetDTO) {
		return executeArithmeticOperation(dto1, dto2, targetDTO, "addition", ArithmeticOperation.ADD);
	} 


	@Override
	public QuantityMeasurementDTO subtract(QuantityDTO dto1, QuantityDTO dto2) {
		return subtract(dto1, dto2, dto1); 
	}

	@Override
	public QuantityMeasurementDTO subtract(QuantityDTO dto1, QuantityDTO dto2, QuantityDTO targetDTO) {
		 return executeArithmeticOperation(dto1, dto2, targetDTO, "subtraction", ArithmeticOperation.SUBTRACT);
	}

	@Override
	public QuantityMeasurementDTO divide(QuantityDTO dto1, QuantityDTO dto2) {
		return executeArithmeticOperation(dto1, dto2, dto1, "division", ArithmeticOperation.DIVIDE); 
	}
	
		
	@Override
	public List<QuantityMeasurementDTO> getOperationHistory(String operation) {
		List<QuantityMeasurementEntity> entities = repository.findByOperation(operation);
	    return new QuantityMeasurementDTO().fromEntityList(entities);
	}

	@Override
	public List<QuantityMeasurementDTO> getMeasurementsByType(String type) {
		List<QuantityMeasurementEntity> entities = repository.findByThisMeasurementType(type);
	    return new QuantityMeasurementDTO().fromEntityList(entities);
	}

	@Override
	public long getOperationCount(String operation) {
		return repository.countByOperationAndIsErrorFalse(operation); 
	}

	@Override
	public List<QuantityMeasurementDTO> getErrorHistory() {
		List<QuantityMeasurementEntity> entities = repository.findByIsErrorTrue();
        return new QuantityMeasurementDTO().fromEntityList(entities);
	}
	
	@Override
	public List<QuantityMeasurementDTO> getAllHistory() {
	    List<QuantityMeasurementEntity> entities = repository.findAllByOrderByCreatedAtDesc();
	    return new QuantityMeasurementDTO().fromEntityList(entities);
	}
	
	
	private QuantityMeasurementDTO executeArithmeticOperation(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO, QuantityDTO resultQuantityDTO, String operation, ArithmeticOperation arithmeticOperation) {
		
        QuantityModel<IMeasurable> model1 = convertDtoToModel(thisQuantityDTO);
        QuantityModel<IMeasurable> model2 = convertDtoToModel(thatQuantityDTO);
        QuantityModel<IMeasurable> targetModel = convertDtoToModel(resultQuantityDTO);

        validateQuanntityModels(model1, model2, targetModel);
        
        Quantity<IMeasurable> quantity1 = convertModelToQuantity(model1);
        Quantity<IMeasurable> quantity2 = convertModelToQuantity(model2);
        Quantity<IMeasurable> targetQuantity = convertModelToQuantity(targetModel);

        Quantity<IMeasurable> resultQuantity; 
        
        switch(operation) {
        	case "addition":
        		resultQuantity = quantity1.add(quantity2, targetQuantity.getUnit());
        		break;
        		
        	case "subtraction":
        		resultQuantity = quantity1.subtract(quantity2, targetQuantity.getUnit());
        		break;
        		
        	case "division":
        		resultQuantity = quantity1.divide(quantity2, targetQuantity.getUnit());
        		break;
        		
        	default:
                throw new IllegalArgumentException("Unsupported arithmetic operation: " + arithmeticOperation);
        }
        
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
			thisQuantityDTO.getValue(),
			thisQuantityDTO.getUnit(),
			thisQuantityDTO.getMeasurementType(),
			thatQuantityDTO.getValue(),
			thatQuantityDTO.getUnit(),
			thatQuantityDTO.getMeasurementType(),
			operation,
			resultQuantity.getValue(),
			resultQuantity.getUnit().toString(),
			resultQuantityDTO.getMeasurementType(),
			"null",
			false,
			"null"
		);
        repository.save(entity);
        return new QuantityMeasurementDTO().from(entity);
    }
	

    private QuantityModel<IMeasurable> convertDtoToModel(QuantityDTO quantityDTO) {
        if (quantityDTO == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }

        if (quantityDTO.getUnit() == null || quantityDTO.getUnit().isBlank()) {
            throw new IllegalArgumentException("Unit cannot be null or blank");
        }

        if (quantityDTO.getMeasurementType() == null || quantityDTO.getMeasurementType().isBlank()) {
            throw new IllegalArgumentException("Measurement type cannot be null or blank");
        }

        String measurementType = quantityDTO.getMeasurementType().trim().toLowerCase(Locale.ROOT);
        String unit = quantityDTO.getUnit().trim().toUpperCase(Locale .ROOT);

        switch (measurementType) {
            case "length":
            case "lengthunit":
                return new QuantityModel<>(quantityDTO.getValue(), LengthUnit.valueOf(unit));

            case "volume":
            case "volumeunit":
                return new QuantityModel<>(quantityDTO.getValue(), VolumeUnit.valueOf(unit));

            case "weight":
            case "weightunit":
                return new QuantityModel<>(quantityDTO.getValue(), WeightUnit.valueOf(unit));

            case "temperature":
            case "temperatureunit":
                return new QuantityModel<>(quantityDTO.getValue(), TemperatureUnit.valueOf(unit));

            default:
                throw new IllegalArgumentException("Unsupported measurement type: " + quantityDTO.getMeasurementType());
        }
    }
    

    private enum ArithmeticOperation {
        ADD,
        SUBTRACT,
        DIVIDE,
        COMPARE,
        CONVERT
    }
	
	
	// Helper method for addition	
    private <U extends IMeasurable> void validateQuanntityModels(
            QuantityModel<U> model1,
            QuantityModel<U> model2,
            QuantityModel<U> targetModel) {

        if (model1 == null || model2 == null || targetModel == null) {
            throw new QuantityMeasurementException("QuantityDTO objects can't be null!!!");
        }

        if (!model1.getUnit().getClass().equals(model2.getUnit().getClass())
                || !model1.getUnit().getClass().equals(targetModel.getUnit().getClass())) {
            throw new CategoryMismatchException("Operation not possible on two different measurement types!!!");
        }

        if (!Double.isFinite(model1.getValue())
                || !Double.isFinite(model2.getValue())
                || !Double.isFinite(targetModel.getValue())) {
            throw new QuantityMeasurementException("Values must be finite numbers!!!");
        }
    }
	
	 private <U extends IMeasurable> Quantity<U> convertModelToQuantity(QuantityModel<U> model) {
        if (model == null) {
            throw new QuantityMeasurementException("Model cannot be null");
        }

        return new Quantity<>(model.getValue(), model.getUnit());
     }
}