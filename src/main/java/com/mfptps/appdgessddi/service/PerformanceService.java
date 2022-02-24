package com.mfptps.appdgessddi.service;

import com.mfptps.appdgessddi.entities.Performance;
import com.mfptps.appdgessddi.service.dto.PerformanceDTO;
import com.mfptps.appdgessddi.service.dto.PerformanceEntityDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PerformanceService {

    Performance create(PerformanceDTO performanceDTO);

    Performance update(Performance performance);

    Optional<Performance> get(Long id);

    /**
     * Recherche la performance d'une structure pour un exercice donne
     *
     * @param structureId
     * @param exerciceId
     * @return
     */
    Page<PerformanceEntityDTO> getByStructure(long structureId, long exerciceId, Pageable pageable);

    /**
     * Recherche la performance d'une structure pour l'exercice en cours
     *
     * @param structureId
     * @return
     */
    Page<PerformanceEntityDTO> getByStructureAndExerciceENCOURS(long structureId);

    Page<Performance> findAll(Pageable pageable);

    /**
     * Liste les performances d'un ministere pour un exercice donne
     *
     * @param ministereId
     * @param exerciceId
     * @return
     */
    Page<PerformanceEntityDTO> findAllByMinistere(long ministereId, long exerciceId, Pageable pageable);

    /**
     * Liste les performances d'un ministere pour l'exercice en cours
     *
     * @param ministereId
     * @return
     */
    Page<PerformanceEntityDTO> findAllByMinistereAndExerciceENCOURS(long ministereId, Pageable pageable);

    void delete(Long id);
}
