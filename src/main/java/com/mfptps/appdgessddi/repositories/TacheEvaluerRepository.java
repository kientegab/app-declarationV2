/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.repositories;

import com.mfptps.appdgessddi.entities.TacheEvaluer;
import java.util.Optional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public interface TacheEvaluerRepository extends JpaRepository<TacheEvaluer, Long> {

    @Query("SELECT te FROM TacheEvaluer te "
            + "WHERE te.tache.id = :id "
            + "AND te.cumuleeActive = true")
    Optional<TacheEvaluer> findByIdAndActive(Long id);

    Optional<TacheEvaluer> findByCumuleeActiveIsTrue();

    //return the sum of the cumulative values ​​of the tasks of a given program
    @Query("SELECT SUM(te.valeurCumulee) AS val_actuelle FROM TacheEvaluer te, Tache t, Programmation p "
            + "WHERE te.tache.id = t.id AND te.cumuleeActive = true "
            + "AND t.programmation.id = p.id AND p.id = :programmationId ")
    double sumOfCumuleesTachesByProgrammation(@NotNull @NotBlank Long programmationId);
}