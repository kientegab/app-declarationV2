package com.mefp.appdeclaration.repositories;

import com.mefp.appdeclaration.entities.FichePrevention;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FichePreventionRepository extends JpaRepository<FichePrevention, Long> {
}
