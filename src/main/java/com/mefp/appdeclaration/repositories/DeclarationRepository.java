package com.mefp.appdeclaration.repositories;

import com.mefp.appdeclaration.entities.Declaration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface DeclarationRepository extends JpaRepository<Declaration, Long> {
    @Query("SELECT a FROM Declaration a WHERE a.voyageur.id = :voyageurId")
    List<Declaration> findByVoyageurId(Long voyageurId);
    @Query("SELECT a FROM Declaration a WHERE a.dateDeclaration >= :dateDebut and a.dateDeclaration <= :dateFin")
    List<Declaration> findByPeriode(Date dateDebut, Date dateFin);

    @Query("select count(*) as nbDeclaration , to_char(dateDeclaration, 'Month')  as mois FROM Declaration  group by mois")
    String countDeclaration();

   // @Query("SELECT dec FROM Declaration dec, DeviseMontantdto dev "
     //       + "WHERE dec.id=dev.declaration.id")
  // @Query("SELECT dec FROM Declaration dec, DeviseMontantdto dev WHERE dec.id=dev.id ")
    //List<Declaration> findAllD();
   //@Query("SELECT c FROM DeviseMontantdto c JOIN FETCH c.declaration")
    //List<Declaration> findAllD();
}
