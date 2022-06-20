package com.sokolowska.travelplannerapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
