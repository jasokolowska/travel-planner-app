package com.sokolowska.travelplannerapi.service;

import com.sokolowska.travelplannerapi.model.Flight;
import com.sokolowska.travelplannerapi.repository.FlightRepository;
import com.sokolowska.travelplannerapi.tequila.AirportClient;
import com.sokolowska.travelplannerapi.tequila.FlightDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final AirportClient airportClient;

    public Flight getFlight(String originAirportCode, String destinationAirportCode) {
        FlightDto flightDto = airportClient.getFlights(originAirportCode, destinationAirportCode);
        if (!flightDto.getCityFrom().equals(null)){
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
