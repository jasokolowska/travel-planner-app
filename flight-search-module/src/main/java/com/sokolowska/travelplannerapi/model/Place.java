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
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private Double latitude;
    private Double longitude;

    @OneToMany(mappedBy = "place")
    private List<Airport> airports;

}
