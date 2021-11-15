package com.mfptps.appdgessddi.service.impl;

import com.mfptps.appdgessddi.entities.Ministere;
import com.mfptps.appdgessddi.repositories.MinistereRepository;
import com.mfptps.appdgessddi.service.MinistereService;
import com.mfptps.appdgessddi.service.dto.MinistereDTO;
import com.mfptps.appdgessddi.service.mapper.MinistereMapper;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MinistereServiceImpl implements MinistereService {

    private final MinistereRepository ministereRepository;
    private final MinistereMapper ministereMapper;

    public MinistereServiceImpl(MinistereRepository ministereRepository, MinistereMapper ministereMapper) {
        this.ministereRepository = ministereRepository;
        this.ministereMapper = ministereMapper;
    }

    @Override
    public Ministere create(MinistereDTO ministereDTO) {
        Ministere ministere = ministereMapper.toEntity(ministereDTO);
        return ministereRepository.save(ministere);
    }

    @Override
    public Ministere update(Ministere ministere) {

        return ministereRepository.save(ministere);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Ministere> get(String code) {

        return ministereRepository.findByCode(code);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Ministere> findAll(Pageable pageable) {

        return ministereRepository.findAll(pageable);
    }

    @Override
    public void delete(Long code) {
        ministereRepository.deleteById(code);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Ministere> get(Long id) {
        return ministereRepository.findById(id);
    }

}
