package com.sokolowska.airport.webclient.geoapi;

import static org.junit.jupiter.api.Assertions.*;

import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import com.google.maps.model.LatLng;
import com.sokolowska.travelplannerapi.model.LocationData;
import com.sokolowska.travelplannerapi.webclient.geoapi.GeoApiServiceImpl;
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
    private GeoApiServiceImpl geoApiClient;

    @Mock
    private GeocodingApi geocodingApi;

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

        when(GeocodingApi.newRequest(any()).address(any()).awaitIgnoreError())
                .thenReturn(geocodingResults);

        //When
        LocationData results = geoApiClient.findCoordinates(place);

        //Then
        assertEquals(51.1, results.latitude());
        assertEquals(17.03, results.longitude());

    }
}