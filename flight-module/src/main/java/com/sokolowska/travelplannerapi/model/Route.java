package com.sokolowska.travelplannerapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @ManyToOne
//    @JoinColumn(name = "destination_id")
//    private Place destination;
//
//    @ManyToOne
//    @JoinColumn(name = "origin_id")
//    private Place origin;

    private BigDecimal maxPrice;

    private boolean emailNotification;

    @OneToMany
    @JoinColumn(name = "route_id")
    private List<Flight> flights = new LinkedList<>();

}
