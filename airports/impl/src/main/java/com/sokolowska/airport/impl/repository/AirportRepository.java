package com.sokolowska.airport.impl.repository;

import com.sokolowska.airport.impl.model.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

    boolean existsByCode(String code);
}
