package com.app.quantitymeasurementapp.service;

import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.quantitymeasurementapp.dto.QuantityMeasurementDTO;
import com.app.quantitymeasurementapp.dto.QuantityModel;
import com.app.quantitymeasurementapp.exception.CategoryMismatchException;
import com.app.quantitymeasurementapp.exception.QuantityMeasurementException;
import com.app.quantitymeasurementapp.model.QuantityDTO;
import com.app.quantitymeasurementapp.model.QuantityMeasurementEntity;
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
		QuantityMeasurementDTO measurementDTO = createBaseDTO(dto1, dto2, "comparison");

        try {
            QuantityModel<IMeasurable> thisQuantity = convertDtoToModel(dto1);
            QuantityModel<IMeasurable> thatQuantity = convertDtoToModel(dto2);

            validateSameCategory(thisQuantity, thatQuantity);

            Quantity<IMeasurable> quantity1 = convertModelToQuantity(thisQuantity);
            Quantity<IMeasurable> quantity2 = convertModelToQuantity(thatQuantity);
            
            boolean result = quantity1.equals(quantity2);

            measurementDTO.setResultString(String.valueOf(result));
            measurementDTO.setError(false);
            measurementDTO.setErrorMessage(null);

        } catch (Exception e) {
            setError(measurementDTO, e);
        }

        repository.save(measurementDTO.toEntity()); 
        return measurementDTO; 
	}
	
	
	@Override
	public QuantityMeasurementDTO convert(QuantityDTO dto1, QuantityDTO targetDTO) {
		 QuantityMeasurementDTO measurementDTO = createBaseDTO(dto1, targetDTO, "conversion");

        try {
            QuantityModel<IMeasurable> sourceModel = convertDtoToModel(dto1);
            QuantityModel<IMeasurable> targetModel = convertDtoToModel(targetDTO);

            validateSameCategory(sourceModel, targetModel);

            Quantity<IMeasurable> sourceQuantity = convertModelToQuantity(sourceModel);
            Quantity<IMeasurable> targetQuantity = convertModelToQuantity(targetModel);

            Quantity<IMeasurable> convertedQuantity = convertTo(sourceQuantity, targetQuantity);

            setSuccess(
                    measurementDTO,
                    convertedQuantity.getValue(),
                    targetDTO.getUnit(),
                    targetDTO.getMeasurementType(),
                    convertedQuantity.getValue() + " " + targetDTO.getUnit()
            );

        } catch (Exception e) {
            setError(measurementDTO, e);
        }

        repository.save(measurementDTO.toEntity());
        return measurementDTO;
	}
	private <U extends IMeasurable> Quantity<U> convertTo(Quantity<U> sourceQuantity, Quantity<U> targetQuantity) {
	    if (sourceQuantity == null || targetQuantity == null) {
	        throw new IllegalArgumentException("Quantities cannot be null");
	    }

	    return sourceQuantity.convertTo(targetQuantity.getUnit());
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
		QuantityMeasurementDTO measurementDTO = createBaseDTO(dto1, dto2, "division");

        try {
            QuantityModel<IMeasurable> model1 = convertDtoToModel(dto1);
            QuantityModel<IMeasurable> model2 = convertDtoToModel(dto2);

            validateSameCategory(model1, model2);

            Quantity<IMeasurable> quantity1 = convertModelToQuantity(model1);
            Quantity<IMeasurable> quantity2 = convertModelToQuantity(model2);

            double result = quantity1.divide(quantity2);

            setSuccess(
                    measurementDTO,
                    result,
                    "NUMBER",
                    "SCALAR",
                    String.valueOf(result)
            );

        } catch (Exception e) {
            setError(measurementDTO, e);
        }

        repository.save(measurementDTO.toEntity());
        return measurementDTO;
	}
	
		
	@Override
	public List<QuantityMeasurementDTO> getOperationHistory(String operation) {
		List<QuantityMeasurementEntity> entities = repository.findByOperation(operation);
	    return QuantityMeasurementDTO.fromEntityList(entities);
	}

	@Override
	public List<QuantityMeasurementDTO> getMeasurementsByType(String type) {
		List<QuantityMeasurementEntity> entities = repository.findByThisMeasurementType(type);
	    return QuantityMeasurementDTO.fromEntityList(entities);
	}

	@Override
	public long getOperationCount(String operation) {
		return repository.countByOperationAndIsErrorFalse(operation); 
	}

	@Override
	public List<QuantityMeasurementDTO> getErrorHistory() {
		List<QuantityMeasurementEntity> entities = repository.findByIsErrorTrue();
        return QuantityMeasurementDTO.fromEntityList(entities);
	}
	
	
	private QuantityMeasurementDTO executeArithmeticOperation(
            QuantityDTO thisQuantityDTO,
            QuantityDTO thatQuantityDTO,
            QuantityDTO resultQuantityDTO,
            String operation,
            ArithmeticOperation arithmeticOperation) {
		 
        QuantityMeasurementDTO measurementDTO = createBaseDTO(thisQuantityDTO, thatQuantityDTO, operation);

        try {        	
            QuantityModel<IMeasurable> model1 = convertDtoToModel(thisQuantityDTO);
            QuantityModel<IMeasurable> model2 = convertDtoToModel(thatQuantityDTO);
            QuantityModel<IMeasurable> targetModel = convertDtoToModel(resultQuantityDTO);

            validateArithmeticOperands(model1, model2, targetModel);
            
            Quantity<IMeasurable> quantity1 = convertModelToQuantity(model1);
            Quantity<IMeasurable> quantity2 = convertModelToQuantity(model2);
            Quantity<IMeasurable> targetQuantity = convertModelToQuantity(targetModel);

            Quantity<IMeasurable> resultQuantity = performArithmetic(
                    quantity1,
                    quantity2,
                    targetQuantity,
                    arithmeticOperation
            );
            
            setSuccess(
                    measurementDTO,
                    resultQuantity.getValue(), 
                    resultQuantityDTO.getUnit(),
                    resultQuantityDTO.getMeasurementType(),
                    resultQuantity.getValue() + " " + resultQuantityDTO.getUnit()
            );

        } catch (Exception e) {
            setError(measurementDTO, e);
        }

        repository.save(measurementDTO.toEntity());
        return measurementDTO;
    }
	
	
	private QuantityMeasurementDTO createBaseDTO(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO, String operation) {
        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();

        dto.setThisValue(thisQuantityDTO.getValue());
        dto.setThisUnit(thisQuantityDTO.getUnit());
        dto.setThisMeasurementType(thisQuantityDTO.getMeasurementType());

        dto.setThatValue(thatQuantityDTO.getValue());
        dto.setThatUnit(thatQuantityDTO.getUnit());
        dto.setThatMeasurementType(thatQuantityDTO.getMeasurementType());

        dto.setOperation(operation);

        return dto;
    }

	private void setSuccess(
        QuantityMeasurementDTO measurementDTO,
        double resultValue,
        String resultUnit,
        String resultMeasurementType,
        String resultString) {

        measurementDTO.setResultValue(resultValue);
        measurementDTO.setResultUnit(resultUnit);
        measurementDTO.setResultMeasurementType(resultMeasurementType);
        measurementDTO.setResultString(resultString);
        measurementDTO.setError(false);
        measurementDTO.setErrorMessage(null);
	}

	 
	private void setError(QuantityMeasurementDTO measurementDTO, Exception e) {
	    measurementDTO.setError(true);
	    measurementDTO.setErrorMessage(e.getMessage());
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

    private <U extends IMeasurable> void validateSameCategory(
            QuantityModel<U> firstQuantity,
            QuantityModel<U> secondQuantity) {

        if (firstQuantity == null || secondQuantity == null) {
            throw new IllegalArgumentException("Operands cannot be null");
        }

        if (!firstQuantity.getUnit().getClass().equals(secondQuantity.getUnit().getClass())) {
            throw new IllegalArgumentException("Both quantities must belong to same measurement category");
        }
    }

    private <U extends IMeasurable> Quantity<U> performArithmetic(
            Quantity<U> q1,
            Quantity<U> q2,
            Quantity<U> targetQuantity,
            ArithmeticOperation operation) {

        switch (operation) {
            case ADD:
                return q1.add(q2, targetQuantity.getUnit());  

            case SUBTRACT:
                return q1.subtract(q2, targetQuantity.getUnit());

            default: 
                throw new IllegalArgumentException("Unsupported arithmetic operation");
        }
    }

    private enum ArithmeticOperation {
        ADD,
        SUBTRACT
    }
	
	
	// Helper method for addition	
    private <U extends IMeasurable> void validateArithmeticOperands(
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