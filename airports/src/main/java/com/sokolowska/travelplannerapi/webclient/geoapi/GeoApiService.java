package com.sokolowska.travelplannerapi.webclient.geoapi;

import com.sokolowska.travelplannerapi.model.LocationData;

public interface GeoApiService {

    LocationData findCoordinates(String placeName);
}
