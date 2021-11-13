package com.mfptps.appdgessddi.service.impl;

import com.mfptps.appdgessddi.entities.MinistereStructure;
import com.mfptps.appdgessddi.repositories.MinisterestructureRepository;
import com.mfptps.appdgessddi.service.MinistereStructureService;
import com.mfptps.appdgessddi.service.dto.MinistereStructureDTO;
import com.mfptps.appdgessddi.service.mapper.MinistereStructureMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MinistereStructureImpl implements MinistereStructureService {

    private final MinisterestructureRepository ministereSRepository;
    private final MinistereStructureMapper ministereSMapper;

    public MinistereStructureImpl(MinisterestructureRepository ministereSRepository, MinistereStructureMapper ministereSMapper) {
        this.ministereSRepository = ministereSRepository;
        this.ministereSMapper = ministereSMapper;
    }


    @Override
    public MinistereStructure create(MinistereStructureDTO ministereSDTO) {
        MinistereStructure ministere_S = ministereSMapper.toEntity(ministereSDTO);
        return ministereSRepository.save(ministere_S);

    }

    @Override
    public MinistereStructure update(MinistereStructure ministereS) {

        return ministereSRepository.save(ministereS);

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MinistereStructure> get(Long id) {

        return ministereSRepository.findById(id);

    }

    @Override
    public Page<MinistereStructure> findAll(Pageable pageable) {

        return ministereSRepository.findAll(pageable);

    }

    @Override
    public void delete(Long code) {
        ministereSRepository.deleteById(code);

    }
}
