package com.nighthawk.spring_portfolio.mvc.astronomy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CelestialDataService {

    private final CelestialDataRepository celestialDataRepository;

    @Autowired
    public CelestialDataService(CelestialDataRepository celestialDataRepository) {
        this.celestialDataRepository = celestialDataRepository;
    }

    // Method to store celestial data in the database
    public CelestialData storeData(CelestialData celestialData) {
        return celestialDataRepository.save(celestialData);
    }

    // Method to fetch all celestial data from the database
    public List<CelestialData> getAllData() {
        Iterable<CelestialData> iterable = celestialDataRepository.findAll();
        List<CelestialData> dataList = new ArrayList<>();
        iterable.forEach(dataList::add);
        return dataList;
    }


    // Method to fetch celestial data by ID
    public CelestialData getDataById(String id) {
        return celestialDataRepository.findById(id).orElse(null);
    }

    // Method to update celestial data
    public CelestialData updateData(Long id, CelestialData updatedData) {
        if (celestialDataRepository.existsById(id)) {
            updatedData.setId(id);
            return celestialDataRepository.save(updatedData);
        }
        return null; // Data with the provided ID does not exist
    }

    // Method to delete celestial data by ID
    public boolean deleteData(Long id) {
        if (celestialDataRepository.existsById(id)) {
            celestialDataRepository.deleteById(id);
            return true;
        }
        return false; // Data with the provided ID does not exist
    }
}

