package com.mefp.appdeclaration.service;

import com.mefp.appdeclaration.entities.Interpele;
import com.mefp.appdeclaration.repositories.InterpeleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class InterpeleService {
    private final InterpeleRepository interpeleRepository;

    public InterpeleService(InterpeleRepository interpeleRepository) {
        this.interpeleRepository = interpeleRepository;
    }

    public Optional<Interpele> findByFicheSaisieId(Long id){
        return interpeleRepository.findInterpeleByFicheId(id);
    }
}
