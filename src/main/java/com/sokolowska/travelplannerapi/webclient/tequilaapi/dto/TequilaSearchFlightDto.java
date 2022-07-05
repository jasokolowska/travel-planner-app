package com.sokolowska.travelplannerapi.webclient.tequilaapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TequilaSearchFlightDto {

    @JsonProperty("data")
    private List<TequilaFlightDataDto> data;
}
