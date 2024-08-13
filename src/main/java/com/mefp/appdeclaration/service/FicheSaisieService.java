package com.mefp.appdeclaration.service;

import com.mefp.appdeclaration.entities.FicheSaisie;
import com.mefp.appdeclaration.service.dto.FicheSaisieDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface FicheSaisieService{
    FicheSaisieDTO create (FicheSaisieDTO ficheSaisieDTO);

    List<FicheSaisie> findAll();


    Optional<FicheSaisieDTO> update(FicheSaisieDTO ficheSaisieDTO, Long id);

    Optional<FicheSaisie> findFicheSaisieById(Long id);
}
