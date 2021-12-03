package com.mfptps.appdgessddi.repositories;

import java.util.Optional;

import com.mfptps.appdgessddi.entities.AgentStructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentStructureRepository extends JpaRepository<AgentStructure, Long> {

    Optional<AgentStructure> findByAgentIdAndActifTrue(Long id);
    
}
