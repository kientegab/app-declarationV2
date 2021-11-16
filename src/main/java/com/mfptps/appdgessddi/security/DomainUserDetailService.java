/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.security;

import java.util.*;
import java.util.stream.Collectors;

import com.mfptps.appdgessddi.entities.Agent;
import com.mfptps.appdgessddi.entities.Permission;
import com.mfptps.appdgessddi.repositories.AgentRepository;

import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author bieve
 */
@Component("userDetailsService")
public class DomainUserDetailService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailService.class);

    private final AgentRepository agentRepository;

    public DomainUserDetailService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String matricule) {
        log.debug("Authenticating {}", matricule);

        if (new EmailValidator().isValid(matricule, null)) {
            return agentRepository
                .findOneWithProfilesByEmailIgnoreCase(matricule)
                .map(user -> createSpringSecurityUser(matricule, user))
                .orElseThrow(() -> new UsernameNotFoundException("Agent with email " + matricule + " was not found in the database"));
        }

        String lowercaseMatricule = matricule.toLowerCase();
        return agentRepository.findOneWithProfilesByMatricule(lowercaseMatricule)
            .map(agent -> createSpringSecurityUser(matricule, agent))
            .orElseThrow(() -> new UsernameNotFoundException("Agent " + matricule + " was not found in the database"));

    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseMatricule, Agent agent) {
        if (!agent.isActif()) {
            throw new UserNotActivatedException("Agent " + lowercaseMatricule + " was not activated");
        }
        Set<Permission> permissions = new HashSet<>();
        agent.getProfiles().stream().forEach(r -> permissions.addAll(r.getPermissions()));
        List<GrantedAuthority> grantedProfiles = permissions.stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getName()))
            .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(agent.getMatricule(),
            agent.getPassword(),
            grantedProfiles);
    }
}
