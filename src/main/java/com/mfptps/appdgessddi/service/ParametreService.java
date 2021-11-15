package com.mfptps.appdgessddi.service;


import com.mfptps.appdgessddi.entities.Parametre;
import com.mfptps.appdgessddi.service.dto.ParametreDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface ParametreService {

    Parametre create(ParametreDTO parametreDTO);

    Parametre update(Parametre parametre);

    Page<Parametre> findAll(Pageable pageable);

    void delete(Long id);
}
