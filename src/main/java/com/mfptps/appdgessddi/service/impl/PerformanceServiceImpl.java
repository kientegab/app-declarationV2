package com.mfptps.appdgessddi.service.impl;

import com.mfptps.appdgessddi.entities.Performance;
import com.mfptps.appdgessddi.repositories.PerformanceRepository;
import com.mfptps.appdgessddi.service.PerformanceService;
import com.mfptps.appdgessddi.service.dto.PerformanceDTO;
import com.mfptps.appdgessddi.service.mapper.PerformanceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
public class PerformanceServiceImpl  implements PerformanceService {

    private final PerformanceRepository performanceRepository ;
    private final PerformanceMapper performanceMapper ;

    public PerformanceServiceImpl(PerformanceRepository performanceRepository, PerformanceMapper performanceMapper) {
        this.performanceRepository = performanceRepository;
        this.performanceMapper = performanceMapper;
    }


    @Override
    public Performance create(PerformanceDTO performanceDTO) {
        Performance performance=performanceMapper.toEntity(performanceDTO);
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
    @Transactional(readOnly = true)
    public Page<Performance> findAll(Pageable pageable) {
        return performanceRepository.findAll(pageable);
    }

    @Override
    public void delete(Long id) {
       performanceRepository.deleteById(id);
    }
}
