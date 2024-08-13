package com.mefp.appdeclaration.repositories;

import com.mefp.appdeclaration.entities.IntervenantSaisie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IntervSaisieRepository extends JpaRepository<IntervenantSaisie, Long> {
    List<IntervenantSaisie> findIntervenantSaisieByFicheSaisieId(Long id);
}
