/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.repositories;

import com.mfptps.appdgessddi.entities.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    @Query("SELECT e.valeur FROM Evaluation e WHERE e.structure.id=?1 AND e.exercice.id=?2")
    public double findEvaluation(long structureId, long exerciceId);
}
