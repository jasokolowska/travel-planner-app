package com.sokolowska.travelplannerapi.webclient.tequilaapi;

import com.sokolowska.travelplannerapi.model.dto.FlightParamsDto;
import com.sokolowska.travelplannerapi.webclient.tequilaapi.dto.TequilaSearchFlightsDto;
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
public class FlightFinder {


    public static final String TEQUILA_URL_FLIGHT = "https://tequila-api.kiwi.com/v2/search";
    private final String TEQUILA_API_KEY = System.getenv("TEQUILA_API");

    private final RestTemplate restTemplate;

    public TequilaSearchFlightsDto getFlight(String originAirportCode, String destinationAirportCode, FlightParamsDto flightParamsDto) {
        UriComponentsBuilder httpUrlBuilder = UriComponentsBuilder.fromHttpUrl(TEQUILA_URL_FLIGHT)
                .queryParam("fly_from", originAirportCode)
                .queryParam("fly_to", destinationAirportCode)
                .queryParam("date_from", flightParamsDto.getDateFrom() != null ? flightParamsDto.getDateFrom() : LocalDate.now().toString())
                .queryParam("date_to", flightParamsDto.getDateTo() != null ? flightParamsDto.getDateTo() : LocalDate.now().plusDays(90).toString())
                .queryParam("flight_type", flightParamsDto.isTwoWayTrip() ? "round" : "oneway")
                .queryParam("max_stopovers", flightParamsDto.getStopovers() != 3 ? flightParamsDto.getStopovers() : 3)
                .queryParam("vehicle_type", "aircraft")
                .queryParam("sort", "price")
                .queryParam("asc", 1)
                .queryParam("limit", 3);

        if (flightParamsDto.isTwoWayTrip()) {
            httpUrlBuilder
                    .queryParam("nights_in_dst_from", 0)
                    .queryParam("nights_in_dst_to", flightParamsDto.getDays());
        }

        URI url = httpUrlBuilder.build().encode().toUri();

        return restTemplate.exchange(url, HttpMethod.GET, getRequestEntity(), TequilaSearchFlightsDto.class).getBody();

    }

    private HttpEntity<Void> getRequestEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("apikey", TEQUILA_API_KEY);
        return new HttpEntity<>(headers);
    }
}
