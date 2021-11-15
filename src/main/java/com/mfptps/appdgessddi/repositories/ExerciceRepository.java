package com.mfptps.appdgessddi.repositories;

import com.mfptps.appdgessddi.entities.Exercice;
import com.mfptps.appdgessddi.enums.ExerciceStatus;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciceRepository extends JpaRepository<Exercice, Long> {

    public Optional<Exercice> findByStatut(ExerciceStatus exerciceStatus);
}
