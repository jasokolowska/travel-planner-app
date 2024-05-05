package com.sokolowska.travelplannerapi.webclient.geoapi;

import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.sokolowska.travelplannerapi.model.LocationData;
import com.sokolowska.travelplannerapi.webclient.geoapi.config.GeoApiLocalContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class GeoApiServiceImpl implements GeoApiService {

    private final GeoApiLocalContext context;

    @Override
    public LocationData findCoordinates(String placeName) {
        try {
            GeocodingResult[] results = findPlaceData(placeName);
            return new LocationData(
                    results[0].geometry.location.lat,
                    results[0].geometry.location.lng,
                    results[0].placeId);
        } catch (IOException | InterruptedException | ApiException e) {
            System.err.println("Error while fetching geocoding data: " + e.getMessage());
            return null;
        }
    }

    private GeocodingResult[] findPlaceData(String placeName) throws IOException, InterruptedException, ApiException {
        return GeocodingApi
                .newRequest(context.getLocalContext())
                .address(placeName)
                .await();
    }
}
