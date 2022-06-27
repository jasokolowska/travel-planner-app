package com.sokolowska.travelplannerapi.service;

import com.sokolowska.travelplannerapi.model.Airport;
import com.sokolowska.travelplannerapi.repository.AirportRepository;
import com.sokolowska.travelplannerapi.webclient.flights.FlightsClient;
import com.sokolowska.travelplannerapi.webclient.flights.dto.AirportDto;
import com.sokolowska.travelplannerapi.webclient.flights.dto.AirportListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final FlightsClient flightsClient;
    private final AirportRepository airportRepository;

    public List<Airport> getAirports(Double latitude, Double longitude) {
        AirportListDto airportListDto = flightsClient.getAirports(latitude, longitude);
        return airportListDto.getLocations().stream()
                .map(AirportDto::toDomain)
                .map(airportRepository::save)
                .toList();
    }

}
