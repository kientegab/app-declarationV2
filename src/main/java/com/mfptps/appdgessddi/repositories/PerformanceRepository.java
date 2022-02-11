package com.mfptps.appdgessddi.repositories;

import com.mfptps.appdgessddi.entities.Performance; 
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Long> {
    
    @Query("SELECT p FROM Performance p WHERE p.deleted = false AND p.structureId=?1  AND p.exerciceId =?2 ")
    Optional<Performance> findCurrentStructurePerformance(long structureId, long exerciceId);
}
