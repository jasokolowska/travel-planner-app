package com.sokolowska.airport.impl.webclient.geoapi;


import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocalGeocodingApiRequest {

    private final GeoApiLocalContext context;

    public GeocodingResult[] makeFindPlaceDataRequest(String placeName){
        return GeocodingApi.newRequest(context.getLocalContext()).address(placeName).awaitIgnoreError();
    }

}
