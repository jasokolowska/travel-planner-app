package com.sokolowska.travelplannerapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Route {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Place destination;

    @ManyToOne
    @JoinColumn(name = "origin_id")
    private Place origin;

    private BigDecimal maxPrice;

}