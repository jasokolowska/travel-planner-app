package com.sokolowska.travelplannerapi.service;

import com.sokolowska.travelplannerapi.webclient.geoapi.GeoApiService;
import com.sokolowska.travelplannerapi.model.Airport;
import com.sokolowska.travelplannerapi.model.Place;
import com.sokolowska.travelplannerapi.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final GeoApiService geoApiService;
    private final AirportService airportService;
    private final PlaceRepository placeRepository;

    public List<Airport> findAirports(Place place){
        double[] coordinates = geoApiService.findCoordinates(place.getName());
        place.setLatitude(coordinates[0]);
        place.setLongitude(coordinates[1]);

        List<Airport> airports = airportService.getAirports(coordinates[0], coordinates[1]);
        place.setAirports(airports);
        placeRepository.save(place);
        return airports;
    }
}
