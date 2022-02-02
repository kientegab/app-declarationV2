/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.repositories;

import com.mfptps.appdgessddi.entities.Evaluation;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    List<Evaluation> findByProgrammationAndPeriode(Long id, Date date);

    @Query("SELECT e FROM Evaluation e, Periode p, Programmation pro "
            + "WHERE e.programmation.id = pro.id AND pro.id = :id "
            + "AND e.periode.id = p.id ")
    List<Evaluation> findByProgrammationAndPeriode(Long id);

    Page<Evaluation> findByProgrammationId(long id, Pageable pageable);

}
