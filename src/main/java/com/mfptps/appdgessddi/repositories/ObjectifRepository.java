package com.mfptps.appdgessddi.repositories;


import com.mfptps.appdgessddi.entities.Objectif;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ObjectifRepository extends JpaRepository<Objectif, Long> {
    Optional<Objectif> findByLibelle(String libelle);
}
