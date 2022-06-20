package com.sokolowska.travelplannerapi.google;

import com.sokolowska.travelplannerapi.model.Place;

public interface GeoApiService {

    double[] findCoordinates(String placeName);
}
