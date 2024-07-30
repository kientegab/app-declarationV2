package com.mefp.appdeclaration.repositories;


import com.mefp.appdeclaration.entities.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VilleRepository extends JpaRepository<Ville,Long> {
    @Query("SELECT a FROM Ville a WHERE a.pays.id = :paysId")
    List<Ville> findByPaysId(Long paysId);
}
