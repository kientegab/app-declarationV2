/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mfptps.appdgessddi.repositories;

import com.mfptps.appdgessddi.entities.Contribuer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author aboubacary
 */
public interface ContribuerRepository extends JpaRepository<Contribuer, Long> {

    @Query("SELECT c FROM Contribuer c, ParametrerImpact p, Exercice e WHERE c.structure.id=?1 AND c.parametrerImpact.id=p.id AND p.exercice.id=?2 AND e.id=p.exercice.id AND c.nonapplicable=false")
    public List<Contribuer> findStructureContribution(long structureId, long exerciceId);

    @Query("SELECT c FROM Contribuer c, ParametrerImpact p, Exercice e WHERE c.structure.id=?1 AND c.parametrerImpact.id=p.id AND p.exercice.id=?2 AND e.id=p.exercice.id")
    public List<Contribuer> findAllStructureContribution(long structureId, long exerciceId);

}
