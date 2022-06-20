package com.sokolowska.travelplannerapi.repository;

import com.sokolowska.travelplannerapi.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
}
