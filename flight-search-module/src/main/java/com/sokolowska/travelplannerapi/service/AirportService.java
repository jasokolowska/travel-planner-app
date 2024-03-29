package com.sokolowska.travelplannerapi.service;

import com.sokolowska.travelplannerapi.model.Airport;
import com.sokolowska.travelplannerapi.model.AirportType;
import com.sokolowska.travelplannerapi.repository.AirportRepository;
import com.sokolowska.travelplannerapi.webclient.tequilaapi.TequilaApiClient;
import com.sokolowska.travelplannerapi.model.dto.AirportDto;
import com.sokolowska.travelplannerapi.model.dto.AirportListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final TequilaApiClient flightsClient;
    private final AirportRepository airportRepository;

    public List<Airport> getAirports(Double latitude, Double longitude, AirportType airportType) {

        AirportListDto airportListDto = flightsClient.getAirports(latitude, longitude, airportType);

        airportListDto.getLocations().forEach(location -> System.out.println(location.getName()));

        return airportListDto.getLocations().stream()
                .map(AirportDto::toDomain)
                .map(airportRepository::save)
                .toList();
    }

}
