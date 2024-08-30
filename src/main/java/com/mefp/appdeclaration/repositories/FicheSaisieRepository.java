package com.mefp.appdeclaration.repositories;

import com.mefp.appdeclaration.entities.FicheSaisie;
import com.mefp.appdeclaration.entities.dto.EtatSaisie;
import com.mefp.appdeclaration.entities.dto.EtatSaisieDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface FicheSaisieRepository extends JpaRepository<FicheSaisie, Long> {
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

    @Query(value = "SELECT  new com.mefp.appdeclaration.entities.dto.EtatSaisieDetail(ags.nature.libelle, ags.poids, a.dateSaisie," +
            " a.lieuSaisie.libelle,  a.itineraire, s.exportation, s.vente, s.usage, p.nombre, p.nationalite, p.sexe, p.tranche, p.suiteDonne) " +
            "FROM FicheSaisie a, NatureSaisie ags, Destination s, Interpele p " +
            "WHERE a.id = ags.ficheSaisie.id " +
            "AND a.id= s.fiche.id " +
            "AND a.id= p.fiche.id " +
            "AND a.structureSaisie.id =:Id ")
    List <EtatSaisieDetail> findPersonaliseDetail(Long Id);


    @Query(value = "SELECT  a  FROM FicheSaisie a  WHERE a.structureSaisie.id =:Id ")
    List<FicheSaisie> findBystructureSaisieId(Long Id);
    List<FicheSaisie> findBystructureSaisie(String structure);
}
