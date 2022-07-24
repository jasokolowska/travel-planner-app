package com.sokolowska.travelplannerapi.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RouteDto {

    private PlaceDto destination;
    private PlaceDto origin;
    private double maxPrice;
    private boolean emailNotification;

}
