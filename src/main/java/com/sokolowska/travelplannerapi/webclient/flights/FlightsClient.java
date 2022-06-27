package com.sokolowska.travelplannerapi.webclient.flights;

import com.sokolowska.travelplannerapi.webclient.flights.dto.AirportListDto;
import com.sokolowska.travelplannerapi.webclient.flights.dto.FlightDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class FlightsClient {

    public static final String TEQUILA_URL_RADIUS = "https://tequila-api.kiwi.com/locations/radius";
    public static final String TEQUILA_URL_FLIGHT = "https://tequila-api.kiwi.com/v2/search";
    private final String TEQUILA_API_KEY = System.getenv("TEQUILA_API");
    private final int LOCATOR_RADIUS_KM = 250;

    private final RestTemplate restTemplate;

    public AirportListDto getAirports(Double latitude, Double longitude){
        URI url = UriComponentsBuilder.fromHttpUrl(TEQUILA_URL_RADIUS)
                .queryParam("lat", latitude)
                .queryParam("lon", longitude)
                .queryParam("radius", LOCATOR_RADIUS_KM)
                .queryParam("location_types", "airport")
                .queryParam("limit", 3)
                .queryParam("active-only", true)
                .build()
                .encode()
                .toUri();
        System.out.println(">>>>>>>>>>>>>>>url: " + url);
        HttpHeaders headers = new HttpHeaders();
        headers.add("apikey", TEQUILA_API_KEY);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        return restTemplate.exchange(url, HttpMethod.GET, requestEntity, AirportListDto.class).getBody();
    }

    public FlightDto getFlights(String originAirportCode, String destinationAirportCode){
        URI url = UriComponentsBuilder.fromHttpUrl(TEQUILA_URL_FLIGHT)
                .queryParam("fly_from", originAirportCode)
                .queryParam("fly_to", destinationAirportCode)
                .queryParam("date_from", LocalDate.now().toString())
                .queryParam("date_to", LocalDate.now().plusDays(30).toString())
                .queryParam("flight_type", "oneway")
                .queryParam("max_stopovers", 0)
                .queryParam("vehicle_type", "aircraft")
                .queryParam("sort", "price")
                .queryParam("asc", 1)
                .queryParam("limit", 1)
                .build()
                .encode()
                .toUri();
        System.out.println(">>>>>>>>>>>>>>>url: " + url);
        HttpHeaders headers = new HttpHeaders();
        headers.add("apikey", TEQUILA_API_KEY);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        return restTemplate.exchange(url, HttpMethod.GET, requestEntity, FlightDto.class).getBody();

    }


}
