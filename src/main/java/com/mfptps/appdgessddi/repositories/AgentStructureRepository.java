package com.mfptps.appdgessddi.repositories;

import com.mfptps.appdgessddi.entities.AgentStructure;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentStructureRepository extends JpaRepository<AgentStructure, Long> {

    Optional<AgentStructure> findByAgentIdAndActifTrue(Long id);

    /**
     * return the actif structure of the user. 08122021
     *
     * @param matricule : matricule or login of user
     * @param email :
     * @return
     */
    AgentStructure findOneByAgentMatriculeOrAgentEmail(String matricule, String email);

}
