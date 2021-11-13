/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.service.impl;

import com.mfptps.appdgessddi.entities.Programmation;
import com.mfptps.appdgessddi.entities.Tache;
import com.mfptps.appdgessddi.repositories.ProgrammationRepository;
import com.mfptps.appdgessddi.repositories.TacheRepository;
import com.mfptps.appdgessddi.service.ProgrammationService;
import com.mfptps.appdgessddi.service.dto.ProgrammationDTO;
import com.mfptps.appdgessddi.service.mapper.ProgrammationMapper;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Slf4j
@Service
public class ProgrammationServiceImpl implements ProgrammationService {

    private final ProgrammationRepository programmationRepository;
    private final TacheRepository tacheRepository;
    private final ProgrammationMapper programmationMapper;

    public ProgrammationServiceImpl(ProgrammationRepository programmationRepository,
            ProgrammationMapper programmationMapper,
            TacheRepository tacheRepository) {
        this.programmationRepository = programmationRepository;
        this.tacheRepository = tacheRepository;
        this.programmationMapper = programmationMapper;
    }

    @Override
    public Programmation create(ProgrammationDTO programmationDTO) {
        Programmation response = null;
        Programmation programmationMapped = programmationMapper.toEntity(programmationDTO);

        if (programmationDTO.isSingleton()) {//Activite with one Tache
            Tache tache = new Tache();
            tache.setValeur(programmationDTO.getCible());
            tache.setPonderation(100);
            tache.setLibelle(programmationDTO.getActiviteId().toString());//REVIEW
            tache.setDescription(programmationDTO.getActiviteId().toString());//REVIEW

            response = programmationRepository.save(programmationMapped);
            tache.setProgrammation(response);
            tacheRepository.save(tache);
        } else {
            response = programmationRepository.save(programmationMapped);
//            for (Tache tache : programmationDTO.getTaches()) {
//                log.info("Tache info {}", tache);
//                tache.setProgrammation(response);
//                tacheRepository.save(tache);
//            }
        }
        return response;
    }

    @Override
    public Programmation update(Programmation programmation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<Programmation> get(String cible) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Page<Programmation> findAll(Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
