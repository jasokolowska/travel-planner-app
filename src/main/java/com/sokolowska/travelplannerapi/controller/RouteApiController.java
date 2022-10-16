package com.sokolowska.travelplannerapi.controller;

import com.sokolowska.travelplannerapi.model.Route;
import com.sokolowska.travelplannerapi.model.dto.RouteDto;
import com.sokolowska.travelplannerapi.model.mapper.RouteMapper;
import com.sokolowska.travelplannerapi.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/routes")
@RequiredArgsConstructor
public class RouteApiController {

    private final RouteService routeService;
    private final RouteMapper routeMapper;

    //TODO 1: Add endpoint to addRouteDto (without flights)
    @PostMapping("")
    public ResponseEntity<RouteDto> addRoute(@RequestBody RouteDto routeDto) {
        Route route = routeService.add(routeMapper.mapToDomain(routeDto));
        return new ResponseEntity<>(routeMapper.mapToDto(route), HttpStatus.CREATED);
    }

    @GetMapping("/welcome")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Welcome!", HttpStatus.OK);
    }

    //TODO 2: Add endpoint to getRouteDto by id
    //TODO 3: Add endpoint to getAllRouts
    //TODO 4: Add endpoint to get flights for given route id
}
