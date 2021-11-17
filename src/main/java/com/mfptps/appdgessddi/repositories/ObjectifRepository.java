package com.mfptps.appdgessddi.repositories;

import com.mfptps.appdgessddi.entities.Objectif;
import com.mfptps.appdgessddi.enums.TypeObjectif;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjectifRepository extends JpaRepository<Objectif, Long> {

    Optional<Objectif> findByLibelle(String libelle);

    Page<Objectif> findByType(TypeObjectif type, Pageable pageable);
}
