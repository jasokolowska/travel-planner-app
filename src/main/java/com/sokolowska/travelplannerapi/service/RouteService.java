package com.sokolowska.travelplannerapi.service;

import com.sokolowska.travelplannerapi.model.Place;
import com.sokolowska.travelplannerapi.model.Route;
import com.sokolowska.travelplannerapi.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository routeRepository;
    private final PlaceService placeService;
    private final FlightService flightService;

    public List<Route> findAll() {
        return routeRepository.findAll();
    }

    public void save(Route route) {
        Place destination = placeService.setCoordinatesAndAirports(route.getDestination());
        Place origin = placeService.setCoordinatesAndAirports(route.getOrigin());

        origin.getAirports().stream()
                .forEach(startAirport -> {
                    destination.getAirports().stream()
                            .forEach(endAirport -> flightService.getFlight(startAirport.getCode(), endAirport.getCode()));
                });
        routeRepository.save(route);
    }

    public void deleteById(Long id) {
        routeRepository.deleteById(id);
    }
}
