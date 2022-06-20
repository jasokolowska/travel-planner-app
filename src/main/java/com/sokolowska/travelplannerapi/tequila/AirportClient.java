package com.sokolowska.travelplannerapi.tequila;

import com.sokolowska.travelplannerapi.model.Airport;

import java.util.List;

public interface AirportClient {

    public AirportListDto getAirports(Double latitude, Double longitude);

}
