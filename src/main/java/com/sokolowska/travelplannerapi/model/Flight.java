package com.sokolowska.travelplannerapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Flight {

    @Id
    @GeneratedValue
    private long id;

    private String origin;
    private String destination;

    public Flight(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
    }
}
