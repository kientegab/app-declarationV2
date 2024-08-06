package com.mefp.appdeclaration.repositories;

import com.mefp.appdeclaration.entities.Activite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActiviteRepository extends JpaRepository<Activite, Long> {
}
