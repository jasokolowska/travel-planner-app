package com.sokolowska.travelplannerapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FlightDto {

    private String cityFrom;
    private String cityTo;
    private int price;
    private String link;
    private String arrival;
    private String departure;
}
