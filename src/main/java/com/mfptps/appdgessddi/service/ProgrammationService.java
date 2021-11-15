/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.service;

import com.mfptps.appdgessddi.entities.Programmation;
import com.mfptps.appdgessddi.service.dto.ProgrammationDTO;
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

    Page<Programmation> get(String libelle, Pageable pageable);

    Optional<Programmation> get(Long id);

    Page<Programmation> findAll(Pageable pageable);

    void delete(Long id);
}
