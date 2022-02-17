/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.service;

import com.mfptps.appdgessddi.entities.Programmation;
import com.mfptps.appdgessddi.service.dto.CommentaireDTO;
import com.mfptps.appdgessddi.service.dto.ProgrammationDTO;
import com.mfptps.appdgessddi.service.dto.ProgrammationForEvaluationDTO;
import java.io.OutputStream;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public interface ProgrammationService {

    /**
     * Programmation d'une activite
     *
     * @param programmationDTO
     * @return
     */
    Programmation create(ProgrammationDTO programmationDTO);

    /**
     * Modification superficielle d'une activite programmee
     *
     * @param programmation
     * @return
     */
    Programmation update(Programmation programmation);

    Page<Programmation> get(Long structureId, String libelle, Pageable pageable);

    /**
     * Recherche d'une activite programmee via son id et la structure l'ayant
     * programme
     *
     * @param structureId
     * @param id
     * @return
     */
    Optional<Programmation> get(Long structureId, Long id);

    /**
     * Rechercher et Construire la programmation pour l'evaluation
     *
     * @param programationId
     * @return
     */
    ProgrammationForEvaluationDTO getForEvaluation(long programationId);

    /**
     * Liste des activites programmees d'une structure donnee
     *
     * @param structureId
     * @param pageable
     * @return
     */
    Page<Programmation> findAll(Long structureId, Pageable pageable);

    /**
     * Liste des activites programmees (d'une structure) et validees au CASEM
     *
     * @param structureId
     * @param pageable
     * @return
     */
    Page<Programmation> findAllValided(Long structureId, Pageable pageable);

    /**
     * Validation (par Responsable DGESS ou Responsable Structure) d'une
     * programmation d'une structure donnee
     *
     * @param structureId
     * @param programmationId
     * @return
     */
    Optional<Programmation> validationInitialeOrInterne(Long structureId, Long programmationId);

    /**
     * Validation (par Responsable Structure) de toutes les programmations d'une
     * structure donnee
     *
     * @param structureId
     */
    void allValidationInitiale(Long structureId);

    /**
     * Validation (par Responsable DGESS) de toutes les programmations d'une
     * structure donnee. Ces programmations sont prealablement validees par
     * structure concernee
     *
     * @param structureId
     */
    void allValidationInterne(Long structureId);

    /**
     * Validation (par CASEM represente par responsable DGESS) de toutes les
     * programmations (deja validees au sein de la structure et validees aussi
     * au niveau DGESS) d'une structure donnee
     *
     * @param structureId
     */
    void allValidationCASEM(Long structureId);

    /**
     * Rejet d'une programmation
     *
     * @param commentaireDTO
     */
    void rejetDgessOrCasem(CommentaireDTO commentaireDTO);

    void delete(Long structureId, Long programmationId);

    /**
     * Impression de programme d'activites d'une structure ou d'un ministere en
     * fonction de l'exercice
     *
     * @param ministereId
     * @param structureId
     * @param exerciceId : NOT NULLABLE
     * @param currentStructureId : NOT NULLABLE
     * @param fileFormat
     * @param outputStream
     */
    void printProgrammeActivites(long ministereId, Long structureId, long exerciceId, long currentStructureId, String fileFormat, OutputStream outputStream);

    /**
     * Impression de rapport d'activites d'une structure ou d'un ministere en
     * fonction de l'exercice
     *
     * @param ministereId
     * @param structureId
     * @param exerciceId : NOT NULLABLE
     * @param currentStructureId : NOT NULLABLE
     * @param fileFormat
     * @param outputStream
     */
    void printRapportActivites(long ministereId, Long structureId, long exerciceId, long currentStructureId, String fileFormat, OutputStream outputStream);

    /**
     * Taux d'execution annuel ou pour periode d'une structure donnee
     *
     * @param structureId: id field of Structure. NOT NULLABLE
     * @param exerciceId : id field of Exercice. NOT NULLABLE
     * @param periodeId : id field of Period. if null, yearly rate(taux)
     * @return
     */
    double tauxExecution(long structureId, long exerciceId, Long periodeId);

    /**
     * Taux d'execution annuel ou pour periode d'un ministere donne
     *
     * @param ministereId : id field of Ministere. NOT NULLABLE
     * @param exerciceId
     * @param periodeId
     * @return
     */
    double tauxExecutionGlobal(long ministereId, long exerciceId, Long periodeId);
}
