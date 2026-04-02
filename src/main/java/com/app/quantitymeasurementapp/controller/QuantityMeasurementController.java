package com.app.quantitymeasurementapp.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.quantitymeasurementapp.dto.QuantityInputDTO;
import com.app.quantitymeasurementapp.dto.QuantityMeasurementDTO;
import com.app.quantitymeasurementapp.service.IQuantityMeasurementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Quantity Measurements", description = "REST API for quantity measurement operations")
@RestController
@RequestMapping("/api/v1/quantities") 
public class QuantityMeasurementController {
	
	// Private Logger for logging information in the controller
	private static final Logger logger = Logger.getLogger(QuantityMeasurementController.class.getName());

	 
	@Autowired
	private IQuantityMeasurementService service;
	
	
	private static final String EX_FEET_INCH = """
			{ "thisQuantityDTO": {"value":1.0, "unit":"YARDS", "measurementType":"LengthUnit"},
			  "thatQuantityDTO": {"value":12.0, "unit":"INCHES", "measurementType":"LengthUnit"} }""";
	
	private static final String EX_YARD_FEET = """
			{ "thisQuantityDTO": {"value":1.0, "unit":"YARDS", "measurementType":"LengthUnit"},
			  "thatQuantityDTO": {"value":3.0, "unit":"FEET", "measurementType":"LengthUnit"} }""";

	private static final String EX_GALLON_LITRE = """
	{ "thisQuantityDTO": {"value":1.0, "unit":"GALLON", "measurementType":"VolumeUnit"},
	  "thatQuantityDTO": {"value":3.785, "unit":"LITRE", "measurementType":"VolumeUnit"} }""";
	
	private static final String EX_TEMP = """
			{ "thisQuantityDTO": {"value":212.0,"unit":"FAHRENHEIT", "measurementType":"TemperatureUnit"},
			  "thatQuantityDTO": {"value": 100.0,"unit":"CELSIUS", "measurementType":"TemperatureUnit"} }""";

	private static final String EX_WITH_TARGET = """
			{ 
				"thisQuantityDTO": {"value":1.0, "unit":"FEET", "measurementType":"LengthUnit"},
				"thatQuantityDTO": {"value": 12.0, "unit":"INCHES", "measurementType":"LengthUnit"},
				"targetQuantityDTO": {"value":0.0, "unit":"INCHES", "measurementType":"LengthUnit"} 
			}""";

	
	
	
    // ----------------------- REST APIs --------------------------
	
