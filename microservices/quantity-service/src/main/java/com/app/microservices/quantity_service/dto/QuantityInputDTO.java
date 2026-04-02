package com.app.microservices.quantity_service.dto;

import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.Data;

@Data
@Schema(example = """ 
	{
		"thisQuantityDTO": {"value: 1.0, "unit": "FEET", "measurementType": "LengthUnit"},
		"thatQuantityDTO": {"value: 12.0, "unit": "FEET", "measurementType": "LengthUnit"},
		"thisQuantityDTO": {"value: 0.0, "unit": "FEET", "measurementType": "LengthUnit"}
	}
	"""	
)
public class QuantityInputDTO {
	
	@Valid
	@NotNull(message = "First Quantity DTO can not be null")
	public QuantityDTO thisQuantityDTO;
	
	@Valid
	@NotNull(message = "Second Quantity DTO can not be null")
	public QuantityDTO thatQuantityDTO;

	@Valid 
	public QuantityDTO targetQuantityDTO;

}