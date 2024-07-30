package com.mefp.appdeclaration.repositories;

import com.mefp.appdeclaration.entities.Nature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NatureRepository extends JpaRepository<Nature , Long> {
}
