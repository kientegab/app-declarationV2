/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.repositories;

import com.mfptps.appdgessddi.entities.Evaluation;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    @Query("SELECT e FROM Evaluation e, Periode p, Programmation pro "
            + "WHERE e.programmation.id = pro.id AND pro.id = :id "
            + "AND e.periode.id = p.id "
            + "AND :date BETWEEN p.debut AND p.fin")
    Optional<Evaluation> findByProgrammationAndPeriode(Long id, Date date);

}
