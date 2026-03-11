package com.apps.quantitymeasurementapp.repository;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import com.apps.quantitymeasurementapp.entity.QuantityMeasurementEntity;

public class QuantityMeasurementCacheRepository implements IQuantityMeasurementRepository {
	
	// File where data will be stored
    public static final String FILE_NAME = "quantity_measurement_repo.ser";

    // List to store entities in memory
    List<QuantityMeasurementEntity> quantityMeasurementEntityCache;

	public Object lastSavedEntity;

    // Singleton instance
    private static QuantityMeasurementCacheRepository instance;

 
    public QuantityMeasurementCacheRepository() {
        quantityMeasurementEntityCache = new ArrayList<>();

        // Load existing data from disk
        loadFromDisk();
    }

    // Get single instance of repository
    public static QuantityMeasurementCacheRepository getInstance() {
        if (instance == null) {
            instance = new QuantityMeasurementCacheRepository();
        }
        return instance;
    }

    // Save entity in memory and disk
    @Override
    public void save(QuantityMeasurementEntity entity) {

        // Add entity to memory cache
        quantityMeasurementEntityCache.add(entity);

        // Save entity to disk
        saveToDisk(entity);
    }

    // Return all stored entities
    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {

        // Return cached list
        return quantityMeasurementEntityCache;
    }

    // Save entity to file
    private void saveToDisk(QuantityMeasurementEntity entity) {

        try {
            FileOutputStream fos = new FileOutputStream(FILE_NAME, true);
            AppendableObjectOutputStream oos = new AppendableObjectOutputStream(fos);

            // Write object to file
            oos.writeObject(entity);

            oos.close();
            fos.close();

        } catch (IOException e) {
            System.err.println("Error saving entity: " + e.getMessage());
        }
    }

    // Load entities from disk when repository starts
    private void loadFromDisk() {

        File file = new File(FILE_NAME);

        // If file does not exist, nothing to load
        if (!file.exists()) {
            return;
        }

        try {
            FileInputStream fis = new FileInputStream(FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    QuantityMeasurementEntity entity =
                            (QuantityMeasurementEntity) ois.readObject();

                    // Add entity to cache
                    quantityMeasurementEntityCache.add(entity);

                } catch (EOFException e) {
                    // End of file reached
                    break;
                }
            }

            ois.close();
            fis.close();

//            System.out.println("Loaded " +
//                    quantityMeasurementEntityCache.size() +
//                    " quantity measurement entities from storage");

        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(
                    "Error loading quantity measurement entities: " +
                            ex.getMessage()
            );
        }
    }

}