	@PostMapping("/compare")
	@Operation(summary = "Compare two quantities", 
		requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
			content = @Content(examples = {
					@ExampleObject(name = "Feet = 12 Inches", value = EX_FEET_INCH),
					@ExampleObject(name = "Yard = 3 Feet", value = EX_YARD_FEET),
					@ExampleObject(name = "Gallon = Litres", value = EX_GALLON_LITRE),
					@ExampleObject(name = "212°F = 100C", value = EX_TEMP)
			})
		)
	)  
	public ResponseEntity<QuantityMeasurementDTO> performComparison(@RequestBody QuantityInputDTO dto) {
	    QuantityMeasurementDTO resultDto = service.compare(dto.getThisQuantityDTO(), dto.getThatQuantityDTO()); 
	    
	    return ResponseEntity.ok(resultDto); 
	}
	
	
	@PostMapping("/convert")
	@Operation(summary = "Convert quantity to target unit",
		requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
			content = @Content(examples = {
					@ExampleObject(name = "Feet -> Inches", value = EX_FEET_INCH),
					@ExampleObject(name = "Yard -> Feet", value = EX_YARD_FEET),
					@ExampleObject(name = "Gallon -> Litres", value = EX_GALLON_LITRE),
					@ExampleObject(name ="212°F -> 100C", value = EX_TEMP)
			})
		) 
	)
	public ResponseEntity<QuantityMeasurementDTO> performConversion(@Valid @RequestBody QuantityInputDTO inputDto) {
		QuantityMeasurementDTO resultDto = service.convert(inputDto.getThisQuantityDTO(), inputDto.getThatQuantityDTO());
		
		return ResponseEntity.ok(resultDto); 
	}
	
	
	
	@PostMapping("/add")
	@Operation (summary = "Add two quantities",
		requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
			content = @Content(examples = {
				@ExampleObject(name = "Feet + Inches", value = EX_FEET_INCH),
				@ExampleObject(name = "Yard + Feet", value = EX_YARD_FEET),
				@ExampleObject(name = "Gallon + Litres", value = EX_GALLON_LITRE)
			}) 
		)
	)
	public ResponseEntity<QuantityMeasurementDTO> performAddition(@Valid @RequestBody QuantityInputDTO inputDto) {
	    QuantityMeasurementDTO dto = service.add(inputDto.getThisQuantityDTO(), inputDto.getThatQuantityDTO());
	    
	    return ResponseEntity.ok(dto); 
	}

	
	
	@PostMapping("/add-with-target-unit")
	@Operation (summary = "Add two quantities with a target unit",
		requestBody = @io. swagger.v3.oas.annotations.parameters.RequestBody(
			content = @Content(examples = {
				@ExampleObject(name = "Feet + Inches with Target Unit", value = EX_WITH_TARGET)
			})
		)
	)
	public ResponseEntity<QuantityMeasurementDTO> performAdditionWithTargetUnit(@Valid @RequestBody QuantityInputDTO inputDto) {
		QuantityMeasurementDTO dto = service.add(inputDto.getThisQuantityDTO(), inputDto.getThatQuantityDTO(), inputDto.getTargetQuantityDTO());
		
		return ResponseEntity.ok(dto); 
	}

	
	
	
	@PostMapping("/subtract")
	@Operation (summary = "Subtract two quantities",
		requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
			content = @Content(examples = {
				@ExampleObject(name = "Feet - Inches", value = EX_FEET_INCH),
				@ExampleObject(name = "Yard - Feet", value = EX_YARD_FEET),
				@ExampleObject(name = "Gallon - Litres", value = EX_GALLON_LITRE)
			})
		)
	)
	public ResponseEntity<QuantityMeasurementDTO> performSubtraction(@Valid @RequestBody QuantityInputDTO inputDto) {
		QuantityMeasurementDTO dto = service.subtract(inputDto.getThisQuantityDTO(), inputDto.getThatQuantityDTO());
		
		return ResponseEntity.ok(dto); 
	}

	
	
	@PostMapping("/subtract-with-target-unit")
	@Operation (summary = "Subtract two quantities with target unit",
		requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
			content = @Content(examples = {
				@ExampleObject(name = "Feet - Inches with Target Unit", value = EX_WITH_TARGET)
			})
		)
	)
	public ResponseEntity<QuantityMeasurementDTO> performSubtractionWithTargetUnit(@Valid @RequestBody QuantityInputDTO inputDto) {
		QuantityMeasurementDTO dto = service.subtract(inputDto.getThisQuantityDTO(), inputDto.getThatQuantityDTO(), inputDto.getTargetQuantityDTO());
		
		return ResponseEntity.ok(dto);  
	}

	

	@PostMapping("/divide")
	@Operation(summary = "Divide two quantities",
		requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
			content = @Content(examples = {
				@ExampleObject(name = "Feet / Inches", value = EX_FEET_INCH),
				@ExampleObject(name = "Yard / Feet", value = EX_YARD_FEET),
				@ExampleObject(name = "Gallon / Litres", value = EX_GALLON_LITRE)
			})
		)
	)	
	public ResponseEntity<QuantityMeasurementDTO> performDivision(@Valid @RequestBody QuantityInputDTO quantityInputDTO) {
		QuantityMeasurementDTO dto = service.divide(quantityInputDTO.getThisQuantityDTO(), quantityInputDTO.getThatQuantityDTO());
		return ResponseEntity.ok(dto);  
	}
	
	
	
	
	@GetMapping("/history/operation/{operation}")
	@Operation(
		summary = "Get operation history",
		description = "Valid operations: ADD, SUBTRACT, MULTIPLY, DIVIDE, CONVERT"
	)
	public ResponseEntity<List<QuantityMeasurementDTO>> getOperationHistory(@PathVariable String operation) {
		return ResponseEntity.ok(service.getOperationHistory(operation)); 
	}
	
	
	
	@GetMapping("/history/type/{type}")
	@Operation(
		summary = "Get operation history by type",
		description = "Valid types: LengthUnit, VolumeUnit, WeightUnit, TemperatureUnit"
	)
	public ResponseEntity<List<QuantityMeasurementDTO>> getOperationHistoryByType(@PathVariable String type) {
		return ResponseEntity.ok(service.getMeasurementsByType(type)); 
	}
	
	
	
	@GetMapping("/count/{operation}")
	@Operation(
		summary = "Get operation count",
		description = "Valid operations: ADD, SUBTRACT, MULTIPLY, DIVIDE, CONVERT"
	)
	public ResponseEntity<Long> getOperationCount(@PathVariable String operation) {
		return ResponseEntity.ok(service.getOperationCount(operation));
	}
	
	
	
	@GetMapping("/history/errored")
	@Operation(summary = "Get errored operations history")
	public ResponseEntity<List<QuantityMeasurementDTO>> getErroredOperations() {
		return ResponseEntity.ok(service.getErrorHistory());  
	}
	
	
	@GetMapping("/history")
	@Operation(summary = "Get complete measurement history")
	public ResponseEntity<List<QuantityMeasurementDTO>> getAllHistory() {
	    return ResponseEntity.ok(service.getAllHistory());
	}
}