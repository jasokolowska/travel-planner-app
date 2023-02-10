package com.sokolowska.travelplannerapi.webclient.geoapi;

import com.google.maps.GeoApiContext;
import lombok.Getter;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
@Getter
public class GeoApiLocalContext {

    private GeoApiContext localContext;

    @PostConstruct
    public void init() {
        String apiKey = System.getenv("GEOCODING_API_KEY");
        localContext = new GeoApiContext.Builder().apiKey(apiKey).queryRateLimit(500).build();
    }

    @PreDestroy
    public void shutdown(){
        localContext.shutdown();
    }
}
