/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.service;

import com.mfptps.appdgessddi.entities.Evaluation;
import com.mfptps.appdgessddi.entities.Programmation;
import com.mfptps.appdgessddi.service.dto.PeriodesDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public interface EvaluationService {

    Evaluation create(Evaluation evaluation);

    void addEvaluation(List<PeriodesDTO> periodes, Programmation programmation);

    /**
     * Return the Id of Periode come from Evaluation ligne
     *
     * @param programmationId
     * @return
     * @throws CustomException
     */
    Long checkPeriodeEvaluation(Long programmationId) throws CustomException;

    Page<Evaluation> findAllByProgrammation(Long progammationId, Pageable pageable);
}
