package com.mefp.appdeclaration.service;

import com.mefp.appdeclaration.entities.FichePrevention;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface FichePreventionService {

    FichePrevention createPrevention(FichePrevention fichePrevention);
    List<FichePrevention> findAllPrevention();
    Optional<FichePrevention> updatePrevention(FichePrevention fichePrevention, Long id);
}
