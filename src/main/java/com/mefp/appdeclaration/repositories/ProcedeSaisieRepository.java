package com.mefp.appdeclaration.repositories;

import com.mefp.appdeclaration.entities.NatureSaisie;
import com.mefp.appdeclaration.entities.ProcedeSaisie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcedeSaisieRepository extends JpaRepository<ProcedeSaisie, Long> {
   List<ProcedeSaisie>  findProcedeSaisieByFicheSaisieId(Long id);

}
