package com.mefp.appdeclaration.repositories;

import com.mefp.appdeclaration.entities.Nature;
import com.mefp.appdeclaration.entities.NatureSaisie;
import com.mefp.appdeclaration.entities.dto.Naturedto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NatureSaisieRepository extends JpaRepository<NatureSaisie, Long> {
    List<NatureSaisie> findNatureSaisieByFicheSaisieId(Long id);
    @Query("SELECT new com.mefp.appdeclaration.entities.dto.Naturedto(SUM(a.poids) as totalPoids, SUM(a.valeur) as totalValeur) FROM NatureSaisie a WHERE a.ficheSaisie.id = :ficheSaisieId")
    Naturedto findByFicheSaisieId(Long ficheSaisieId);

}
