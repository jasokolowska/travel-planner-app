package com.sokolowska.travelplannerapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Place {

    private String name;
    private Double latitude;
    private Double longitude;

}
