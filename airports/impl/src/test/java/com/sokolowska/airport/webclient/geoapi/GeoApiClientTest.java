package com.sokolowska.airport.webclient.geoapi;

import static org.junit.jupiter.api.Assertions.*;

import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import com.google.maps.model.LatLng;
import com.sokolowska.airport.impl.webclient.geoapi.GeoApiClient;
import com.sokolowska.airport.impl.webclient.geoapi.LocalGeocodingApiRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GeoApiClientTest {

    @InjectMocks
    private GeoApiClient geoApiClient;

    @Mock
    private LocalGeocodingApiRequest geocodingApiRequest;

    @Test
    void shouldReturnCoordinatesOfPlace() throws IOException, InterruptedException, ApiException {
        //Given
        String place = "Wroclaw";

        GeocodingResult geocodingResult = new GeocodingResult();
        geocodingResult.geometry = new Geometry();
        geocodingResult.geometry.location = new LatLng();
        geocodingResult.geometry.location.lat = 51.1;
        geocodingResult.geometry.location.lng = 17.03;

        GeocodingResult[] geocodingResults = new GeocodingResult[1];
        geocodingResults[0] = geocodingResult;

        when(geocodingApiRequest.makeFindPlaceDataRequest(place))
                .thenReturn(geocodingResults);

        //When
        double[] results = geoApiClient.findCoordinates(place);

        //Then
        assertEquals(2, results.length);
        assertEquals(51.1, results[0]);
        assertEquals(17.03, results[1]);

    }
}