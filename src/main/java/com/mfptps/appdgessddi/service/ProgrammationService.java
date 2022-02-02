/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.service;

import com.mfptps.appdgessddi.entities.Programmation;
import com.mfptps.appdgessddi.service.dto.CommentaireDTO;
import com.mfptps.appdgessddi.service.dto.ProgrammationDTO;
import java.io.OutputStream;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public interface ProgrammationService {

    Programmation create(ProgrammationDTO programmationDTO);

    Programmation update(Programmation programmation);

    Page<Programmation> get(Long structureId, String libelle, Pageable pageable);

    Optional<Programmation> get(Long structureId, Long id);

    Page<Programmation> findAll(Long structureId, Pageable pageable);

    Page<Programmation> findAllValided(Long structureId, Pageable pageable);

    Optional<Programmation> validationInitialeOrInterne(Long structureId, Long programmationId);

    void rejetDgessOrCasem(CommentaireDTO commentaireDTO);

    void delete(Long structureId, Long programmationId);

    void printProgrammeActivites(long ministereId, Long structureId, long exerciceId, long currentStructureId, OutputStream outputStream);
}
