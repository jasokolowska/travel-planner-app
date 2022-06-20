package com.sokolowska.travelplannerapi.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Place {

    @Id
    @GeneratedValue
    private long id;

    private String name;
    private Double latitude;
    private Double longitude;

    @OneToMany
    private List<Airport> airports;

}
