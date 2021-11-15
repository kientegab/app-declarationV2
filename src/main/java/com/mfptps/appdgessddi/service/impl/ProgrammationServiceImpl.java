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
import com.mfptps.appdgessddi.service.CustomException;
import com.mfptps.appdgessddi.service.ProgrammationService;
import com.mfptps.appdgessddi.service.dto.ProgrammationDTO;
import com.mfptps.appdgessddi.service.mapper.ProgrammationMapper;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Slf4j
@Service
@Transactional
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

    /**
     *
     * @param programmationDTO : DTO content Activite, Programmation, Projet,
     * SourceFinancement, and Tache(s) fields
     * @return
     */
    @Override
    public Programmation create(ProgrammationDTO programmationDTO) {
        Programmation programmationMapped = programmationMapper.toEntity(programmationDTO);
        log.debug("Sum of Ponderations = {} %", programmationMapped.checkPonderation());
        if (programmationMapped.checkPonderation() != 100) {
            throw new CustomException("L'ensemble des ponderations de vos taches n'atteint pas 100%.");
        }
        Programmation response = programmationRepository.save(programmationMapped);

        if (programmationDTO.isSingleton()) {//Activite with one Tache
            Tache tache = new Tache();
            tache.setValeur(programmationDTO.getCible());
            tache.setPonderation(100);
            tache.setLibelle(programmationDTO.getActivite().getLibelle());
            tache.setDescription(programmationDTO.getActivite().getDescription());
            tache.setProgrammation(response);
            tacheRepository.save(tache);
        } else {//Activite with many Tache. Then we create those Taches linking ProgrammationId
            for (Tache tache : programmationDTO.getTaches()) {
                log.debug("Tache info {}", tache);
                tache.setProgrammation(response);
                tacheRepository.save(tache);
            }
        }
        return response;
    }

    @Override
    public Programmation update(Programmation programmation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param libelle : libelle of Activite
     * @return
     */
    @Override
    public Page<Programmation> get(String libelle, Pageable pageable) {
        return programmationRepository.findByActiviteLibelleContainingIgnoreCase(libelle, pageable);
    }

    /**
     *
     * @param id : id of Programmation
     * @return
     */
    @Override
    public Optional<Programmation> get(Long id) {
        return programmationRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Programmation> findAll(Pageable pageable) {
        return programmationRepository.findAll(pageable);
    }

    /**
     *
     * @param id : id of Programmation
     */
    @Override
    public void delete(Long id) {
        programmationRepository.deleteById(id);
    }

}
