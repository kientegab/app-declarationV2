package com.mefp.appdeclaration.repositories;

import com.mefp.appdeclaration.entities.FicheSaisie;
import com.mefp.appdeclaration.entities.dto.EtatSaisie;
import com.mefp.appdeclaration.service.dto.AgentStructureDTO;
import com.mefp.appdeclaration.service.dto.FicheSaisieDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FicheSaisieRepository extends JpaRepository<FicheSaisie, Long> {
//    @Query(value = "SELECT  com.mefp.appdeclaration.service.dto.FicheSaisieDTO( a.numSaisie, a.dateSaisie,  a.anneeSaisie,  a.structureSaisie, a.lieuSaisie,  a.itineraire,  a.commentaire,  ags.nature.libelle)  "
//            + "FROM FcheSaisie a, NatureSaisie ags"
//            + "WHERE a.id = ags.ficheSaisie.id "
//            + "AND a.id = :id ")
    FicheSaisie findFicheSaisieById(long id);

        @Query(value = "SELECT  new com.mefp.appdeclaration.entities.dto.EtatSaisie(a.numSaisie, a.dateSaisie," +
                " a.lieuSaisie.libelle,  a.itineraire, ags.nature.libelle, s.procede.libelle," +
                " SUM(ags.poids) as totalPoids, SUM(ags.valeur) as totalValeur) " +
                "FROM FicheSaisie a, NatureSaisie ags, ProcedeSaisie s " +
                "WHERE a.id = ags.ficheSaisie.id " +
                "AND a.id= s.ficheSaisie.id " +
                "AND a.structureSaisie.id =:Id "+
                "group by ags.ficheSaisie.id, a.numSaisie, a.dateSaisie,a.lieuSaisie.libelle,  a.itineraire, ags.nature.libelle, s.procede.libelle")
        List <EtatSaisie> findPersonalise(Long Id);



    List<FicheSaisie> findBystructureSaisie(String structure);
}
