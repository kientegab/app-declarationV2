package com.mefp.appdeclaration.repositories;

import java.util.List;
import java.util.Optional;

import com.mefp.appdeclaration.service.dto.AgentStructureDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mefp.appdeclaration.entities.AgentStructure;

public interface AgentStructureRepository extends JpaRepository<AgentStructure, Long> {

    Optional<AgentStructure> findByAgentIdAndActifTrue(Long id);

    /**
     * return the actif structure of the user. 08122021
     *
     * @param matricule : matricule or login of user
     * @param email :
     * @return
     */
    @Query("SELECT ast FROM AgentStructure ast, Agent a "
            + "WHERE ast.agent.id = a.id AND ast.actif = true "
            + "AND a.matricule = :matricule OR a.email = :email")
    AgentStructure findOneByAgentMatriculeOrAgentEmail(String matricule, String email);


    @Query("SELECT ast FROM Agent a, AgentStructure ast "
            + "WHERE a.actif = true AND a.deleted = false "
            + "AND  a.id = ast.agent.id")
    List<AgentStructure> findAgentStructure();
}
