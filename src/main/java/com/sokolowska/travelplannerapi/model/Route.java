package com.sokolowska.travelplannerapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Route {

    @Id
    @GeneratedValue
    private long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "name", column = @Column(name = "destination_name")),
            @AttributeOverride( name = "latitude", column = @Column(name = "destination_lat")),
            @AttributeOverride( name = "longitude", column = @Column(name = "destination_long"))
    })
    private Place destination;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "name", column = @Column(name = "origin_name")),
            @AttributeOverride( name = "latitude", column = @Column(name = "origin_lat")),
            @AttributeOverride( name = "longitude", column = @Column(name = "origin_long"))
    })
    private Place origin;

    private BigDecimal maxPrice;

}
