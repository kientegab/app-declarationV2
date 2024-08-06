package com.mefp.appdeclaration.repositories;

import com.mefp.appdeclaration.entities.Nature;
import com.mefp.appdeclaration.entities.NatureSaisie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NatureSaisieRepository extends JpaRepository<NatureSaisie, Long> {
}
