package com.sokolowska.travelplannerapi.service;

import com.sokolowska.travelplannerapi.model.Flight;
import com.sokolowska.travelplannerapi.repository.FlightRepository;

import com.sokolowska.travelplannerapi.webclient.flights.FlightsClient;
import com.sokolowska.travelplannerapi.webclient.flights.dto.FlightDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightsClient flightsClient;

    public Flight getFlight(String originAirportCode, String destinationAirportCode) {
        FlightDto flightDto = flightsClient.getFlights(originAirportCode, destinationAirportCode);
        if (flightDto.getCityFrom() != null){
            Flight flight = Flight.builder()
                    .cityFrom(flightDto.getCityFrom())
                    .cityTo(flightDto.getCityTo())
                    .arrival(flightDto.getArrival())
                    .departure(flightDto.getDeparture())
                    .price(BigDecimal.valueOf(flightDto.getPrice()))
                    .link(flightDto.getLink())
                    .build();
            return flightRepository.save(flight);
        }
        return null;
    }

}
