package com.sokolowska.travelplannerapi.service;

import com.sokolowska.travelplannerapi.model.Flight;
import com.sokolowska.travelplannerapi.model.dto.FlightParamsDto;
import com.sokolowska.travelplannerapi.model.mapper.FlightMapper;
import com.sokolowska.travelplannerapi.repository.FlightRepository;
import com.sokolowska.travelplannerapi.webclient.tequilaapi.FlightFinder;
import com.sokolowska.travelplannerapi.model.dto.FlightDto;
import com.sokolowska.travelplannerapi.webclient.tequilaapi.dto.TequilaSearchFlightsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightFinder flightsClient;
    private final FlightMapper flightMapper;

    public List<Flight> retrieveAndSaveFlights(List<String> originAirportCodes, List<String> destinationAirportCodes,
                                               FlightParamsDto flightParamsDto) {
        List<FlightDto> allFlightData = fetchFlightDataFromAllPairs(originAirportCodes, destinationAirportCodes, flightParamsDto);
        List<FlightDto> filteredAndSortedFlights = filterAndSortFlights(allFlightData);
        return saveFlights(filteredAndSortedFlights);
    }

    private List<FlightDto> retrieveFlightData(String originAirportCode, String destinationAirportCode,
                                         FlightParamsDto flightParamsDto) {
        TequilaSearchFlightsDto tequilaSearchFlightsDto = flightsClient.getFlight(originAirportCode, destinationAirportCode, flightParamsDto);
        return tequilaSearchFlightsDto.getData().stream()
                .map(flightMapper::toDto)
                .collect(Collectors.toList());
    }

    private List<FlightDto> fetchFlightDataFromAllPairs(List<String> originAirportCodes, List<String> destinationAirportCodes,
                                                        FlightParamsDto flightParamsDto) {
        return originAirportCodes.stream()
                .flatMap(origin -> destinationAirportCodes.stream()
                        .flatMap(destination -> retrieveFlightData(origin, destination, flightParamsDto).stream()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    // Filter and sort flights by price

    private List<FlightDto> filterAndSortFlights(List<FlightDto> flightDataList) {
        return flightDataList.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(FlightDto::getPrice))
                .collect(Collectors.toList());
    }
    // Save flights in the repository

    private List<Flight> saveFlights(List<FlightDto> flights) {
        return flights.stream()
                .map(this::saveFlightEntity)
                .collect(Collectors.toList());
    }

    private Flight saveFlightEntity(FlightDto flightDto) {
        Flight flight = this.flightMapper.toEty(flightDto);
        return flightRepository.save(flight);
    }
}
