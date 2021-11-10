package com.mfptps.appdgessddi.service;

import com.mfptps.appdgessddi.entities.Structure;
import com.mfptps.appdgessddi.service.dto.MinistereDTO;
import com.mfptps.appdgessddi.service.dto.StructureDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface StructureService {
    Structure create(StructureDTO  structure);
    Structure update(Structure structure);
    Optional<Structure> get(long id);
    Page<Structure> findAll(Pageable pageable);
    void delete(Long code);
}
