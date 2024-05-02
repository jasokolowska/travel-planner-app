package com.sokolowska.airport.impl.service;



import com.sokolowska.airport.impl.model.model.Airport;
import com.sokolowska.airport.impl.model.model.AirportType;
import com.sokolowska.airport.impl.model.model.dto.AirportListDto;
import com.sokolowska.airport.impl.model.model.mapper.AirportMapper;
import com.sokolowska.airport.impl.repository.AirportRepository;
import com.sokolowska.airport.impl.webclient.tequilaapi.AirportLocator;
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

        airportListDto.getLocations().forEach(location -> System.out.println(location.getName()));

        return airportListDto.getLocations().stream()
                .map(mapper::toEty)
                .map(airportRepository::save)
                .toList();
    }

}
