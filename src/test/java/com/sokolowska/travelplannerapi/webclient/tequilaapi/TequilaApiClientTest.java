package com.sokolowska.travelplannerapi.webclient.tequilaapi;

import com.sokolowska.travelplannerapi.model.dto.AirportDto;
import com.sokolowska.travelplannerapi.model.dto.AirportListDto;
import com.sokolowska.travelplannerapi.webclient.tequilaapi.dto.TequilaFlightDataDto;
import com.sokolowska.travelplannerapi.webclient.tequilaapi.dto.TequilaSearchFlightDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TequilaApiClientTest {

    @InjectMocks
    private TequilaApiClient tequilaApiClient;

    @Mock
    private RestTemplate restTemplate;

    @Test
    void shouldReturnListOfAirports() {
        //Given
        Double lat = 1.0;
        Double lon = 1.0;

        AirportDto airportDto = new AirportDto();
        airportDto.setName("Wroclaw");
        airportDto.setCode("WRO");

        AirportListDto airportListDto = new AirportListDto(new ArrayList<>());
        airportListDto.getLocations().add(airportDto);

        when(restTemplate.exchange(
                ArgumentMatchers.any(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(HttpEntity.class),
                ArgumentMatchers.<Class<AirportListDto>>any()
        )).thenReturn(new ResponseEntity<>(airportListDto, HttpStatus.OK));

        //When
        AirportListDto airports = tequilaApiClient.getAirports(lat, lon);

        //Then
        assertEquals(1, airports.getLocations().size());
        assertEquals("Wroclaw", airports.getLocations().get(0).getName());
        assertEquals("WRO", airports.getLocations().get(0).getCode());
    }

    @Test
    void shouldReturnTequilaSearchFlightDto() {
        String origin = "Wroclaw";
        String destination = "Londyn";

        TequilaFlightDataDto tequilaFlightDataDto = TequilaFlightDataDto.builder()
                .cityFrom("Wroclaw")
                .cityTo("Londyn")
                .build();
        TequilaSearchFlightDto tequilaSearchFlightDto = new TequilaSearchFlightDto(new ArrayList<>());
        tequilaSearchFlightDto.getData().add(tequilaFlightDataDto);

        when(restTemplate.exchange(
                ArgumentMatchers.any(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(HttpEntity.class),
                ArgumentMatchers.<Class<TequilaSearchFlightDto>>any()
        )).thenReturn(new ResponseEntity<>(tequilaSearchFlightDto, HttpStatus.OK));

        //When
        TequilaSearchFlightDto flightsDto = tequilaApiClient.getFlight(origin, destination);

        //Then
        assertEquals(1, flightsDto.getData().size());
        assertEquals(origin, flightsDto.getData().get(0).getCityFrom());
        assertEquals(destination, flightsDto.getData().get(0).getCityTo());
    }
}