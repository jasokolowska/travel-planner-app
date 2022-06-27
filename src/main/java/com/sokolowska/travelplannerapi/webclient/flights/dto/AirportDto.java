package com.sokolowska.travelplannerapi.webclient.flights.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sokolowska.travelplannerapi.model.Airport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AirportDto {

    @JsonProperty("code")
    private String code;

    @JsonProperty("name")
    private String name;

    public Airport toDomain(){
        Airport airport = new Airport();
        airport.setCode(code);
        airport.setName(name);
        return airport;
    }
}
