/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.repositories;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import com.mfptps.appdgessddi.entities.Agent;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author bieve
 */
public interface AgentRepository extends JpaRepository<Agent, Long>{
    
    String USERS_BY_LOGIN_CACHE = "usersByLogin";
    String USERS_BY_EMAIL_CACHE = "usersByEmail";

    Optional<Agent> findOneByActivationKey(String activationKey);

    List<Agent> findAllByActifIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(Instant dateTime);

    Optional<Agent> findOneByResetKey(String resetKey);

    Optional<Agent> findOneByLogin(String login);

    @EntityGraph(attributePaths = "profiles")
    @Cacheable(cacheNames = USERS_BY_LOGIN_CACHE)
    Optional<Agent> findOneWithAuthoritiesByLogin(String login);

    @EntityGraph(attributePaths = "profiles")
    @Cacheable(cacheNames = USERS_BY_EMAIL_CACHE)
    Optional<Agent> findOneWithAuthoritiesByEmailIgnoreCase(String email);

    Page<Agent> findAllByIdNotNullAndActifIsTrue(Pageable pageable);
}
