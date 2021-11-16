package com.mfptps.appdgessddi.repositories;


import java.util.Optional;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mfptps.appdgessddi.entities.MinistereStructure;

public interface MinistereStructureRepository extends JpaRepository<MinistereStructure, Long> {

    Optional<MinistereStructure> findByStructureIdAndStatutIsTrue(@NotNull long structureId);

    Page<MinistereStructure> findAllByStatutIsTrue(Pageable pageable);
}
