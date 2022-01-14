package com.mfptps.appdgessddi.repositories;

import com.mfptps.appdgessddi.entities.Objectif;
import com.mfptps.appdgessddi.enums.BaseStatus;
import com.mfptps.appdgessddi.enums.TypeObjectif;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ObjectifRepository extends JpaRepository<Objectif, Long> {

    Optional<Objectif> findByLibelle(String libelle);

    Optional<Objectif> findByIdAndType(long objectifId, TypeObjectif type);

    Page<Objectif> findByType(TypeObjectif type, Pageable pageable);

    @Query("SELECT COUNT(*) FROM Objectif o, Programme p "
            + "WHERE o.programme.id = p.id "
            + "AND p.id = :programmeId "
            + "AND p.statut = :programmeStatut "
            + "AND o.type = :typeObjectif")
    long countObjectifStrategique(long programmeId, BaseStatus programmeStatut, TypeObjectif typeObjectif);

    @Query("SELECT COUNT(*) FROM Objectif o, Action a "
            + "WHERE o.action.id = a.id "
            + "AND a.id = :actionId "
            + "AND o.type = :typeObjectif "
            + "AND o.parent = null")//can be ameliorate
    long countObjectifOperationnel(long actionId, TypeObjectif typeObjectif);

    @Query("SELECT COUNT(*) FROM Objectif o "
            + "WHERE o.parent.id = :parentId "
            + "AND o.parent.type = :typeObjectif "
            + "AND o.type = :typeObjectif")
    long countObjectifSubOperationnel(long parentId, TypeObjectif typeObjectif);
}
