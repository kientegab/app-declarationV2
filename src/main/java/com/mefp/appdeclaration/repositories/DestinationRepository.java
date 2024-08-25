package com.mefp.appdeclaration.repositories;

import com.mefp.appdeclaration.entities.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DestinationRepository extends JpaRepository<Destination , Long> {

    Optional<Destination> findDestinationByFicheId(Long aLong);
}
