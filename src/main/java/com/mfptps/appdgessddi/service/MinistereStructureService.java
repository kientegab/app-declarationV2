package com.mfptps.appdgessddi.service;

import com.mfptps.appdgessddi.entities.MinistereStructure;
import com.mfptps.appdgessddi.service.dto.MinistereStructureDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MinistereStructureService {

    MinistereStructure create(MinistereStructureDTO ministereSDTO);
    MinistereStructure update(MinistereStructure ministereS);
    Optional<MinistereStructure> get(Long id);
    Page<MinistereStructure> findAll(Pageable pageable);
    void delete(Long code);
}
