package com.sokolowska.travelplannerapi.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CriteriaDto {

    private String destination;
    private String departure;
    private int adults;
    private int children;

}
