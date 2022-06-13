package com.sokolowska.travelplannerapi.repository;

import com.sokolowska.travelplannerapi.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {
}
