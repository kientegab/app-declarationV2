package com.mefp.appdeclaration.service;

import com.mefp.appdeclaration.entities.IntervenantSaisie;
import com.mefp.appdeclaration.entities.NatureSaisie;
import com.mefp.appdeclaration.repositories.IntervSaisieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntervService {
    @Autowired
    private final IntervSaisieRepository intervSaisieRepository;

    public IntervService(IntervSaisieRepository intervSaisieRepository) {
        this.intervSaisieRepository = intervSaisieRepository;
    }

    public List<IntervenantSaisie> findIntervByFicheSaisieId(Long id){return intervSaisieRepository.findIntervenantSaisieByFicheSaisieId(id);}
}
