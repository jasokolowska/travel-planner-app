package com.sokolowska.travelplannerapi.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class LocationDto {

    private String name;
    private Double latitude;
    private Double longitude;
    private String geoApiPlaceId;
//    private List<AirportDto> airports;
}
