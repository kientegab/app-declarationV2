/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.repositories;

import com.mfptps.appdgessddi.entities.Programmation;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public interface ProgrammationRepository extends JpaRepository<Programmation, Long> {

    @Query("SELECT p FROM Programmation p, Structure s "
            + "WHERE p.deleted = false "
            + "AND p.structure.id = s.id "
            + "AND s.id = :structureId")
    Page<Programmation> findAll(Long structureId, Pageable Pageable);

    @Query("SELECT p FROM Programmation p, Structure s "
            + "WHERE p.id = :id "
            + "AND p.deleted = false "
            + "AND p.structure.id = s.id "
            + "AND s.id = :structureId")
    Optional<Programmation> findById(Long structureId, Long id);

    @Query("SELECT p FROM Programmation p, Structure s, Activites a "
            + "WHERE p.deleted = false "
            + "AND p.structure.id = s.id "
            + "AND s.id = :structureId "
            + "AND p.activite.id = a.id "
            + "AND UPPER(a.libelle) LIKE CONCAT('%',UPPER(:libelle),'%')")
    Page<Programmation> findByLibelle(Long structureId, String libelle, Pageable pageable);

    @Query("SELECT COUNT(*) FROM Programmation p, Structure s "
            + "WHERE p.structure.id = s.id "
            + "AND s.id = :structureId")
    long countProgrammationByStrucutre(long structureId);

    //Page<Programmation> findByActiviteLibelleContainingIgnoreCase(String libelle, Pageable pageable);
}
