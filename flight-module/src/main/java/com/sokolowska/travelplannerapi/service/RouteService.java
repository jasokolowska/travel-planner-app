package com.sokolowska.travelplannerapi.service;

import com.sokolowska.airport.api.service.PlaceService;
import com.sokolowska.travelplannerapi.model.*;
import com.sokolowska.travelplannerapi.model.dto.FlightParamsDto;
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
    private final PlaceService placeService;
    private final FlightService flightService;

    public List<Route> findAll() {
        return routeRepository.findAll();
    }

    public List<Flight> findCheapestFlights(FlightParamsDto flightParamsDto) {
        List<String> destinationAirports = placeService.findAirportCodesByLocation(flightParamsDto.getDestination());
        List<String> originAirports = placeService.findAirportCodesByLocation(flightParamsDto.getOrigin());
        return flightService.findFlights(originAirports, destinationAirports, flightParamsDto);
    }

    public void deleteById(Long id) {
        routeRepository.deleteById(id);
    }

    public Route add(Route route) {
//        TODO: Decide how to handle Place (Location object)
//        Place destination = route.getDestination();
//        Place origin = route.getOrigin();
//        placeService.saveAll(List.of(destination, origin));
        return routeRepository.save(route);
    }
}
