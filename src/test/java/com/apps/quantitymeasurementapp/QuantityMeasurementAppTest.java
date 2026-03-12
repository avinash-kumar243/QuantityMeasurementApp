package com.apps.quantitymeasurementapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.apps.quantitymeasurementapp.entity.QuantityDTO;
import com.apps.quantitymeasurementapp.entity.QuantityModel;
import com.apps.quantitymeasurementapp.repository.QuantityMeasurementCacheRepository;
import com.apps.quantitymeasurementapp.service.QuantityMeasurementServiceImpl;
import com.apps.quantitymeasurementapp.unit.IMeasurable;
import com.apps.quantitymeasurementapp.unit.*;


public class QuantityMeasurementAppTest {
	 private QuantityMeasurementServiceImpl service;
	    private QuantityMeasurementCacheRepository repository;

	    @BeforeEach
	    
	    void setUp() {
	        repository = new QuantityMeasurementCacheRepository();
	        service = new QuantityMeasurementServiceImpl(repository);
	    }

	    // ----------------------------------------------------
	    // DTO TESTS
	    // ----------------------------------------------------

	    @Test
	    void testQuantityDTO_Constructor_WithIMeasurable() {
	        QuantityDTO dto = new QuantityDTO(1.0, LengthUnit.FEET);

	        assertEquals(1.0, dto.getValue());
	        assertEquals("FEET", dto.getUnit());
	        assertEquals("LengthUnit", dto.getMeasurementType());
	    }

	    @Test
	    void testQuantityDTO_Constructor_WithStrings() {
	        QuantityDTO dto = new QuantityDTO(2.0, "GRAM", "WeightUnit");

	        assertEquals(2.0, dto.getValue());
	        assertEquals("GRAM", dto.getUnit());
	        assertEquals("WeightUnit", dto.getMeasurementType());
	    }

	    @Test
	    void testQuantityDTO_ToString() {
	        QuantityDTO dto = new QuantityDTO(1.0, "LITRE", "VolumeUnit");
	        String text = dto.toString();

	        assertTrue(text.contains("1.0"));
	        assertTrue(text.contains("LITRE"));
	        assertTrue(text.contains("VolumeUnit"));
	    }

	    // ----------------------------------------------------
	    // MODEL TESTS
	    // ----------------------------------------------------

	    @Test
	    void testQuantityModel_Construction() {
	        QuantityModel<IMeasurable> model = new QuantityModel<>(5.0, LengthUnit.INCHES);

	        assertEquals(5.0, model.getValue());
	        assertEquals(LengthUnit.INCHES, model.getUnit());
	    }

	    @Test
	    void testQuantityModel_ToString() {
	        QuantityModel<IMeasurable> model = new QuantityModel<>(10.0, WeightUnit.KILOGRAM);
	        String text = model.toString();

	        assertTrue(text.contains("10.0"));
	        assertTrue(text.contains("KILOGRAM"));
	    }

	    // ----------------------------------------------------
	    // SERVICE - COMPARE
	    // ----------------------------------------------------

	    @Test
	    void testService_CompareEquality_SameUnit_Success() {
	        QuantityDTO q1 = new QuantityDTO(1.0, LengthUnit.FEET);
	        QuantityDTO q2 = new QuantityDTO(1.0, LengthUnit.FEET);

	        boolean result = service.compare(q1, q2);

	        assertTrue(result);
	    }

	    @Test
	    void testService_CompareEquality_DifferentUnit_Success() {
	        QuantityDTO q1 = new QuantityDTO(1.0, LengthUnit.FEET);
	        QuantityDTO q2 = new QuantityDTO(12.0, LengthUnit.INCHES);

	        boolean result = service.compare(q1, q2);

	        assertTrue(result);
	    }

	    @Test
	    void testService_CompareEquality_CrossCategory_Error() {
	        QuantityDTO q1 = new QuantityDTO(1.0, LengthUnit.FEET);
	        QuantityDTO q2 = new QuantityDTO(1.0, VolumeUnit.LITRE);

	        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
	                () -> service.compare(q1, q2));

