package com.sokolowska.travelplannerapi.webclient.tequilaapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TequilaFlightDataDto {

    private String cityFrom;
    private String cityTo;
    private int price;
    @JsonProperty("deep_link")
    private String deepLink;
    @JsonProperty("local_arrival")
    private String localArrival;
    @JsonProperty("local_departure")
    private String localDeparture;

}
