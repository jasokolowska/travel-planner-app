package com.sokolowska.travelplannerapi.service;

import com.sokolowska.travelplannerapi.api.LocationService;
import com.sokolowska.travelplannerapi.api.dto.AirportDto;
import com.sokolowska.travelplannerapi.model.*;
import com.sokolowska.travelplannerapi.model.dto.FlightDto;
import com.sokolowska.travelplannerapi.model.dto.FlightParamsDto;
import com.sokolowska.travelplannerapi.model.mapper.FlightMapper;
import com.sokolowska.travelplannerapi.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RouteService {

    private final RouteRepository routeRepository;
    private final LocationService locationService;
    private final FlightService flightService;
    private final FlightMapper flightMapper;


    public List<FlightDto> findCheapestFlights(FlightParamsDto flightParamsDto) {
        List<AirportDto> destinationAirports =
                locationService.findOriginAirportsByLocation(flightParamsDto.getDestination());
        List<AirportDto> originAirports =
                locationService.findDestinationAirportsByLocation(flightParamsDto.getOrigin());

        List<Flight> flights = flightService.retrieveAndSaveFlights(
                getAirportCodes(originAirports),
                getAirportCodes(destinationAirports),
                flightParamsDto);
        return this.flightMapper.toDtoList(flights);
    }

    private List<String> getAirportCodes(List<AirportDto> airports) {
        return airports.stream().map(AirportDto::getCode).toList();
    }

    public void deleteById(Long id) {
        routeRepository.deleteById(id);
    }

    public Route add(Route route) {
        return routeRepository.save(route);
    }
}
