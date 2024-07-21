package com.sokolowska.airport.webclient.geoapi;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.PendingResult;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
//@PrepareForTest({GeocodingApi.class, GeocodingApiRequest.class})
class GeoApiClientTest {

    @InjectMocks
    private GeoApiServiceImpl geoApiService;

    @Mock
    private GeoApiContext geoApiContext;

    @Test
    void shouldReturnCoordinatesOfPlace() throws IOException, InterruptedException, ApiException {
        //Given
        String place = "Wroclaw";

        GeocodingResult geocodingResult = new GeocodingResult();
        geocodingResult.geometry = new Geometry();
        geocodingResult.geometry.location = new LatLng();
        geocodingResult.geometry.location.lat = 51.1;
        geocodingResult.geometry.location.lng = 17.03;

        GeocodingResult[] geocodingResults = new GeocodingResult[]{geocodingResult};

        GeocodingApiRequest geocodingApiRequest = mock(GeocodingApiRequest.class);
        when(geocodingApiRequest.address(eq(place))).thenReturn(geocodingApiRequest);
        when(geocodingApiRequest.await()).thenReturn(geocodingResults);

        mockStatic(GeocodingApi.class);

        when(GeocodingApi.newRequest(any()))
                .thenReturn(geocodingApiRequest);

        //When
        LocationData results = geoApiService.findCoordinates(place);

        //Then
        assertEquals(51.1, results.latitude());
        assertEquals(17.03, results.longitude());
    }
}