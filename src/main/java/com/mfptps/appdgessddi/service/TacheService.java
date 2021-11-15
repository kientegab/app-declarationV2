/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.service;

import com.mfptps.appdgessddi.entities.Tache;
import com.mfptps.appdgessddi.service.dto.TacheDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public interface TacheService {

    Tache create(TacheDTO tache);

    Tache update(Tache tache);

    Page<Tache> get(String libelle);

    Page<Tache> findAll(Pageable pageable);

    void delete(Long id);
}
