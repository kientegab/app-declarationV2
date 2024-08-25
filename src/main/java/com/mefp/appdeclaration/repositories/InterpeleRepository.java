package com.mefp.appdeclaration.repositories;

import com.mefp.appdeclaration.entities.Interpele;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterpeleRepository extends JpaRepository<Interpele, Long> {


    Optional<Interpele> findInterpeleByFicheId(Long aLong);
}
