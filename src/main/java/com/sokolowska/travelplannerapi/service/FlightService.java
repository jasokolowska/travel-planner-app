package com.sokolowska.travelplannerapi.service;

import com.sokolowska.travelplannerapi.model.Airport;
import com.sokolowska.travelplannerapi.model.Flight;
import com.sokolowska.travelplannerapi.repository.FlightRepository;
import com.sokolowska.travelplannerapi.webclient.tequilaapi.TequilaApiClient;
import com.sokolowska.travelplannerapi.model.dto.FlightDto;
import com.sokolowska.travelplannerapi.webclient.tequilaapi.dto.TequilaSearchFlightDto;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final TequilaApiClient flightsClient;

    public FlightDto getFlight(String originAirportCode, String destinationAirportCode) {
        TequilaSearchFlightDto flightDto = flightsClient.getFlight(originAirportCode, destinationAirportCode);
        if (flightDto.getData().isEmpty()) {
            return null;
        }
        return FlightDto.builder()
                .cityFrom(flightDto.getData().get(0).getCityFrom())
                .cityTo(flightDto.getData().get(0).getCityTo())
                .price(flightDto.getData().get(0).getPrice())
                .link(flightDto.getData().get(0).getDeepLink())
                .arrival(flightDto.getData().get(0).getLocalArrival())
                .departure(flightDto.getData().get(0).getLocalDeparture())
                .build();
    }

    @NotNull
    private Flight mapAndSave(FlightDto flightDto) {
        Flight flight = Flight.builder()
                .cityFrom(flightDto.getCityFrom())
                .cityTo(flightDto.getCityTo())
                .arrival(flightDto.getArrival())
                .departure(flightDto.getDeparture())
                .price(flightDto.getPrice())
                .link(flightDto.getLink())
                .build();
        return flightRepository.save(flight);
    }

    public List<Flight> findFlights(List<Airport> originAirports, List<Airport> destinationAirports) {
        return originAirports.stream()
                .flatMap(startAirport -> destinationAirports.stream()
                .map(endAirport -> getFlight(startAirport.getCode(), endAirport.getCode())))
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(FlightDto::getPrice))
                .limit(3)
                .map(this::mapAndSave)
                .toList();
    }
}
