package com.sokolowska.travelplannerapi.webclient.flights;

import com.sokolowska.travelplannerapi.webclient.flights.dto.AirportListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class FlightsClient {

    public static final String TEQUILA_URL = "https://tequila-api.kiwi.com/locations/radius";
    private final String TEQUILA_API_KEY = System.getenv("TEQUILA_API");
    private final int LOCATOR_RADIUS_KM = 250;

    private final RestTemplate restTemplate;

    public AirportListDto getAirports(Double latitude, Double longitude){
        URI url = UriComponentsBuilder.fromHttpUrl(TEQUILA_URL)
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


}
