package com.mfptps.appdgessddi.repositories;

import com.mfptps.appdgessddi.entities.Action;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActionRepository extends JpaRepository<Action,Long> {

    Optional<Action> findByLibelle(String libelle);

}
