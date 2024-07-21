package com.sokolowska.travelplannerapi.service;



import com.sokolowska.travelplannerapi.model.Airport;
import com.sokolowska.travelplannerapi.model.AirportType;
import com.sokolowska.travelplannerapi.model.dto.AirportListDto;
import com.sokolowska.travelplannerapi.model.mapper.AirportMapper;
import com.sokolowska.travelplannerapi.repository.AirportRepository;
import com.sokolowska.travelplannerapi.webclient.tequilaapi.AirportLocator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl {

    private final AirportLocator airportLocator;
    private final AirportRepository airportRepository;
    private final AirportMapper mapper;

    public List<Airport> getAirports(Double latitude, Double longitude, AirportType airportType) {
        AirportListDto airportListDto = airportLocator.getAirports(latitude, longitude, airportType);
        List<Airport> airports = this.mapper.toEtyList(airportListDto.getAirportLocations());
        saveAirportWithLocation(airports);
        return airports;
    }

    private void saveAirportWithLocation(List<Airport> airport) {
        this.airportRepository.saveAll(airport);
    }
}
