/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.service.impl;

import com.mfptps.appdgessddi.entities.Periode;
import com.mfptps.appdgessddi.repositories.PeriodeRepository;
import com.mfptps.appdgessddi.service.CustomException;
import com.mfptps.appdgessddi.service.PeriodeService;
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
public class PeriodeServiceImpl implements PeriodeService {

    private final PeriodeRepository periodeRepository;

    public PeriodeServiceImpl(PeriodeRepository periodeRepository) {
        this.periodeRepository = periodeRepository;
    }

    @Override
    public Periode create(Periode periode) {
        if (!periode.getPeriodicite().isActif()
                && periode.getPeriodicite().isDeleted()) {
            throw new CustomException("Opération interdite ! Veuillez selectionner une périodicité active.");
        }
        return periodeRepository.save(periode);
    }

    @Override
    public Page<Periode> findByPeriodiciteActif(Pageable pageable) {
        return periodeRepository.findByPeriodiciteActif(pageable);
    }

}
