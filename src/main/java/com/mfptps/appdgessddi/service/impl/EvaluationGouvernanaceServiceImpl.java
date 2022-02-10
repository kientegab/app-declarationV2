package com.mfptps.appdgessddi.service.impl;

import com.mfptps.appdgessddi.entities.EvaluationGouvernance;
import com.mfptps.appdgessddi.repositories.EvaluationGouvernanceRepository;
import com.mfptps.appdgessddi.service.EvaluationGouvernanaceService;
import com.mfptps.appdgessddi.service.dto.EvaluationGouvernanceDTO;
import com.mfptps.appdgessddi.service.mapper.EvaluationGouvernanceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
public class EvaluationGouvernanaceServiceImpl implements EvaluationGouvernanaceService {

    private final EvaluationGouvernanceRepository evaluationGouvernanceRepository ;
    private final EvaluationGouvernanceMapper evaluationGouvernanceMapper ;

    public EvaluationGouvernanaceServiceImpl(EvaluationGouvernanceRepository evaluationGouvernanceRepository, EvaluationGouvernanceMapper evaluationGouvernanceMapper) {
        this.evaluationGouvernanceRepository = evaluationGouvernanceRepository;
        this.evaluationGouvernanceMapper = evaluationGouvernanceMapper;
    }

    @Override
    public EvaluationGouvernance create(EvaluationGouvernanceDTO evaluationGouvernanceDTO) {
        EvaluationGouvernance evaluationGouvernance =evaluationGouvernanceMapper.toEntity(evaluationGouvernanceDTO);

        return evaluationGouvernanceRepository.save(evaluationGouvernance);
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
