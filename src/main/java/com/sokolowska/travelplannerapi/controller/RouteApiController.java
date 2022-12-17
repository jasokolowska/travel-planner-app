package com.sokolowska.travelplannerapi.controller;

import com.sokolowska.travelplannerapi.model.Flight;
import com.sokolowska.travelplannerapi.model.dto.FlightDto;
import com.sokolowska.travelplannerapi.model.dto.FlightParamsDto;
import com.sokolowska.travelplannerapi.model.mapper.RouteMapper;
import com.sokolowska.travelplannerapi.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/routes")
@RequiredArgsConstructor
public class RouteApiController {

    private final RouteService routeService;
    private final RouteMapper routeMapper;

    //TODO 1: Add endpoint to addRouteDto (without flights)
    @PostMapping("")
    public ResponseEntity<List<FlightDto>> searchFlight(@RequestBody FlightParamsDto flightParamsDto) {
        System.out.println("flightParamsDto.isTwoWayTrip() in Controller" + flightParamsDto.isTwoWayTrip());
        List<Flight> flights= routeService.findCheapestFlights(flightParamsDto);
        return new ResponseEntity(flights, HttpStatus.CREATED);
    }

//    @GetMapping("")
//    public ResponseEntity<List<Flight>> search(@RequestParam String destination, @RequestParam String origin) {
//        List<Flight> flights= routeService.findCheapestFlights(destination, origin);
//        return new ResponseEntity<>(flights, HttpStatus.CREATED);
//    }

    @GetMapping("/welcome")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Welcome!", HttpStatus.OK);
    }

    //TODO 2: Add endpoint to getRouteDto by id
    //TODO 3: Add endpoint to getAllRouts
    //TODO 4: Add endpoint to get flights for given route id
}
