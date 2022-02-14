/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mfptps.appdgessddi.repositories;
 
import com.mfptps.appdgessddi.entities.Impact;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author aboubacary
 */
public interface ImpactRepository extends JpaRepository<Impact, Long> {
    
    @Query("SELECT i FROM Impact i WHERE i.ministere.id=?1 AND i.statistique=?2 AND i.deleted=false")
    public List<Impact> findMinisterImpact(long ministereId, boolean statistique);
    
}
