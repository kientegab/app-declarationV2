/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.repositories;

import com.mfptps.appdgessddi.entities.Programmation;
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
    List<Programmation> findAll(Long structureId);

    @Query("SELECT p FROM Programmation p, Structure s "
            + "WHERE p.deleted = false "
            + "AND p.structure.id = s.id "
            + "AND s.id = :structureId")
    Page<Programmation> findAll(Long structureId, Pageable Pageable);

    @Query("SELECT p FROM Programmation p, Structure s "
            + "WHERE p.deleted = false "
            + "AND p.structure.id = s.id "
            + "AND s.id = :structureId "
            + "AND p.validationInterne = true "
            + "AND p.validationFinal = true")
    Page<Programmation> findAllValided(Long structureId, Pageable Pageable);

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

    @Query("SELECT p FROM Programmation p, Structure s, Exercice e "
            + "WHERE p.deleted = false "
            + "AND p.structure.id = s.id AND s.id = :structureId "
            + "AND p.exercice.id = e.id AND e.id = :exerciceId "
            + "AND p.validationInterne = true "
            + "AND p.validationFinal = true")
    List<Programmation> findByStructureAndExerciceValided(long structureId, long exerciceId);

    @Query("SELECT p FROM Programmation p, Structure s, Exercice e, Evaluation ev "
            + "WHERE p.deleted = false "
            + "AND p.structure.id = s.id AND s.id = :structureId "
            + "AND p.exercice.id = e.id AND e.id = :exerciceId "
            + "AND p.id = ev.programmation.id AND ev.periode.id = :periodeId "
            + "AND p.validationInterne = true AND p.validationFinal = true")
    List<Programmation> findByStructureAndExerciceAndPeriodeValided(long structureId, long exerciceId, long periodeId);

    @Query("SELECT p FROM Programmation p, Exercice e, Structure s, MinistereStructure ms "
            + "WHERE p.deleted = false "
            + "AND p.validationInterne = true AND p.validationFinal = true " //s'assurer que la programmation a ete validee
            + "AND p.exercice.id = e.id AND e.id = :exerciceId " //filtrer par exercice
            + "AND p.structure.id = s.id " //liaison entre programmation et structure
            + "AND s.id = ms.structure.id AND ms.statut = true AND ms.ministere.id = :ministereId") //filtrer enfin par ministere
    List<Programmation> findByMinistereAndExerciceValided(long ministereId, long exerciceId);

    @Query("SELECT p FROM Programmation p, Exercice e, Evaluation ev, Structure s, MinistereStructure ms "
            + "WHERE p.deleted = false "
            + "AND p.validationInterne = true AND p.validationFinal = true " //s'assurer que la programmation a ete validee
            + "AND p.exercice.id = e.id AND e.id = :exerciceId " //filtrer par exercice
            + "AND p.id = ev.programmation.id AND ev.periode.id = :periodeId " //filter par periode
            + "AND p.structure.id = s.id " //liaison entre programmation et structure
            + "AND s.id = ms.structure.id AND ms.statut = true AND ms.ministere.id = :ministereId")
    List<Programmation> findByMinistereAndExerciceAndPeriodeValided(long ministereId, long exerciceId, long periodeId);
    
    @Query("SELECT COUNT(p) FROM Programmation p, Structure s, Exercice e "
            + "WHERE p.structure.id = s.id "
            + "AND s.id =?1 "
            + "AND p.exercice.id =?2 "
            + "AND p.deleted = false "
            + "AND p.validationFinal.id = true")
    long countStructureProgrammation(long structureId, long exerciceId);
    
    @Query("SELECT SUM(p.coutReel) FROM Programmation p, Structure s, Exercice e "
            + "WHERE p.structure.id = s.id "
            + "AND s.id =?1 "
            + "AND p.exercice.id =?2 "
            + "AND p.deleted = false "
            + "AND p.validationFinal.id = true")
    double coutTotalStructureProgrammation(long structureId, long exerciceId);
}