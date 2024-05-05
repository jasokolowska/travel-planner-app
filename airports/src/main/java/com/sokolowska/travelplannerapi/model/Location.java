package com.sokolowska.travelplannerapi.model;

import lombok.*;

import jakarta.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private Double latitude;
    private Double longitude;
    private String geoApiPlaceId;

    @OneToMany(mappedBy = "location")
    private List<Airport> airports;
}
