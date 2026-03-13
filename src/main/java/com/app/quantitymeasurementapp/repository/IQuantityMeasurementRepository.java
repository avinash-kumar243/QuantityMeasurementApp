package com.app.quantitymeasurementapp.repository;

import java.util.List;

import com.app.quantitymeasurementapp.entity.QuantityMeasurementEntity;

public interface IQuantityMeasurementRepository {
	void save(QuantityMeasurementEntity entity);
	List<QuantityMeasurementEntity> getAllMeasurements();
}