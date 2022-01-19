/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.repositories;

import com.mfptps.appdgessddi.entities.Programmation;
import com.mfptps.appdgessddi.service.reportentities.ActiviteRE;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Query("SELECT COUNT(*) FROM Programmation p, Structure s, Objectif o "
            + "WHERE p.structure.id = s.id "
            + "AND s.id = :structureId "
            + "AND p.objectif.id = o.id "
            + "AND o.id = :objectifId")
    long countProgrammationByStrucutreAndObjectif(long structureId, long objectifId);

    @Modifying
    @Query("UPDATE Programmation p SET p.deleted = true "
            + "WHERE p.id = :programmationId "
            + "AND p.structure.id = :structureId")
    int deleteById(@Param("structureId") Long structureId, @Param("programmationId") Long programmationId);

    @Query("SELECT p FROM Programmation p, Structure s, Exercice e "
            + "WHERE p.deleted = false "
            + "AND p.structure.id = s.id AND s.id = :structureId "
            + "AND p.exercice.id = e.id AND e.id = :exerciceId "
            + "GROUP BY p.objectif.id")
    List<Programmation> findByStructureAndExercice(long structureId, long exerciceId);


//    @Query("SELECT new com.mfptps.appdgessddi.service.reportentities.ObjectifsOperationnelsRE(s.type, COUNT(s.type)) "
//            + "FROM Programmation ")
//    List<ObjectifsOperationnelsRE> findtoPa(long structureId, long exerciceId);
    
//    @Query("SELECT new com.mfptps.appdgessddi.service.reportentities.ActiviteRE(p.code, p.activite.libelle,ind.libelle, p.cible,p.coutPrevisionnel, p.coutReel,p.sourceFinancement.libelle, p.structure.libelle) "
//            + "FROM Programmation p, Objectif o, IndicateurObjectif ind WHERE p.objectif.id=o.id AND o.id=ind.objectif.id AND p.exercice.id=:exerciceId AND o.id=:objectifID AND p.deleted = false")
//    List<ActiviteRE> constructActiviteRE(long exerciceId, long objectifID);

}
