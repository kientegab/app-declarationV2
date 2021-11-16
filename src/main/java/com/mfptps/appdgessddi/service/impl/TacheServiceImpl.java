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
import com.mfptps.appdgessddi.service.TacheService;
import java.util.List;
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
public class TacheServiceImpl implements TacheService {

    private final TacheRepository tacheRepository;
    private final ProgrammationRepository programmationRepository;

    public TacheServiceImpl(TacheRepository tacheRepository,
            ProgrammationRepository programmationRepository) {
        this.tacheRepository = tacheRepository;
        this.programmationRepository = programmationRepository;
    }

    @Override
    public Tache update(Tache tache) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * acts as deletion and update. Do at frontEnd : Modify fields and delete
     * tasks (checkBox for example)
     *
     * @param taches : list of taches (deleted or no, updated or no)
     * @return
     */
    @Override
    public List<Tache> update(List<Tache> taches) {
        Programmation programmation = programmationRepository.findById(taches.get(0).getProgrammation().getId()).orElseThrow(() -> new CustomException("Programmation inexistante."));
        log.debug("Sum of Ponderations = {} %", programmation.checkPonderation());
        double ponderation = 0d;
        for (Tache t : taches) {
            if (!t.isDeleted()) {
                ponderation = ponderation + t.getPonderation();
            }
        }

        if (ponderation != 100.0) {
            throw new CustomException("L'ensemble des ponderations de vos taches n'atteint pas 100%.");
        }

        return tacheRepository.saveAll(taches);
    }

    @Override
    public Page<Tache> get(String libelle, Long programmationId, Pageable pageable) {
        return tacheRepository.findByAndLibelleAndProgrammationId(libelle, programmationId, pageable);
    }

    @Override
    public Page<Tache> get(Long programmationId, Pageable pageable) {
        return tacheRepository.findByDeletedFalseAndProgrammationId(programmationId, pageable);
    }

}
