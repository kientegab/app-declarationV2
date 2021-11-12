package com.mfptps.appdgessddi.service.impl;

import com.mfptps.appdgessddi.entities.Structure;
import com.mfptps.appdgessddi.repositories.StructureRepository;
import com.mfptps.appdgessddi.service.StructureService;
import com.mfptps.appdgessddi.service.dto.StructureDTO;
import com.mfptps.appdgessddi.service.mapper.StructureMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
@Transactional
public class StructureServiceImpl implements StructureService {

    private final StructureRepository structureRepository;
    private final StructureMapper structureMapper;

    public StructureServiceImpl(StructureRepository structureRepository, StructureMapper structureMapper) {
        this.structureRepository = structureRepository;
        this.structureMapper = structureMapper;
    }

    @Override
    public Structure create(StructureDTO structureDTO) {
        Structure structure = structureMapper.toEntity(structureDTO);
        return structureRepository.save(structure);
    }

    @Override
    public Structure update(Structure structure) {
        return structureRepository.save(structure);
    }

    @Override
    public Optional<Structure> get(long id) {
        return structureRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Structure> findAll(Pageable pageable) {
        return structureRepository.findAll(pageable);

    }

    @Override
    public void delete(Long id) {
        structureRepository.deleteById(id);
    }
}
