package com.mefp.appdeclaration.repositories;

import com.mefp.appdeclaration.entities.FicheSaisie;
import com.mefp.appdeclaration.service.dto.AgentStructureDTO;
import com.mefp.appdeclaration.service.dto.FicheSaisieDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FicheSaisieRepository extends JpaRepository<FicheSaisie, Long> {
//    @Query(value = "SELECT  com.mefp.appdeclaration.service.dto.FicheSaisieDTO( a.numSaisie, a.dateSaisie,  a.anneeSaisie,  a.structureSaisie, a.lieuSaisie,  a.itineraire,  a.commentaire,  a.nature,  a.procede, a.intervenant)  "
//            + "FROM FcheSaisie a, NatureSaisie ags, ProcedeSaisie s, IntervenantSaisie v "
//            + "WHERE a.id = ags.ficheSaisie.id "
//            + "AND a.id= s.ficheSaisie.id"
//            + "AND a.id= v.ficheSaisie.id"
//            + "AND a.id = :id ")
    FicheSaisie findFicheSaisieById(long id);
}
