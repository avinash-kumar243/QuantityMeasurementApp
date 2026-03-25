package com.app.quantitymeasurementapp;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import com.app.quantitymeasurementapp.dto.QuantityDTO;
import com.app.quantitymeasurementapp.dto.QuantityInputDTO;
import com.app.quantitymeasurementapp.dto.QuantityMeasurementDTO;

import static org.assertj.core.api.Assertions.within;


/**
* Full Integration Tests for Quantity Measurement REST API
* 
* Base URL : /api/v1/quantities
* Endpoints:
* POST /compare
* POST /convert
* POST /add
* POST /add-with-target-unit
* POST /subtract
* POST /subtract-with-target-unit
* POST /divide
* GET
* GET /history/type/{type}
* GET /count/{operation}
* GET /history/errored
* 
* Test order ensures operations are performed before history/count tests,
* so history endpoints have data to return.
*/


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class QuantityMeasurementAppApplicationTests {
	
	@LocalServerPort
	private int port;
	
	private TestRestTemplate restTemplate;
	
	@BeforeEach
	void setup() {
		restTemplate = new TestRestTemplate();
	}
	
	
	private String baseUrl() {
		return "http://localhost:" + port + "/api/v1/quantities";
	}
	
	
	
	/**
	* Builds a QuantityInputDTO with thisQuantity and thatQuantity only.
	* Used for compare, add, subtract, divide, convert.
	*/
	
	private QuantityInputDTO input(double thisValue, String thisUnit, String thisMeasurementType, double thatValue, String thatUnit, String thatMeasurementType) {
		QuantityInputDTO inputDTO = new QuantityInputDTO();
		
		inputDTO.setThisQuantityDTO(new QuantityDTO(thisValue, thisUnit, thisMeasurementType));
		inputDTO.setThatQuantityDTO(new QuantityDTO(thatValue, thatUnit, thatMeasurementType));

		return inputDTO; 
	}
	
	
	/**
	* Builds a QuantityInputDTO with thisQuantity, thatQuantity, and targetUnit.
	* Used for add-with-target-unit and subtract-with-target-unit.
	*/
	
	private QuantityInputDTO inputWithTarget(double thisValue, String thisUnit, String thisMeasurementType, double thatValue, String thatUnit, String thatMeasurementType,  double targetValue, String targetUnit, String targetMeasurementType) {
		QuantityInputDTO inputDTO = new QuantityInputDTO();
		
		inputDTO.setThisQuantityDTO(new QuantityDTO(thisValue, thisUnit, thisMeasurementType));
		inputDTO.setThatQuantityDTO(new QuantityDTO(thatValue, thatUnit, thatMeasurementType));
		inputDTO.setTargetQuantityDTO(new QuantityDTO(targetValue, targetUnit, targetMeasurementType));

		return inputDTO; 
	}	
	
	private HttpEntity<QuantityInputDTO> jsonEntity(QuantityInputDTO body) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<>(body, headers); 
	}
	
	
	
	// --------------------------------- 1. Context ---------------------------------------- 
	
	@Test
	@Order(1)
	@DisplayName("Application context loads and test server starts")
	void contextLoads() {
		assertThat(restTemplate).isNotNull();
		assertThat(port).isGreaterThan(0);
		System.out.println("Test server on port: " + port); 
	}
	
	

	// ------------------------------ 2. POST / Compare ------------------------------------ 
	@Test
	@Order(2)
	@DisplayName("POST /compare - 1 foot equals 12 inches - result is true")
	void testCompare_FootEqualsInches() {
	    QuantityInputDTO body = input(
	            1.0, "FEET", "LengthUnit",
	            12.0, "INCHES", "LengthUnit"
	    );

	    ResponseEntity<QuantityMeasurementDTO> response = restTemplate.exchange(
	            baseUrl() + "/compare",
	            HttpMethod.POST,
	            jsonEntity(body),
	            QuantityMeasurementDTO.class
	    );

	    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	    assertCompareResult(response.getBody(), true);
	}

	@Test
	@Order(3)
	@DisplayName("POST /compare - 1 foot does NOT equal 1 inch - result is false")
	void testCompare_FootNotEqualInch() {
	    QuantityInputDTO body = input(
	            1.0, "FEET", "LengthUnit",
	            1.0, "INCHES", "LengthUnit"
	    );

	    ResponseEntity<QuantityMeasurementDTO> response = restTemplate.exchange(
	            baseUrl() + "/compare",
	            HttpMethod.POST,
	            jsonEntity(body),
	            QuantityMeasurementDTO.class
	    );

	    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	    assertCompareResult(response.getBody(), false);
	}
	
	
	@Test
	@Order(4)
	@DisplayName("POST /compare - 212 Fahrenheit equals 100 Celsius - result is true")
	void testCompare_FahrenheitEqualsCelsius() {
	    QuantityInputDTO body = input(
	            212.0, "FAHRENHEIT", "TemperatureUnit",
	            100.0, "CELSIUS", "TemperatureUnit"
	    );

	    ResponseEntity<QuantityMeasurementDTO> response = restTemplate.exchange(
	            baseUrl() + "/compare",
	            HttpMethod.POST,
	            jsonEntity(body),
	            QuantityMeasurementDTO.class
	    );

	    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	    assertCompareResult(response.getBody(), true);
	}
	
	
	
	// ------------------------------ 3. POST / convert ------------------------------------ 
	@Test
	@Order(5)
	@DisplayName("POST /convert - convert 100 Celsius to Fahrenheit")
	void testConvert_CelsiusToFahrenheit() {
		QuantityInputDTO body = input(
			 100.0, "CELSIUS", "TemperatureUnit",
			 0.0, "FAHRENHEIT", "TemperatureUnit"
		);
	
		ResponseEntity<QuantityMeasurementDTO> response = restTemplate.exchange(
			baseUrl() + "/convert", HttpMethod.POST, jsonEntity(body), QuantityMeasurementDTO. class
		);
	
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat (response.getBody()).isNotNull();
		// 100C = 212°F
		assertThat((Double) response.getBody().getResultValue()).isEqualTo(212.0);
	}
	
	
	
	// ------------------------------ 5. POST / add-with-target-unit ------------------------------------ 
	@Test
	@Order(6)
	@DisplayName("POST /add-with-target-unit - add 1 foot + 12 inches, target INCH = 24 inches")
	void testAddWithTargetUnit_FootAndInchesToInches() {
		QuantityInputDTO body = inputWithTarget(
			1.0, "FEET", "LengthUnit",
			12.0, "INCHES", "LengthUnit",
			0.0, "INCHES" , "LengthUnit"
		);
	
		ResponseEntity<QuantityMeasurementDTO> response = restTemplate.exchange(
			baseUrl() + "/add-with-target-unit",
			HttpMethod.POST,
			jsonEntity(body),
			QuantityMeasurementDTO.class
		);
	
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		// 1 foot = 12 inches + 12 inches = 24 inches
		assertThat((Double) response.getBody().getResultValue()).isEqualTo(24.0);
	}
	
	
	
	// ------------------------------ 6. POST / subtract ------------------------------------ 
	@Test
	@Order(7)
	@DisplayName("POST /subtract - subtract 12 inches from 2 feet = 1 foot")
	void testSubtract_FeetMinusInches() {
		QuantityInputDTO body = input(
			2.0, "FEET", "LengthUnit",
			12.0, "INCHES", "LengthUnit"
		);
		
		ResponseEntity<QuantityMeasurementDTO> response = restTemplate.exchange(
			baseUrl() + "/subtract",
			HttpMethod.POST,
			jsonEntity(body),
			QuantityMeasurementDTO.class
		);
	
		assertThat (response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		assertThat((Double) response.getBody().getResultValue()).isEqualTo(1.0);
	}
	
	
	
	// ------------------------------ 7. POST / subtract-with-target-unit ------------------------------------ 
	@Test
	@Order(8)
	@DisplayName("POST /subtract-with-target-unit - 2 feet minus 12 inches, target INCH = 12 inches")
	void testSubtractWithTargetUnit() {
		QuantityInputDTO body = inputWithTarget(
			2.0, "FEET", "LengthUnit",
			12.0, "INCHES", "LengthUnit",
			0.0, "INCHES", "LengthUnit"
		);
	
		ResponseEntity<QuantityMeasurementDTO> response = restTemplate.exchange(
			baseUrl() + "/subtract-with-target-unit", HttpMethod.POST, jsonEntity(body), QuantityMeasurementDTO.class
		);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		// 2 feet = 24 inches - 12 inches = 12 inches
		assertThat((Double) response.getBody().getResultValue()).isEqualTo(12.0);
	}
	
	
	
	// ------------------------------ 8. POST / divide ------------------------------------ 
	@Test
	@Order(9)
	@DisplayName("POST /divide - 1 yard divided by 1 foot = 3.0")
	void testDivide_YardByFoot() {
		QuantityInputDTO body = input(
			1.0, "YARDS", "LengthUnit",
			1.0, "FEET", "LengthUnit"
		);

		ResponseEntity<QuantityMeasurementDTO> response = restTemplate.exchange(
			baseUrl() + "/divide", HttpMethod.POST, jsonEntity(body), QuantityMeasurementDTO.class
		);
	
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat (response.getBody()). isNotNull();
		assertThat ((Double) response.getBody().getResultValue()).isEqualTo(3.0);
	}
	
	
	
	// ----------------------- 9. GET /history/operation/{operation} ---------------------- 
	// Run after operations so there is data in history

	@Test
	@Order(10)
	@DisplayName("GET /history/operation/CONVERT - "+
	"returns list of CONVERT operations")
	@SuppressWarnings("unchecked")
	void testGetHistoryByOperation_Convert() {
		ResponseEntity<List> response = restTemplate.getForEntity(
			 baseUrl() + "/history/operation/CONVERT", List.class
		);
	
		assertThat (response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody()).isNotEmpty();
	}
	
	
	
	// --------------------------- 10. GET /history/type/{type} ---------------------------

	@Test
	@Order(11)
	@DisplayName("GET /history/type/TemperatureUnit - " + "returns history for TemperatureUnit measurements")
	@SuppressWarnings("unchecked")
	void testGetHistoryByType_Temperature() {
		ResponseEntity<List> response = restTemplate.getForEntity(
				baseUrl() + "/history/type/TemperatureUnit", List.class
		);
	
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody()).isNotEmpty();
	}
		
	
	// ------------------------- 12. CREATE and GET /history/errored -------------------------
	// Helper method
	private void assertCompareResult(QuantityMeasurementDTO body, boolean expected) {
	    assertThat(body).isNotNull();
	    assertThat(body.isError()).isFalse();

	    if (expected) {
	        assertThat(body.getResultValue()).isEqualTo(1.0);
	    } else {
	        assertThat(body.getResultValue()).isEqualTo(0.0);
	    }
	}
}
