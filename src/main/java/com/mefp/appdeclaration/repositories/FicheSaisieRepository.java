package com.mefp.appdeclaration.repositories;

import com.mefp.appdeclaration.entities.FicheSaisie;
import com.mefp.appdeclaration.service.dto.FicheSaisieDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FicheSaisieRepository extends JpaRepository<FicheSaisie, Long> {

}
