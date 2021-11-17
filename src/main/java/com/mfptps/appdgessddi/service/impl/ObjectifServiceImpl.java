package com.mfptps.appdgessddi.service.impl;

import com.mfptps.appdgessddi.entities.Objectif;
import com.mfptps.appdgessddi.enums.TypeObjectif;
import com.mfptps.appdgessddi.repositories.ObjectifRepository;
import com.mfptps.appdgessddi.service.ObjectifService;
import com.mfptps.appdgessddi.service.dto.ObjectifDTO;
import com.mfptps.appdgessddi.service.mapper.ObjectiMapper;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class ObjectifServiceImpl implements ObjectifService {

    private final ObjectifRepository objectifRepository;
    private final ObjectiMapper objectiMapper;

    public ObjectifServiceImpl(ObjectifRepository objectifRepository, ObjectiMapper objectiMapper) {
        this.objectifRepository = objectifRepository;
        this.objectiMapper = objectiMapper;
    }

    @Override
    public Objectif create(ObjectifDTO objectifDTO) {

        Objectif objectifmapped = objectiMapper.toEntity(objectifDTO);
        return objectifRepository.save(objectifmapped);
    }

    @Override
    public Objectif update(Objectif objectif) {
        return objectifRepository.save(objectif);

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Objectif> get(Long id) {

        return objectifRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Objectif> get(String libelle) {
        return objectifRepository.findByLibelle(libelle);
    }

    @Override
    public Page<Objectif> findAll(Pageable pageable) {
        return objectifRepository.findAll(pageable);
    }

    @Override
    public void delete(Long id) {
        objectifRepository.deleteById(id);
    }

    @Override
    public Page<Objectif> findByType(String type, Pageable pageable) {
        TypeObjectif ype = TypeObjectif.valueOf(type);
        return objectifRepository.findByType(ype, pageable);
    }
}
