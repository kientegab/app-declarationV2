package com.mfptps.appdgessddi.service.impl;

import com.mfptps.appdgessddi.entities.Ministere;
import com.mfptps.appdgessddi.entities.Periode;
import com.mfptps.appdgessddi.entities.Structure;
import com.mfptps.appdgessddi.repositories.MinistereRepository;
import com.mfptps.appdgessddi.repositories.PeriodeRepository;
import com.mfptps.appdgessddi.repositories.StructureRepository;
import com.mfptps.appdgessddi.service.MinistereService;
import com.mfptps.appdgessddi.service.dto.MinistereDTO;
import com.mfptps.appdgessddi.service.dto.statisticresponses.MinistereBundleData;
import com.mfptps.appdgessddi.service.mapper.MinistereMapper;
import com.mfptps.appdgessddi.utils.AppUtil;
import com.mfptps.appdgessddi.web.exceptions.CustomException;
import java.util.Date;
import java.util.List;
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
    private final StructureRepository structureRepository;
    
    private final PeriodeRepository periodeRepository;

    public MinistereServiceImpl(MinistereRepository ministereRepository, MinistereMapper ministereMapper,
            StructureRepository structureRepository, PeriodeRepository periodeRepository) {
        this.ministereRepository = ministereRepository;
        this.ministereMapper = ministereMapper;
        this.structureRepository = structureRepository;
        this.periodeRepository = periodeRepository;
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

    @Override
    public Optional<MinistereBundleData> getBundledData(Long structureId) {
       
        MinistereBundleData data = new MinistereBundleData();
        
        // Recherche de la structure
        Structure structure = structureRepository.getById(structureId);
        
        // Recherche du ministère et de la Structure
        Ministere ministere = ministereRepository.findMinistereStructureCode(structureId).orElse(null);
        
        if(ministere == null){
            throw new CustomException("Aucune donnée trouvée");
        }
        
        data.setMinisterId(ministere.getId());
        data.setMinistereCode(ministere.getSigle());
        data.setMinistereLibelle(ministere.getLibelle());
        
        data.setStructureId(structure.getId());
        data.setStructureCode(structure.getSigle());
        data.setStructureLibelle(structure.getLibelle());
        
        // Recherche de la période
        List<Periode> periodes =  periodeRepository.findByPeriodiciteActif();
        Periode periode = AppUtil.checkExactPeriode(periodes, new Date());
        
        data.setPeriode(periode.getLibelle());
        data.setPeriodicite(periode.getPeriodicite().getLibelle());
        
        
        
        return Optional.of(data);
    }

}
