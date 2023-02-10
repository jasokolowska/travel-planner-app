package com.sokolowska.travelplannerapi.webclient.geoapi;

import com.google.maps.model.GeocodingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GeoApiClient implements GeoApiService {

    private final LocalGeocodingApiRequest geocodingApiRequest;

    @Override
    public double[] findCoordinates(String placeName) {

        GeocodingResult[] results = geocodingApiRequest.makeFindPlaceDataRequest(placeName);
        return new double[]{results[0].geometry.location.lat, results[0].geometry.location.lng};
    }
}
