package com.mfptps.appdgessddi.repositories;

import com.mfptps.appdgessddi.entities.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionRepository extends JpaRepository<Action,Long> {

    List<Action> findByLibelleContainingIgnoreCase(String libelle);

}
