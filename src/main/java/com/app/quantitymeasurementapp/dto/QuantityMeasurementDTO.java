package com.app.quantitymeasurementapp.dto;

import java.util.ArrayList;
import java.util.List;

import com.app.quantitymeasurementapp.entity.QuantityMeasurementEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// The QuantityMeasurementDTO class will be created to serve as a DTO for the QuantityMeasurementEntity. 

/* 
 * The QuantityMeasurementDTO class will be used in the REST controllers to get Quantity Measurement History by operation type
 * and to get Quantity Measurements by measurement type. It will also be used in the service layer for performing quantity
 * measurement operations and returning results to the controllers.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor 
public class QuantityMeasurementDTO {
	
	@Positive(message = "Value must be positive")
	@NotNull(message = "value can not be null") 
	public Double thisValue;
	@NotNull(message = "unit must be not null")
	public String thisUnit;
	@NotNull(message = "measurementType must be not null")
	public String thisMeasurementType;
	
	@Positive(message = "Value must be positive")
	@NotNull(message = "value can not be null") 
	public Double thatValue;
	@NotNull(message = "unit must be not null")
	public String thatUnit;
	@NotNull(message = "measurementType must be not null")
	public String thatMeasurementType;
	
	@NotNull(message = "operation must be not null") 
	@NotBlank(message = "operation can not be blank")
	public String operation;
	
	public Double resultValue;
	public String resultUnit;
	public String resultMeasurementType;
	
	public String resultString;
	public String errorMessage;
	
	@JsonProperty("error")
	public boolean error;

	
	public QuantityMeasurementDTO from(QuantityMeasurementEntity entity) {
		return new QuantityMeasurementDTO(entity.getThisValue(), entity.getThisUnit(), entity.getThisMeasurementType(), entity.getThatValue(), entity.getThatUnit(), entity.getThatMeasurementType(), entity.getOperation(), entity.getResultValue(), entity.getResultUnit(), entity.getResultMeasurementType(), entity.getResultString(), entity.getErrorMessage(), entity.isError());
	} 
	
	public QuantityMeasurementEntity toEntity() {
	    return new QuantityMeasurementEntity(thisValue, thisUnit, thisMeasurementType, thatValue, thatUnit, thatMeasurementType, operation, resultValue, resultUnit, resultMeasurementType, resultString, error, errorMessage);
	}
		
	public List<QuantityMeasurementDTO> fromEntityList(List<QuantityMeasurementEntity> entities) {
		List<QuantityMeasurementDTO> dto = new ArrayList<>();
		for(QuantityMeasurementEntity entity : entities) {
			dto.add(from(entity));
		}
		return dto;
	}
	
	
	public List<QuantityMeasurementEntity> toEntityList(List<QuantityMeasurementDTO> dtoList) {

		List<QuantityMeasurementEntity> entityList = new ArrayList<>();
		
		for(QuantityMeasurementDTO dto : dtoList) { 
			entityList.add(dto.toEntity());
		}		
		return entityList; 
	}

}