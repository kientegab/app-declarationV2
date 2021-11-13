package com.mfptps.appdgessddi.service;

import com.mfptps.appdgessddi.entities.Ministere;
import com.mfptps.appdgessddi.service.dto.MinistereDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MinistereService {

    Ministere create(MinistereDTO ministere);

    Ministere update(Ministere ministere);

    Optional<Ministere> get(String code);

    Optional<Ministere> get(Long id);

    Page<Ministere> findAll(Pageable pageable);

    void delete(Long code);

}
