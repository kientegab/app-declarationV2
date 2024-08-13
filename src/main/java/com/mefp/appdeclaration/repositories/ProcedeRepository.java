package com.mefp.appdeclaration.repositories;

import com.mefp.appdeclaration.entities.Procede;
import com.mefp.appdeclaration.entities.ProcedeSaisie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcedeRepository extends JpaRepository<Procede, Long> {

}
