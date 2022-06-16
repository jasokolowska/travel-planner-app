package com.sokolowska.travelplannerapi.google;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.sokolowska.travelplannerapi.model.Place;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@Component
public class CoordinatesLocator implements GeoApiService {

    private GeoApiContext context;

    @PostConstruct
    public void init() {
        String apiKey = System.getenv("GEOCODING_API_KEY");
        context = new GeoApiContext.Builder().apiKey(apiKey).queryRateLimit(500).build();
    }

    @PreDestroy
    public void shutdown(){
        context.shutdown();
    }

    @Override
    public Place findCoordinates(String placeName) {
        try {
            GeocodingResult[] results = GeocodingApi.newRequest(context).address(placeName).await();
            return new Place(placeName, results[0].geometry.location.lat, results[0].geometry.location.lng);
        } catch (ApiException | IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        return new Place();
    }
}
