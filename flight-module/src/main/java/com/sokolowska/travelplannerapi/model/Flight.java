package com.sokolowska.travelplannerapi.model;

import lombok.*;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String cityFrom;
    private String cityTo;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private BigDecimal price;
    @Column(length = 4000)
    private String link;
}
