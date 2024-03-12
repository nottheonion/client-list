package com.clientlist.location.service;

import com.clientlist.location.model.Location;
import com.clientlist.location.repository.LocationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Transactional
    public Location createLocation(Location location) {
        try {
            return locationRepository.save(location);
        } catch (Exception e) {
            throw new RuntimeException("Error creating location", e);
        }
    }
}
