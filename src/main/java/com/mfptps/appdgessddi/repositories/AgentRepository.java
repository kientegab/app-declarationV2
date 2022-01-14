/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.repositories;

import com.mfptps.appdgessddi.entities.Agent;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author bieve
 */
public interface AgentRepository extends JpaRepository<Agent, Long> {

    String USERS_BY_LOGIN_CACHE = "usersByMatricule";
    String USERS_BY_EMAIL_CACHE = "usersByEmail";

    Optional<Agent> findOneByActivationKey(String activationKey);

    List<Agent> findAllByActifIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(Instant dateTime);

    Optional<Agent> findOneByResetKey(String resetKey);

    Optional<Agent> findOneByMatricule(String matricule);

    Optional<Agent> findOneByEmailIgnoreCase(String email);

    @EntityGraph(attributePaths = "profiles")
    @Cacheable(cacheNames = USERS_BY_LOGIN_CACHE)
    Optional<Agent> findOneWithProfilesByMatricule(String matricule);

    @EntityGraph(attributePaths = "profiles")
    @Cacheable(cacheNames = USERS_BY_EMAIL_CACHE)
    Optional<Agent> findOneWithProfilesByEmailIgnoreCase(String email);

    Page<Agent> findAllByIdNotNullAndActifIsTrue(Pageable pageable);

    Optional<Agent> findOneByMatriculeOrEmail(String matricule, String email);

    /**
     * return user created by system. 08122021
     *
     * @param matricule
     * @return
     */
    @Query("SELECT a FROM Agent a "
            + "WHERE a.actif = true "
            + "AND a.matricule = :matricule "
            + "AND a.createdBy = 'system' ")
    Agent findAgentSystemByMatricule(@Param("matricule") String matricule);
}
