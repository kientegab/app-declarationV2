package com.mefp.appdeclaration.service;

import com.mefp.appdeclaration.entities.FicheSaisie;
import com.mefp.appdeclaration.entities.Structure;
import com.mefp.appdeclaration.entities.dto.EtatSaisie;
import com.mefp.appdeclaration.entities.dto.EtatSaisieDetail;
import com.mefp.appdeclaration.service.dto.FicheSaisieDTO;
import com.mefp.appdeclaration.service.dto.FicheSaisieCompDTO;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

@Service
public interface FicheSaisieService{
    FicheSaisieDTO create (FicheSaisieDTO ficheSaisieDTO,String matricule);

    FicheSaisieCompDTO ajouter(FicheSaisieCompDTO ficheSaisieCompDTO);
    List<FicheSaisie> findAll();
    List<FicheSaisie> findBystructureSaisieId(Long id);
    List<EtatSaisie> findPersonalise(Long id);
    List<EtatSaisieDetail> findPersonaliseDetail(Long id);

    String numeroSaisie(String matricule);


    Optional<FicheSaisieDTO> update(FicheSaisieDTO ficheSaisieDTO, Long id);

    Optional<FicheSaisie> findFicheSaisieById(Long id);

    void exportDeclaration(Structure structure,OutputStream outputStream);

    void exportSaisieDetails(Structure structure,OutputStream outputStream);
}
