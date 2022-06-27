package com.sokolowska.travelplannerapi.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Flight {

    @Id
    @GeneratedValue
    private long id;

    private String cityFrom;
    private String cityTo;
    private String departure;
    private String arrival;
    private BigDecimal price;
    @Column(length = 1000)
    private String link;

}
