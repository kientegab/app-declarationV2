/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.service.impl;

import com.mfptps.appdgessddi.entities.Exercice;
import com.mfptps.appdgessddi.entities.Programmation;
import com.mfptps.appdgessddi.entities.Tache;
import com.mfptps.appdgessddi.enums.ExerciceStatus;
import com.mfptps.appdgessddi.repositories.ExerciceRepository;
import com.mfptps.appdgessddi.repositories.ProgrammationRepository;
import com.mfptps.appdgessddi.repositories.TacheRepository;
import com.mfptps.appdgessddi.service.CustomException;
import com.mfptps.appdgessddi.service.EvaluationService;
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
    private final ExerciceRepository exerciceRepository;
    private final EvaluationService evaluationService;
    private final ProgrammationMapper programmationMapper;

    public ProgrammationServiceImpl(ProgrammationRepository programmationRepository,
            ExerciceRepository exerciceRepository,
            ProgrammationMapper programmationMapper,
            TacheRepository tacheRepository,
            EvaluationService evaluationService) {
        this.programmationRepository = programmationRepository;
        this.tacheRepository = tacheRepository;
        this.exerciceRepository = exerciceRepository;
        this.evaluationService = evaluationService;
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
//        if (!programmationDTO.isSingleton() && programmationMapped.checkValeur() != programmationDTO.getCible()) {
//            throw new CustomException("La somme des valeurs de vos taches n'atteint pas la cible (" + programmationDTO.getCible() + ") de l'activité programmée.");
//        }
        Exercice exerciceEnAttente = exerciceRepository.findByStatut(ExerciceStatus.EN_ATTENTE).orElseThrow(() -> new CustomException("Aucun exercice en attente."));
        programmationMapped.setExercice(exerciceEnAttente);
        Programmation response = programmationRepository.save(programmationMapped);
        evaluationService.addEvaluation(programmationDTO.getPeriodes(), response);

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
    public Page<Programmation> get(Long structureId, String libelle, Pageable pageable) {
        return programmationRepository.findByLibelle(structureId, libelle, pageable);
    }

    /**
     *
     * @param id : id of Programmation
     * @return
     */
    @Override
    public Optional<Programmation> get(Long structureId, Long id) {
        return programmationRepository.findById(structureId, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Programmation> findAll(Long structureId, Pageable pageable) {
        return programmationRepository.findAll(structureId, pageable);
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
