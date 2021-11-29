package com.mfptps.appdgessddi.service.impl;


import com.mfptps.appdgessddi.entities.Performer;
import com.mfptps.appdgessddi.repositories.PerformerRepository;
import com.mfptps.appdgessddi.service.PerformerService;
import com.mfptps.appdgessddi.service.dto.PerformerDTO;
import com.mfptps.appdgessddi.service.mapper.PerformerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
public class PerformerServiceImpl  implements PerformerService {

    private final PerformerRepository performerRepository ;
    private final PerformerMapper performerMapper ;

    public PerformerServiceImpl(PerformerRepository performerRepository, PerformerMapper performerMapper) {
        this.performerRepository = performerRepository;
        this.performerMapper = performerMapper;
    }

    @Override
    public Performer create(PerformerDTO performerDTO) {
        Performer performer=performerMapper.toEntity(performerDTO);
        return performerRepository.save(performer);
    }

    @Override
    public Performer update(Performer performer) {
        return performerRepository.save(performer);
    }

    @Override
    public Optional<Performer> get(Long id) {
        return performerRepository.findById(id);
    }

    @Override
    public Page<Performer> findAll(Pageable pageable) {
        return performerRepository.findAll(pageable);
    }

    @Override
    public void delete(Long id) {
       performerRepository.deleteById(id);
    }
}
