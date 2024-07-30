package com.mefp.appdeclaration.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mefp.appdeclaration.entities.Ministere;
import com.mefp.appdeclaration.service.dto.MinistereDTO;
import com.mefp.appdeclaration.service.dto.statisticresponses.MinistereBundleData;

public interface MinistereService {

    Ministere create(MinistereDTO ministere);

    Ministere update(Ministere ministere);

    Optional<Ministere> get(String code);

    Optional<Ministere> get(Long id);
    
    /**
     * 
    // * @param structureId
    //* @return
     */
    //Optional<MinistereBundleData> getBundledData(Long structureId);

    Page<Ministere> findAll(Pageable pageable);

    void delete(Long code);

}
