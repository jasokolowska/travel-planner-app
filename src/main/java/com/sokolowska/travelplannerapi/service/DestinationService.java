package com.sokolowska.travelplannerapi.service;

import com.sokolowska.travelplannerapi.model.Destination;
import com.sokolowska.travelplannerapi.repository.DestinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DestinationService {

    private final DestinationRepository destinationRepository;

    public Destination save(Destination destination){
        return destinationRepository.save(destination);
    }

    public List<Destination> findAll() {
        return destinationRepository.findAll();
    }

    public void deleteById(Long id) {
        destinationRepository.deleteById(id);
    }
}
