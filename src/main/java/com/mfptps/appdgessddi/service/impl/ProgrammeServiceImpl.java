/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.service.impl;

import com.mfptps.appdgessddi.entities.Programme;
import com.mfptps.appdgessddi.repositories.ProgrammeRepository;
import com.mfptps.appdgessddi.service.ProgrammeService;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Service
@Transactional
public class ProgrammeServiceImpl implements ProgrammeService {

    private final ProgrammeRepository repository;

    public ProgrammeServiceImpl(ProgrammeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Programme create(Programme programme) {
        return repository.save(programme);
    }

    @Override
    public Programme update(Programme programme) {
        return repository.save(programme);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Programme> get(String code) {
        return repository.findByCode(code);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Programme> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
