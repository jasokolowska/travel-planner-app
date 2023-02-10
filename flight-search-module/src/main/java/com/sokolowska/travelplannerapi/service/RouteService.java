package com.sokolowska.travelplannerapi.service;

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

//    public void process(Route route) {
//        List<Airport> destinationAirports = placeService.findAirports(route.getDestination());
//        List<Airport> originAirports = placeService.findAirports(route.getOrigin());
//        List<Flight> flights = flightService.findFlights(originAirports, destinationAirports);
//        route.getFlights().addAll(flights);
//        routeRepository.save(route);
//    }

    public List<Flight> findCheapestFlights(FlightParamsDto flightParamsDto) {
        List<Airport> destinationAirports = placeService.findAirports(flightParamsDto.getDestination(), AirportType.DESTINATION);
        List<Airport> originAirports = placeService.findAirports(flightParamsDto.getOrigin(), AirportType.ORIGIN);
        System.out.println("Lotniska: ");
        System.out.println(">>> docelowe");
        destinationAirports.forEach(airport -> System.out.println(airport.getName()));
        System.out.println(">>> wylot");
        originAirports.forEach(airport -> System.out.println(airport.getName()));
        return flightService.findFlights(originAirports, destinationAirports, flightParamsDto);
    }

    public void deleteById(Long id) {
        routeRepository.deleteById(id);
    }

    public Route add(Route route) {
        Place destination = route.getDestination();
        Place origin = route.getOrigin();
        placeService.saveAll(List.of(destination, origin));
        return routeRepository.save(route);
    }
}
