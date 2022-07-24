package com.sokolowska.travelplannerapi.service;

import com.sokolowska.travelplannerapi.model.Airport;
import com.sokolowska.travelplannerapi.model.Flight;
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

    public void process(Route route) {
        List<Airport> destinationAirports = placeService.findAirports(route.getDestination());
        List<Airport> originAirports = placeService.findAirports(route.getOrigin());
        List<Flight> flights = flightService.findFlights(originAirports, destinationAirports);
        route.getFlights().addAll(flights);
        routeRepository.save(route);
    }

    public void deleteById(Long id) {
        routeRepository.deleteById(id);
    }

    public Route add(Route route) {
        return routeRepository.save(route);
    }
}
