package com.mefp.appdeclaration.service;

import com.mefp.appdeclaration.entities.Destination;
import com.mefp.appdeclaration.repositories.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class DestinationService {
    @Autowired
    private final DestinationRepository destinationRepository;

    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public Optional<Destination> findByFicheSaisieId(Long id){
        return destinationRepository.findDestinationByFicheId(id);
    }
}
