package com.app.quantitymeasurementapp.repository;

import java.util.List;

import com.app.quantitymeasurementapp.entity.QuantityMeasurementEntity;

public interface IQuantityMeasurementRepository {
	int getTotalCount();
	void save(QuantityMeasurementEntity entity);
	List<QuantityMeasurementEntity> getAllMeasurements();
}