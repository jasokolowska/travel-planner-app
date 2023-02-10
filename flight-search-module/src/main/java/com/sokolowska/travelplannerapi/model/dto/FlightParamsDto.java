package com.sokolowska.travelplannerapi.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightParamsDto {

    String destination;
    String origin;
    LocalDate dateFrom;
    LocalDate dateTo;
    int stopovers;
    boolean twoWayTrip;
    int days;
}
