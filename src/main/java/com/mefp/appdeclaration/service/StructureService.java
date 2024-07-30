package com.mefp.appdeclaration.service;

import java.util.List;
import java.util.Optional;

import com.mefp.appdeclaration.entities.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mefp.appdeclaration.entities.Structure;
import com.mefp.appdeclaration.service.dto.ChangeMinistereDTO;
import com.mefp.appdeclaration.service.dto.StructureDTO;

public interface StructureService {

    Structure create(StructureDTO structure);

    Structure update(Structure structure);

    Optional<Structure> get(long id);

    Page<Structure> findAll(Pageable pageable);

    List<Structure> findAllStructure();


    void delete(Long code);

    Structure changementMinistere(ChangeMinistereDTO changeMinistereDTO);
}
