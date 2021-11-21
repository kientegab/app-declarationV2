package com.mfptps.appdgessddi.service;

import com.mfptps.appdgessddi.entities.Performance;
import com.mfptps.appdgessddi.service.dto.PerformanceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PerformanceService {

    Performance create(PerformanceDTO performanceDTO);

    Performance update(Performance performance);

    Optional<Performance> get(Long id);

    Page<Performance> findAll(Pageable pageable);

    void delete(Long id);
}
