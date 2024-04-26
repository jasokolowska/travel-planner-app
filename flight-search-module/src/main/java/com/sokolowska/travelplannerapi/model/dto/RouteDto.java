package com.sokolowska.travelplannerapi.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Data
@Builder
public class RouteDto {

    private PlaceDto destination;
    private PlaceDto origin;
    private BigDecimal maxPrice;
    private boolean emailNotification;

}
