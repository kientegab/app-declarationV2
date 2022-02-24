package com.mfptps.appdgessddi.service.impl;

import com.mfptps.appdgessddi.entities.Exercice;
import com.mfptps.appdgessddi.entities.Performance;
import com.mfptps.appdgessddi.enums.ExerciceStatus;
import com.mfptps.appdgessddi.repositories.ExerciceRepository;
import com.mfptps.appdgessddi.repositories.PerformanceRepository;
import com.mfptps.appdgessddi.service.PerformanceService;
import com.mfptps.appdgessddi.service.dto.PerformanceDTO;
import com.mfptps.appdgessddi.service.dto.PerformanceEntityDTO;
import com.mfptps.appdgessddi.service.mapper.PerformanceMapper;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class PerformanceServiceImpl implements PerformanceService {
    
    private final PerformanceRepository performanceRepository;
    private final ExerciceRepository exerciceRepository;
    private final PerformanceMapper performanceMapper;
    
    public PerformanceServiceImpl(PerformanceRepository performanceRepository,
            ExerciceRepository exerciceRepository,
            PerformanceMapper performanceMapper) {
        this.performanceRepository = performanceRepository;
        this.exerciceRepository = exerciceRepository;
        this.performanceMapper = performanceMapper;
    }
    
    @Override
    public Performance create(PerformanceDTO performanceDTO) {
        Performance performance = performanceMapper.toEntity(performanceDTO);
        return performanceRepository.save(performance);
    }
    
    @Override
    public Performance update(Performance performance) {
        return performanceRepository.save(performance);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Performance> get(Long id) {
        return performanceRepository.findById(id);
    }
    
    @Override
    public Optional<PerformanceEntityDTO> getByStructure(long structureId, long exerciceId) {
        return performanceRepository.findCurrentStructurePerformance(structureId, exerciceId)
                .map(performanceMapper::toEntityOrigin);
    }
    
    @Override
    public Optional<PerformanceEntityDTO> getByStructureAndExerciceENCOURS(long structureId) {
        Optional<Exercice> exercice = exerciceRepository.findByStatut(ExerciceStatus.EN_COURS);
        if (exercice.isPresent()) {
            return performanceRepository.findCurrentStructurePerformance(structureId, exercice.get().getId())
                    .map(performanceMapper::toEntityOrigin);
        }
        return null;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<Performance> findAll(Pageable pageable) {
        return performanceRepository.findAll(pageable);
    }
    
    @Override
    public Page<PerformanceEntityDTO> findAllByMinistere(long ministereId, long exerciceId, Pageable pageable) {
        return performanceRepository.findAllByMinistere(ministereId, exerciceId, pageable)
                .map(performanceMapper::toEntityOrigin);
    }
    
    @Override
    public Page<PerformanceEntityDTO> findAllByMinistereAndExerciceENCOURS(long ministereId, Pageable pageable) {
        Optional<Exercice> exercice = exerciceRepository.findByStatut(ExerciceStatus.EN_COURS);
        if (exercice.isPresent()) {
            return performanceRepository.findAllByMinistere(ministereId, exercice.get().getId(), pageable)
                    .map(performanceMapper::toEntityOrigin);
        }
        return null;
    }
    
    @Override
    public void delete(Long id) {
        performanceRepository.deleteById(id);
    }
    
}
