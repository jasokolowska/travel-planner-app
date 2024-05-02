package com.sokolowska.airport.api.service;

import java.util.List;

public interface PlaceService {
    List<String> findAirportCodesByLocation(String locationName);
}
