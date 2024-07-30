package com.mefp.appdeclaration.repositories;



import com.mefp.appdeclaration.entities.Voyageur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoyageurRepository extends JpaRepository<Voyageur, Long> {
    Optional<Voyageur> findByRefDocument(String refDocument);
//    @Query("SELECT a FROM Voyageur a order by a.id")
    List<Voyageur> findAll();
}
