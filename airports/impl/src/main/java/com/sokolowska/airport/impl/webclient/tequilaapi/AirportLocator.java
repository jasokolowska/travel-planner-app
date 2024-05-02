package com.sokolowska.airport.impl.webclient.tequilaapi;


import com.sokolowska.airport.impl.model.model.AirportType;
import com.sokolowska.airport.impl.model.model.dto.AirportListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class AirportLocator {

    public static final String TEQUILA_URL_RADIUS = "https://tequila-api.kiwi.com/locations/radius";

    private final String TEQUILA_API_KEY = System.getenv("TEQUILA_API");
    private final int LOCATOR_RADIUS_KM = 300;

    private final RestTemplate restTemplate;

    public AirportListDto getAirports(Double latitude, Double longitude, AirportType airportType) {
        int radius = LOCATOR_RADIUS_KM;
        int limit = 3;
        if (airportType.equals(airportType.ORIGIN)){
            radius = 400;
            limit = 10;
        }
        URI url = UriComponentsBuilder.fromHttpUrl(TEQUILA_URL_RADIUS)
                .queryParam("lat", latitude)
                .queryParam("lon", longitude)
                .queryParam("radius", radius)
                .queryParam("location_types", "airport")
                .queryParam("limit", limit)
                .queryParam("active-only", true)
                .build()
                .encode()
                .toUri();

        return restTemplate.exchange(url, HttpMethod.GET, getRequestEntity(), AirportListDto.class).getBody();
    }

    private HttpEntity<Void> getRequestEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("apikey", TEQUILA_API_KEY);
        return new HttpEntity<>(headers);
    }
}