	        assertEquals("Operation not possible for two different measurement types!!!", ex.getMessage());
	    }

	    // ----------------------------------------------------
	    // SERVICE - CONVERT
	    // ----------------------------------------------------

	    @Test
	    void testService_Convert_Success() {
	        QuantityDTO source = new QuantityDTO(1.0, LengthUnit.FEET);
	        QuantityDTO target = new QuantityDTO(0.0, LengthUnit.INCHES);

	        QuantityDTO result = service.convert(source, target);

	        assertEquals(12.0, result.getValue(), 0.01);
	        assertEquals("INCHES", result.getUnit());
	        assertEquals("LengthUnit", result.getMeasurementType());
	    }

	    // ----------------------------------------------------
	    // SERVICE - ADD
	    // ----------------------------------------------------


	    @Test
	    void testService_Add_WithTargetUnit_Success() {
	        QuantityDTO q1 = new QuantityDTO(1.0, LengthUnit.FEET);
	        QuantityDTO q2 = new QuantityDTO(12.0, LengthUnit.INCHES);
	        QuantityDTO target = new QuantityDTO(0.0, LengthUnit.INCHES);

	        QuantityDTO result = service.add(q1, q2, target);

	        assertEquals(24.0, result.getValue(), 0.01);
	        assertEquals("INCHES", result.getUnit());
	        assertEquals("LengthUnit", result.getMeasurementType());
	    }

	    @Test
	    void testService_Add_CrossCategory_Error() {
	        QuantityDTO q1 = new QuantityDTO(1.0, LengthUnit.FEET);
	        QuantityDTO q2 = new QuantityDTO(1.0, WeightUnit.KILOGRAM);

	        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
	                () -> service.add(q1, q2));

	        assertEquals("Operation not possible for two different measurement types!!!", ex.getMessage());
	    }

	    // ----------------------------------------------------
	    // SERVICE - SUBTRACT
	    // ----------------------------------------------------


	    @Test
	    void testService_Subtract_WithTargetUnit_Success() {
	        QuantityDTO q1 = new QuantityDTO(5.0, WeightUnit.KILOGRAM);
	        QuantityDTO q2 = new QuantityDTO(2000.0, WeightUnit.GRAM);
	        QuantityDTO target = new QuantityDTO(0.0, WeightUnit.GRAM);

	        QuantityDTO result = service.subtract(q1, q2, target);

	        assertEquals(3000.0, result.getValue(), 0.01);
	        assertEquals("GRAM", result.getUnit());
	        assertEquals("WeightUnit", result.getMeasurementType());
	    }

	    // ----------------------------------------------------
	    // SERVICE - DIVIDE
	    // ----------------------------------------------------

	    @Test
	    void testService_Divide_Success() {
	        QuantityDTO q1 = new QuantityDTO(2.0, VolumeUnit.LITRE);
	        QuantityDTO q2 = new QuantityDTO(1000.0, VolumeUnit.MILLILITRE);

	        QuantityDTO result = service.divide(q1, q2);

	        assertEquals(2.0, result.getValue(), 0.01);
	        assertEquals("LITRE", result.getUnit());
	        assertEquals("VolumeUnit", result.getMeasurementType());
	    }

	    @Test
	    void testService_Divide_ByZero_Error() {
	        QuantityDTO q1 = new QuantityDTO(10.0, LengthUnit.FEET);
	        QuantityDTO q2 = new QuantityDTO(0.0, LengthUnit.FEET);

	        ArithmeticException ex = assertThrows(ArithmeticException.class,
	                () -> service.divide(q1, q2));

	        assertEquals("Cannot divide by zero!!!", ex.getMessage());
	    }

	    // ----------------------------------------------------
	    // SERVICE - VALIDATION
	    // ----------------------------------------------------

	    @Test
	    void testService_NullEntity_Rejection() {
	        QuantityDTO q = new QuantityDTO(1.0, LengthUnit.FEET);

	        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
	                () -> service.add(null, q));

	        assertEquals("QuantityDTO objects can't be null!!!", ex.getMessage());
	    }

	    @Test
	    void testService_ValidationConsistency() {
	        QuantityDTO q = new QuantityDTO(1.0, LengthUnit.FEET);

	        Exception ex1 = assertThrows(IllegalArgumentException.class, () -> service.add(null, q));
	        Exception ex2 = assertThrows(IllegalArgumentException.class, () -> service.subtract(null, q));
	        Exception ex3 = assertThrows(IllegalArgumentException.class, () -> service.divide(null, q));

	        assertEquals(ex1.getMessage(), ex2.getMessage());
	        assertEquals(ex2.getMessage(), ex3.getMessage());
	    }

	    // ----------------------------------------------------
	    // SERVICE - ALL CURRENT MEASUREMENT CATEGORIES
	    // ----------------------------------------------------

	    @Test
	    void testService_AllMeasurementCategories_CurrentlySupported() {
	        assertTrue(service.compare(
	                new QuantityDTO(1.0, LengthUnit.FEET),
	                new QuantityDTO(12.0, LengthUnit.INCHES)));

	        assertTrue(service.compare(
	                new QuantityDTO(1.0, WeightUnit.KILOGRAM),
	                new QuantityDTO(1000.0, WeightUnit.GRAM)));

	        assertTrue(service.compare(
	                new QuantityDTO(1.0, VolumeUnit.LITRE),
	                new QuantityDTO(1000.0, VolumeUnit.MILLILITRE)));
	    }

	    @Test
	    void testService_Temperature_CurrentlyNotSupportedInService() {
	        QuantityDTO t1 = new QuantityDTO(0.0, "CELSIUS", "TemperatureUnit");
	        QuantityDTO t2 = new QuantityDTO(32.0, "FAHRENHEIT", "TemperatureUnit");

	        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
	                () -> service.compare(t1, t2));

	        assertEquals("Invalid measurement type or unit!!!", ex.getMessage());
	    }
}
