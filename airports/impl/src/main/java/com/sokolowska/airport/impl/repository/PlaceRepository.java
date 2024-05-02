package com.sokolowska.airport.impl.repository;

import com.sokolowska.airport.impl.model.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Location, Long> {

    Optional<Location> findByName(String name);
}
