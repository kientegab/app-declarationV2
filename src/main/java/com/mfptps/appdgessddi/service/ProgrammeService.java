/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.service;

import com.mfptps.appdgessddi.entities.Programme;
import com.mfptps.appdgessddi.service.dto.ProgrammeDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public interface ProgrammeService {

    Programme create(ProgrammeDTO programmeDTO);

    Programme update(Programme programme);

    Optional<Programme> get(String code);

    Page<Programme> findAll(Pageable pageable);

    void delete(Long id);
}
