package com.sokolowska.travelplannerapi.service;

import com.sokolowska.travelplannerapi.google.GeoApiService;
import com.sokolowska.travelplannerapi.model.Airport;
import com.sokolowska.travelplannerapi.model.Place;
import com.sokolowska.travelplannerapi.repository.PlaceRepository;
import com.sokolowska.travelplannerapi.tequila.AirportClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final GeoApiService geoApiService;
    private final AirportService airportService;
    private final PlaceRepository placeRepository;

    public Place setCoordinatesAndAirports(Place place){
        double[] coordinates = geoApiService.findCoordinates(place.getName());
        List<Airport> airports = airportService.getAirports(coordinates[0], coordinates[1]);
        place.setLatitude(coordinates[0]);
        place.setLongitude(coordinates[1]);
        place.setAirports(airports);
        return placeRepository.save(place);
    }
}
