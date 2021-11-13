package com.mfptps.appdgessddi.service;


import com.mfptps.appdgessddi.entities.Exercice;
import com.mfptps.appdgessddi.service.dto.ExerciceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface ExerciceService {

    Exercice create(ExerciceDTO exerciceDTO);

    Exercice update(Exercice exercice);

    Optional<Exercice> get(Long id);

    Page<Exercice> findAll(Pageable pageable);

    void delete(Long id);
}
