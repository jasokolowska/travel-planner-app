package com.sokolowska.travelplannerapi.google;

import com.sokolowska.travelplannerapi.model.Place;

public interface GeoApiService {

    Place findCoordinates(String placeName);
}
