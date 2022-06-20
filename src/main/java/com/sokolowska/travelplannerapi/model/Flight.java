package com.sokolowska.travelplannerapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Flight {

    @Id
    @GeneratedValue
    private long id;

    private String departureCity;
    private String departureAirportCode;
    private String destinationCity;
    private String destinationAirportCode;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private BigDecimal price;

}
