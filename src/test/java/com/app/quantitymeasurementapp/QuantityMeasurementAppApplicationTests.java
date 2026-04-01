package com.app.quantitymeasurementapp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.app.quantitymeasurementapp.dto.QuantityDTO;
import com.app.quantitymeasurementapp.dto.QuantityInputDTO;
import com.app.quantitymeasurementapp.dto.QuantityMeasurementDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class QuantityMeasurementAppApplicationTests {
 
	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper objectMapper = new ObjectMapper();

	private String baseUrl() {
		return "/api/v1/quantities";
	}

	private QuantityInputDTO input(double thisValue, String thisUnit, String thisMeasurementType,
								   double thatValue, String thatUnit, String thatMeasurementType) {
		QuantityInputDTO inputDTO = new QuantityInputDTO();

		inputDTO.setThisQuantityDTO(new QuantityDTO(thisValue, thisUnit, thisMeasurementType));
		inputDTO.setThatQuantityDTO(new QuantityDTO(thatValue, thatUnit, thatMeasurementType));

		return inputDTO;
	}

	private QuantityInputDTO inputWithTarget(double thisValue, String thisUnit, String thisMeasurementType,
											 double thatValue, String thatUnit, String thatMeasurementType,
											 double targetValue, String targetUnit, String targetMeasurementType) {
		QuantityInputDTO inputDTO = new QuantityInputDTO();

		inputDTO.setThisQuantityDTO(new QuantityDTO(thisValue, thisUnit, thisMeasurementType));
		inputDTO.setThatQuantityDTO(new QuantityDTO(thatValue, thatUnit, thatMeasurementType));
		inputDTO.setTargetQuantityDTO(new QuantityDTO(targetValue, targetUnit, targetMeasurementType));

		return inputDTO;
	}

	@Test
	@Order(1)
	@DisplayName("Application context loads")
	void contextLoads() {
		assertThat(mockMvc).isNotNull();
	}

	@Test
	@Order(2)
	@DisplayName("POST /compare - 1 foot equals 12 inches - result is true")
	void testCompare_FootEqualsInches() throws Exception {
		QuantityInputDTO body = input(
				1.0, "FEET", "LengthUnit",
				12.0, "INCHES", "LengthUnit"
		);

		String response = mockMvc.perform(post(baseUrl() + "/compare")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(body)))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		QuantityMeasurementDTO result = objectMapper.readValue(response, QuantityMeasurementDTO.class);
		assertCompareResult(result, true);
	}

	@Test
	@Order(3)
	@DisplayName("POST /compare - 1 foot does NOT equal 1 inch - result is false")
	void testCompare_FootNotEqualInch() throws Exception {
		QuantityInputDTO body = input(
				1.0, "FEET", "LengthUnit",
				1.0, "INCHES", "LengthUnit"
		);

		String response = mockMvc.perform(post(baseUrl() + "/compare")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(body)))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		QuantityMeasurementDTO result = objectMapper.readValue(response, QuantityMeasurementDTO.class);
		assertCompareResult(result, false);
	}

	@Test
	@Order(4)
	@DisplayName("POST /compare - 212 Fahrenheit equals 100 Celsius - result is true")
	void testCompare_FahrenheitEqualsCelsius() throws Exception {
		QuantityInputDTO body = input(
				212.0, "FAHRENHEIT", "TemperatureUnit",
				100.0, "CELSIUS", "TemperatureUnit"
		);

		String response = mockMvc.perform(post(baseUrl() + "/compare")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(body)))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		QuantityMeasurementDTO result = objectMapper.readValue(response, QuantityMeasurementDTO.class);
		assertCompareResult(result, true);
	}

	@Test
	@Order(5)
	@DisplayName("POST /convert - convert 100 Celsius to Fahrenheit")
	void testConvert_CelsiusToFahrenheit() throws Exception {
		QuantityInputDTO body = input(
				100.0, "CELSIUS", "TemperatureUnit",
				0.0, "FAHRENHEIT", "TemperatureUnit"
		);

		String response = mockMvc.perform(post(baseUrl() + "/convert")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(body)))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		QuantityMeasurementDTO result = objectMapper.readValue(response, QuantityMeasurementDTO.class);
		assertThat(result).isNotNull();
		assertThat(result.getResultValue()).isEqualTo(212.0);
	}

	@Test
	@Order(6)
	@DisplayName("POST /add-with-target-unit - add 1 foot + 12 inches, target INCH = 24 inches")
	void testAddWithTargetUnit_FootAndInchesToInches() throws Exception {
		QuantityInputDTO body = inputWithTarget(
				1.0, "FEET", "LengthUnit",
				12.0, "INCHES", "LengthUnit",
				0.0, "INCHES", "LengthUnit"
		);

		String response = mockMvc.perform(post(baseUrl() + "/add-with-target-unit")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(body)))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		QuantityMeasurementDTO result = objectMapper.readValue(response, QuantityMeasurementDTO.class);
		assertThat(result).isNotNull();
		assertThat(result.getResultValue()).isEqualTo(24.0);
	}

	@Test
	@Order(7)
	@DisplayName("POST /subtract - subtract 12 inches from 2 feet = 1 foot")
	void testSubtract_FeetMinusInches() throws Exception {
		QuantityInputDTO body = input(
				2.0, "FEET", "LengthUnit",
				12.0, "INCHES", "LengthUnit"
		);

		String response = mockMvc.perform(post(baseUrl() + "/subtract")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(body)))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		QuantityMeasurementDTO result = objectMapper.readValue(response, QuantityMeasurementDTO.class);
		assertThat(result).isNotNull();
		assertThat(result.getResultValue()).isEqualTo(1.0);
	}

	@Test
	@Order(8)
	@DisplayName("POST /subtract-with-target-unit - 2 feet minus 12 inches, target INCH = 12 inches")
	void testSubtractWithTargetUnit() throws Exception {
		QuantityInputDTO body = inputWithTarget(
				2.0, "FEET", "LengthUnit",
				12.0, "INCHES", "LengthUnit",
				0.0, "INCHES", "LengthUnit"
		);

		String response = mockMvc.perform(post(baseUrl() + "/subtract-with-target-unit")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(body)))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		QuantityMeasurementDTO result = objectMapper.readValue(response, QuantityMeasurementDTO.class);
		assertThat(result).isNotNull();
		assertThat(result.getResultValue()).isEqualTo(12.0);
	}

	@Test
	@Order(9)
	@DisplayName("POST /divide - 1 yard divided by 1 foot = 3.0")
	void testDivide_YardByFoot() throws Exception {
		QuantityInputDTO body = input(
				1.0, "YARDS", "LengthUnit",
				1.0, "FEET", "LengthUnit"
		);

		String response = mockMvc.perform(post(baseUrl() + "/divide")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(body)))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		QuantityMeasurementDTO result = objectMapper.readValue(response, QuantityMeasurementDTO.class);
		assertThat(result).isNotNull();
		assertThat(result.getResultValue()).isEqualTo(3.0);
	}

	@Test
	@Order(10)
	@DisplayName("GET /history/operation/CONVERT - returns list of CONVERT operations")
	void testGetHistoryByOperation_Convert() throws Exception {
		String response = mockMvc.perform(get(baseUrl() + "/history/operation/CONVERT")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		assertThat(response).isNotNull();
		assertThat(response).isNotBlank();
		assertThat(response).isNotEqualTo("[]");
	}

	@Test
	@Order(11)
	@DisplayName("GET /history/type/TemperatureUnit - returns history for TemperatureUnit measurements")
	void testGetHistoryByType_Temperature() throws Exception {
		String response = mockMvc.perform(get(baseUrl() + "/history/type/TemperatureUnit")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		assertThat(response).isNotNull();
		assertThat(response).isNotBlank();
		assertThat(response).isNotEqualTo("[]");
	}

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