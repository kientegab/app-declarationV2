package com.mfptps.appdgessddi.repositories;

import com.mfptps.appdgessddi.entities.MinistereStructure;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MinistereStructureRepository extends JpaRepository<MinistereStructure, Long> {

    Optional<MinistereStructure> findByStructureIdAndStatutIsTrue(@NotNull long structureId);

    Page<MinistereStructure> findAllByStatutIsTrue(Pageable pageable);

    @Query("SELECT COUNT(*) FROM MinistereStructure ms "
            + "WHERE ms.ministere.id = :id "
            + "AND ms.structure.niveau = 1 "
            + "AND ms.statut IS TRUE")
    long countStructureByMinistere(long id);

}
