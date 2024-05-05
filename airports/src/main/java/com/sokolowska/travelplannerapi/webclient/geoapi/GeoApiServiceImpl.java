package com.sokolowska.travelplannerapi.webclient.geoapi;

import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.sokolowska.travelplannerapi.model.LocationData;
import com.sokolowska.travelplannerapi.webclient.geoapi.config.GeoApiLocalContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GeoApiServiceImpl implements GeoApiService {

    private final GeoApiLocalContext context;

    @Override
    public LocationData findCoordinates(String placeName) {
        GeocodingResult[] results = findPlaceData(placeName);
        return new LocationData(
                results[0].geometry.location.lat,
                results[0].geometry.location.lng,
                results[0].placeId);
    }

    private GeocodingResult[] findPlaceData(String placeName){
        return GeocodingApi
                .newRequest(context.getLocalContext())
                .address(placeName)
                .awaitIgnoreError();
    }
}
