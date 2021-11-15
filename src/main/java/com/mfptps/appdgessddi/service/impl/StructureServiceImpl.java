package com.mfptps.appdgessddi.service.impl;

import com.mfptps.appdgessddi.entities.Ministere;
import com.mfptps.appdgessddi.entities.MinistereStructure;
import com.mfptps.appdgessddi.entities.Structure;
import com.mfptps.appdgessddi.repositories.MinistereRepository;
import com.mfptps.appdgessddi.repositories.StructureRepository;
import com.mfptps.appdgessddi.service.CustomException;
import com.mfptps.appdgessddi.service.StructureService;
import com.mfptps.appdgessddi.service.dto.StructureDTO;
import com.mfptps.appdgessddi.service.mapper.StructureMapper;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mfptps.appdgessddi.repositories.MinistereStructureRepository;

@Service
@Transactional
public class StructureServiceImpl implements StructureService {

    private final StructureRepository structureRepository;
    private final MinistereStructureRepository ministereStructureRepository;
    private final MinistereRepository ministereRepository;
    private final StructureMapper structureMapper;

    public StructureServiceImpl(StructureRepository structureRepository,
            MinistereStructureRepository ministereStructureRepository,
            MinistereRepository ministereRepository,
            StructureMapper structureMapper) {
        this.structureRepository = structureRepository;
        this.ministereStructureRepository = ministereStructureRepository;
        this.ministereRepository = ministereRepository;
        this.structureMapper = structureMapper;
    }

    @Override
    public Structure create(StructureDTO structureDTO) {
        Structure structure = structureMapper.toEntity(structureDTO);
        Structure structureSaved = structureRepository.save(structure);
        MinistereStructure ministereStructure = new MinistereStructure();
        Ministere ministere = new Ministere();

        ministere.setId(structureDTO.getMinistereId());
        ministereStructure.setMinistere(ministere);
        ministereStructure.setStructure(structureSaved);
        ministereStructure.setDateDebut(new Date());
        ministereStructureRepository.save(ministereStructure);
        return structureSaved;
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

    @Override
    public Structure changementMinistere(Long structureId, Long ministereId) {
        //Check if exists Objects Ministere and Structure
        Structure existStructure = structureRepository.findById(structureId)
                .orElseThrow(() -> new CustomException("Structure inexistante."));
        Ministere existMinistere = ministereRepository.findById(ministereId)
                .orElseThrow(() -> new CustomException("Ministere inexistant."));

        //find MinistereStructure element from bd
        MinistereStructure existMinistereStructure = ministereStructureRepository
                .findByStructureIdAndStatutIsTrue(existStructure.getId()).get();

        //New line to be add as MinistereStructure object
        MinistereStructure ministereStructure = new MinistereStructure();
        Date date = new Date();
        ministereStructure.setMinistere(existMinistere);
        ministereStructure.setStructure(existStructure);
        ministereStructure.setDateDebut(date);

        //update (set to disable) old MinistereStructure object
        existMinistereStructure.setStatut(false);
        existMinistereStructure.setDateFin(date);

        ministereStructureRepository.save(existMinistereStructure);
        MinistereStructure response = ministereStructureRepository.save(ministereStructure);

        return structureRepository.findById(response.getStructure().getId()).get();
    }
}
