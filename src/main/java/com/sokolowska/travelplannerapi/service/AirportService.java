package com.sokolowska.travelplannerapi.service;

import com.sokolowska.travelplannerapi.model.Airport;
import com.sokolowska.travelplannerapi.repository.AirportRepository;
import com.sokolowska.travelplannerapi.tequila.AirportClient;
import com.sokolowska.travelplannerapi.tequila.AirportDto;
import com.sokolowska.travelplannerapi.tequila.AirportListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportClient airportClient;
    private final AirportRepository airportRepository;

    public List<Airport> getAirports(Double latitude, Double longitude) {
        AirportListDto airportListDto = airportClient.getAirports(latitude, longitude);
        return airportListDto.getLocations().stream()
                .map(AirportDto::toDomain)
                .map(airportRepository::save)
                .toList();
    }
}
