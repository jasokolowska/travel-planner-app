package com.sokolowska.travelplannerapi.api;

import com.sokolowska.travelplannerapi.api.dto.AirportDto;

import java.util.List;

public interface LocationService {
    List<AirportDto> findOriginAirportsByLocation(String locationName);

    List<AirportDto> findDestinationAirportsByLocation(String locationName);
}
