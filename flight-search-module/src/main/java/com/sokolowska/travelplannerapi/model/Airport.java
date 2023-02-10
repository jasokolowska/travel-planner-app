package com.sokolowska.travelplannerapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Airport {

    @Id
    @GeneratedValue
    private long id;

    private String code;
    private String name;
}
