package com.sokolowska.travelplannerapi.service;

import com.sokolowska.travelplannerapi.model.Airport;
import com.sokolowska.travelplannerapi.model.Flight;
import com.sokolowska.travelplannerapi.model.dto.FlightParamsDto;
import com.sokolowska.travelplannerapi.repository.FlightRepository;
import com.sokolowska.travelplannerapi.webclient.tequilaapi.TequilaApiClient;
import com.sokolowska.travelplannerapi.model.dto.FlightDto;
import com.sokolowska.travelplannerapi.webclient.tequilaapi.dto.TequilaFlightDataDto;
import com.sokolowska.travelplannerapi.webclient.tequilaapi.dto.TequilaSearchFlightsDto;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class FlightService {

    private final FlightRepository flightRepository;
    private final TequilaApiClient flightsClient;

    public List<FlightDto> getFlights(String originAirportCode, String destinationAirportCode, FlightParamsDto flightParamsDto) {
        System.out.println("Szukaj połączenia dla: " + originAirportCode + " -> " + destinationAirportCode);
        TequilaSearchFlightsDto tequilaSearchFlightsDto = flightsClient.getFlight(originAirportCode, destinationAirportCode, flightParamsDto);
        List<FlightDto> flightsDto = new ArrayList<>();
        if (tequilaSearchFlightsDto.getData().isEmpty()) {
            return flightsDto;
        }

        for (TequilaFlightDataDto flightDataDto : tequilaSearchFlightsDto.getData()) {
            FlightDto flightDto = FlightDto.builder()
                    .cityFrom(flightDataDto.getCityFrom())
                    .cityTo(flightDataDto.getCityTo())
                    .price(flightDataDto.getPrice())
                    .link(flightDataDto.getDeepLink())
                    .arrival(flightDataDto.getLocalArrival())
                    .departure(flightDataDto.getLocalDeparture())
                    .build();
            flightsDto.add(flightDto);
        }
        System.out.println("Ilość lotów: " + flightsDto.size());
        return flightsDto;
    }

    @NotNull
    private Flight mapAndSave(FlightDto flightDto) {
        Flight flight = Flight.builder()
                .cityFrom(flightDto.getCityFrom())
                .cityTo(flightDto.getCityTo())
                .arrival(LocalDateTime.parse(flightDto.getArrival()))
                .departure(LocalDateTime.parse(flightDto.getDeparture()))
                .price(BigDecimal.valueOf(flightDto.getPrice()))
                .link(flightDto.getLink())
                .build();
        return flightRepository.save(flight);
    }

    public List<Flight> findFlights(List<Airport> originAirports, List<Airport> destinationAirports, FlightParamsDto flightParamsDto) {
        return originAirports.stream()
                .flatMap(startAirport -> destinationAirports.stream()
                        .map(endAirport -> getFlights(startAirport.getCode(), endAirport.getCode(), flightParamsDto)))
                .flatMap(Collection::stream)
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(FlightDto::getPrice))
//                .limit(3)
                .map(this::mapAndSave)
                .collect(Collectors.toList());
    }
}
