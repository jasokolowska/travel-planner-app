package com.sokolowska.airport.impl.service;



import com.sokolowska.airport.api.service.PlaceService;
import com.sokolowska.airport.impl.model.model.Airport;
import com.sokolowska.airport.impl.model.model.AirportType;
import com.sokolowska.airport.impl.model.model.Location;
import com.sokolowska.airport.impl.repository.PlaceRepository;
import com.sokolowska.airport.impl.webclient.geoapi.GeoApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaceServiceImpl implements PlaceService {

    private final GeoApiService geoApiService;
    private final AirportServiceImpl airportService;
    private final PlaceRepository placeRepository;

    @Override
    public List<String> findAirportCodesByLocation(String locationName) {
        return null;
    }

    public List<Airport> findAirports(Location place, AirportType airportType) {
        double[] coordinates = geoApiService.findCoordinates(place.getName());
        place.setLatitude(coordinates[0]);
        place.setLongitude(coordinates[1]);

        List<Airport> airports = airportService.getAirports(coordinates[0], coordinates[1], airportType);
        place.setAirports(airports);
        placeRepository.save(place);
        return airports;
    }

    public List<Airport> findAirports(String placeName, AirportType airportType) {

        Optional<Location> place = placeRepository.findByName(placeName);

        if (place.isPresent() && place.get().getAirports().size() != 0){
            return place.get().getAirports();
        } else {
            return getAirportList(placeName, airportType);
        }
    }

    private List<Airport> getAirportList(String placeName, AirportType airportType) {
        double[] coordinates = geoApiService.findCoordinates(placeName);
        List<Airport> airports = airportService.getAirports(coordinates[0], coordinates[1], airportType);

        Location place = Location.builder()
                .name(placeName)
                .latitude(coordinates[0])
                .longitude(coordinates[1])
                .airports(airports).build();

        placeRepository.save(place);
        return airports;
    }

    public void saveAll(List<Location> places) {
        placeRepository.saveAll(places);
    }
}
