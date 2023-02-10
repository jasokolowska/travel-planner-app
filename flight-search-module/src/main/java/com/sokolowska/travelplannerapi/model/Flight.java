package com.sokolowska.travelplannerapi.model;

import lombok.*;

import jakarta.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Flight {

    @Id
    @GeneratedValue
    private long id;

    private String cityFrom;
    private String cityTo;
    private String departure;
    private String arrival;
    private double price;
    @Column(length = 1000)
    private String link;
}
