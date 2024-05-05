package com.sokolowska.travelplannerapi.service;

import com.sokolowska.travelplannerapi.api.LocationService;
import com.sokolowska.travelplannerapi.api.dto.AirportDto;
import com.sokolowska.travelplannerapi.model.Airport;
import com.sokolowska.travelplannerapi.model.AirportType;
import com.sokolowska.travelplannerapi.model.Location;
import com.sokolowska.travelplannerapi.model.LocationData;
import com.sokolowska.travelplannerapi.model.mapper.AirportMapper;
import com.sokolowska.travelplannerapi.repository.AirportRepository;
import com.sokolowska.travelplannerapi.repository.LocationRepository;
import com.sokolowska.travelplannerapi.webclient.geoapi.GeoApiService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LocationServiceImpl implements LocationService {

    private final GeoApiService geoApiService;
    private final AirportServiceImpl airportService;
    private final LocationRepository locationRepository;
    private final AirportRepository airportRepository;
    private final AirportMapper airportMapper;

    @Override
    public List<AirportDto> findOriginAirportsByLocation(String locationName) {
        List<Airport> airportsByLocation = findAirportsByLocation(locationName, AirportType.ORIGIN);
        return this.airportMapper.toDtoList(airportsByLocation);
    }

    @Override
    public List<AirportDto> findDestinationAirportsByLocation(String locationName) {
        List<Airport> airportsByLocation = findAirportsByLocation(locationName, AirportType.DESTINATION);
        return this.airportMapper.toDtoList(airportsByLocation);
    }

    private List<Airport> findAirportsByLocation(String locationName, AirportType airportType) {
        Optional<Location> location = locationRepository.findByName(locationName);

        if (location.isPresent() && location.get().getAirports().size() != 0){
            return location.get().getAirports();
        } else {
            return getAirportList(locationName, airportType);
        }
    }

    private List<Airport> getAirportList(String locationName, AirportType airportType) {
        LocationData locationData = geoApiService.findCoordinates(locationName);
        List<Airport> airports = airportService.getAirports(
                locationData.latitude(), locationData.longitude(), airportType);

        Location location = Location.builder()
                .name(locationName)
                .latitude(locationData.latitude())
                .longitude(locationData.longitude())
                .geoApiPlaceId(locationData.placeId())
                .airports(airports).build();

        locationRepository.save(location);
        airports.forEach(airport -> airport.setLocation(location));
        airportRepository.saveAll(airports);
        return airports;
    }
}
