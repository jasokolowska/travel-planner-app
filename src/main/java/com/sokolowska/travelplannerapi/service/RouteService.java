package com.sokolowska.travelplannerapi.service;

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

    public List<Route> findAll() {
        return routeRepository.findAll();
    }

    public void save(Route route) {
        placeService.setCoordinatesAndAirports(route.getDestination());
        placeService.setCoordinatesAndAirports(route.getOrigin());
//        Place origin = geoApiService.findCoordinates(route.getOrigin().getName());
//        Place destination = geoApiService.findCoordinates(route.getDestination().getName());
//        route.setDestination(origin);
//        route.setOrigin(destination);
        routeRepository.save(route);
    }

    public void deleteById(Long id) {
        routeRepository.deleteById(id);
    }
}
