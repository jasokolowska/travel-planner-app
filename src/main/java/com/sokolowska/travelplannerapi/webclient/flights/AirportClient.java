package com.sokolowska.travelplannerapi.webclient.flights;

import com.sokolowska.travelplannerapi.model.dto.AirportListDto;

public interface AirportClient {

    public AirportListDto getAirports(Double latitude, Double longitude);

}
