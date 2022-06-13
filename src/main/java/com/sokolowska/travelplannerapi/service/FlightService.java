package com.sokolowska.travelplannerapi.service;

import com.sokolowska.travelplannerapi.controller.dto.CriteriaDto;
import com.sokolowska.travelplannerapi.model.Flight;
import com.sokolowska.travelplannerapi.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    public Flight search(CriteriaDto criteriaDto) {
        return new Flight("Wrocław", "Londyn");
    }
}
