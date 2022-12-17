package com.sokolowska.travelplannerapi.service;

import com.sokolowska.travelplannerapi.webclient.geoapi.GeoApiService;
import com.sokolowska.travelplannerapi.model.Airport;
import com.sokolowska.travelplannerapi.model.Place;
import com.sokolowska.travelplannerapi.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaceService {

    private final GeoApiService geoApiService;
    private final AirportService airportService;
    private final PlaceRepository placeRepository;

    public List<Airport> findAirports(Place place) {
        double[] coordinates = geoApiService.findCoordinates(place.getName());
        place.setLatitude(coordinates[0]);
        place.setLongitude(coordinates[1]);

        List<Airport> airports = airportService.getAirports(coordinates[0], coordinates[1]);
        place.setAirports(airports);
        placeRepository.save(place);
        return airports;
    }

    public List<Airport> findAirports(String placeName) {
        double[] coordinates = geoApiService.findCoordinates(placeName);
        List<Airport> airports = airportService.getAirports(coordinates[0], coordinates[1]);

        Place place = Place.builder()
                .name(placeName)
                .latitude(coordinates[0])
                .longitude(coordinates[1])
                .airports(airports).build();

        placeRepository.save(place);
        return airports;
    }

    public void saveAll(List<Place> places) {
        for (Place place : places) {
            placeRepository.save(place);
        }
    }
}
