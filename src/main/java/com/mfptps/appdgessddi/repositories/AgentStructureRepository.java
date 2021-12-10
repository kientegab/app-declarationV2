package com.mfptps.appdgessddi.repositories;

import com.mfptps.appdgessddi.entities.AgentStructure;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AgentStructureRepository extends JpaRepository<AgentStructure, Long> {

    Optional<AgentStructure> findByAgentIdAndActifTrue(Long id);

    /**
     * return the actif structure of the user. 08122021
     *
     * @param matricule : matricule or login of user
     * @return
     */
    @Query("SELECT a FROM AgentStructure a "
            + "WHERE a.agent.matricule = :matricule "
            + "AND a.actif = true")
    AgentStructure findByAgentMatriculeAndActifTrue(@Param("matricule") String matricule);

}
