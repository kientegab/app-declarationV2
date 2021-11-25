/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.service.impl;

import com.mfptps.appdgessddi.entities.Evaluation;
import com.mfptps.appdgessddi.entities.Periode;
import com.mfptps.appdgessddi.entities.Programmation;
import com.mfptps.appdgessddi.repositories.EvaluationRepository;
import com.mfptps.appdgessddi.repositories.PeriodeRepository;
import com.mfptps.appdgessddi.service.CustomException;
import com.mfptps.appdgessddi.service.EvaluationService;
import com.mfptps.appdgessddi.service.dto.PeriodesDTO;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Slf4j
@Service
public class EvaluationServiceImpl implements EvaluationService {

    private EvaluationRepository evaluationRepository;

    private PeriodeRepository periodeRepository;

    public EvaluationServiceImpl(EvaluationRepository evaluationRepository,
            PeriodeRepository periodeRepository) {
        this.evaluationRepository = evaluationRepository;
        this.periodeRepository = periodeRepository;
    }

    @Override
    public Evaluation create(Evaluation evaluation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * automatically adds the number of lines corresponding to the number of
     * periods checked (true)
     *
     * @param periodes : list of periodes(from ProgrammationDTO) and its value
     * checked
     * @param programmation : Object Programmation saving
     */
    public void addEvaluation(List<PeriodesDTO> periodes, Programmation programmation) {
        List<Periode> periodesParametrees = periodeRepository.findByPeriodiciteActif();
        List<Evaluation> evaluations = new ArrayList<>();

        if (periodes.size() != periodesParametrees.size()) {
            throw new CustomException("Périodes non conformes à celles parametrées.");
        }

        periodes.forEach(p -> {
            periodesParametrees.stream().filter(pp -> ((p.getLibelle().trim().charAt(0) == pp.getLibelle().charAt(0))
                    && (p.getLibelle().trim().charAt(p.getLibelle().length() - 1)
                    == pp.getLibelle().charAt(pp.getLibelle().length() - 1))
                    && p.isValeur())).map(pp -> {
                //if elem of periodesDTO is true and fisrtIndex/lastIndex of libelles are equals
                Evaluation evaluation = new Evaluation();
                evaluation.setPeriode(pp);
                return evaluation;
            }).map(evaluation -> {
                evaluation.setProgrammation(programmation);
                return evaluation;
            }).forEachOrdered(evaluation -> {
                evaluations.add(evaluation);
            });
        });
// above similars loops
//
//        for (PeriodesDTO p : periodes) {
//            for (Periode pp : periodesParametrees) {
//                if ((p.getLibelle().trim().charAt(0) == pp.getLibelle().charAt(0))
//                        && (p.getLibelle().trim().charAt(p.getLibelle().length() - 1)
//                        == pp.getLibelle().charAt(pp.getLibelle().length() - 1))
//                        && p.isValeur()) {//if elem of periodesDTO is true and fisrtIndex/lastIndex of libelles are equals
//                    Evaluation evaluation = new Evaluation();
//                    evaluation.setPeriode(pp);
//                    evaluation.setProgrammation(programmation);
//                    evaluations.add(evaluation);
//                }
//            }
//        }
        evaluationRepository.saveAll(evaluations);
    }
}
