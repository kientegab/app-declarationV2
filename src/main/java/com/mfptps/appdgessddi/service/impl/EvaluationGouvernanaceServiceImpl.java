package com.mfptps.appdgessddi.service.impl;

import com.mfptps.appdgessddi.entities.EvaluationGouvernance;
import com.mfptps.appdgessddi.repositories.CritereGouvernanceRepository;
import com.mfptps.appdgessddi.repositories.EvaluationGouvernanceRepository;
import com.mfptps.appdgessddi.service.EvaluationGouvernanaceService;
import com.mfptps.appdgessddi.service.dto.CritereDTO;
import com.mfptps.appdgessddi.service.dto.EvaluationGouvernanceDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class EvaluationGouvernanaceServiceImpl implements EvaluationGouvernanaceService {

    private final EvaluationGouvernanceRepository evaluationGouvernanceRepository;
    private final CritereGouvernanceRepository critereGouvernanceRepository;

    public EvaluationGouvernanaceServiceImpl(EvaluationGouvernanceRepository evaluationGouvernanceRepository, CritereGouvernanceRepository critereGouvernanceRepository) {
        this.evaluationGouvernanceRepository = evaluationGouvernanceRepository;
        this.critereGouvernanceRepository = critereGouvernanceRepository;
    }

    @Override
    public EvaluationGouvernance create(EvaluationGouvernanceDTO evaluationGouvernanceDTO) {
        List<EvaluationGouvernance> evaluationGouvernances = new ArrayList<>();

        for (CritereDTO critere : evaluationGouvernanceDTO.getCritereGouvernances()) {
            EvaluationGouvernance eg = new EvaluationGouvernance();

            eg.setValeurReference(!critere.getCritereGouvernance().isMode() ? critere.getValeurReference() : 1);
            eg.setCritereGouvernance(critere.getCritereGouvernance());
            eg.setNonapplicable(critere.isNonapplicable());
            eg.setExercice(evaluationGouvernanceDTO.getExercice());
            eg.setStructure(evaluationGouvernanceDTO.getStructure());

            evaluationGouvernances.add(eg);
        }

        evaluationGouvernances = evaluationGouvernanceRepository.saveAll(evaluationGouvernances);

        return evaluationGouvernances.get(0);
    }

    @Override
    public EvaluationGouvernance update(EvaluationGouvernance evaluationGouvernance) {
        return evaluationGouvernanceRepository.save(evaluationGouvernance);
    }

    @Override
    public Optional<EvaluationGouvernance> get(Long id) {
        return evaluationGouvernanceRepository.findById(id);
    }

    @Override
    public Page<EvaluationGouvernance> get(Pageable pageable) {
        return evaluationGouvernanceRepository.findAll(pageable);
    }
}
