package com.sokolowska.travelplannerapi.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sokolowska.travelplannerapi.model.Airport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceDto {

    private String name;
    private Double latitude;
    private Double longitude;
    private List<Airport> airports;
}
