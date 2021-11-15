package com.mfptps.appdgessddi.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import com.mfptps.appdgessddi.entities.Agent;
import com.mfptps.appdgessddi.entities.Profile;
import com.mfptps.appdgessddi.repositories.AgentRepository;
import com.mfptps.appdgessddi.repositories.ProfileRepository;
import com.mfptps.appdgessddi.security.SecurityUtils;
import com.mfptps.appdgessddi.service.dto.AgentDTO;
import com.mfptps.appdgessddi.utils.RandomUtil;

/**
 * Bieve
 * Service class for managing agents.
 */
@Service
@Transactional
public class AgentService {

    private final Logger log = LoggerFactory.getLogger(AgentService.class);

    private final AgentRepository agentRepository;

    private final PasswordEncoder passwordEncoder;

    private final ProfileRepository profileRepository;

    private final CacheManager cacheManager;
    
    

    public AgentService(AgentRepository agentRepository, PasswordEncoder passwordEncoder,
            ProfileRepository profileRepository, CacheManager cacheManager) {
        this.agentRepository = agentRepository;
        this.passwordEncoder = passwordEncoder;
        this.profileRepository = profileRepository;
        this.cacheManager = cacheManager;
    }

    public Optional<Agent> activateRegistration(String key) {
        log.debug("Activating agent for activation key {}", key);
        return agentRepository.findOneByActivationKey(key)
            .map(agent -> {
                // activate given agent for the registration key.
                agent.setActif(true);
                agent.setActivationKey(null);
                this.clearAgentCaches(agent);
                log.debug("Activated agent: {}", agent);
                return agent;
            });
    }

    public Optional<Agent> completePasswordReset(String newPassword, String key) {
        log.debug("Reset agent password for reset key {}", key);
        return agentRepository.findOneByResetKey(key)
            .filter(agent -> agent.getResetDate().isAfter(Instant.now().minusSeconds(86400)))
            .map(agent -> {
                agent.setPassword(passwordEncoder.encode(newPassword));
                agent.setResetKey(null);
                agent.setResetDate(null);
                this.clearAgentCaches(agent);
                return agent;
            });
    }

    public Optional<Agent> requestPasswordReset(String mail) {
        return agentRepository.findOneByLogin(mail)
            .filter(Agent::isActif)
            .map(agent -> {
                agent.setResetKey(RandomUtil.generateResetKey());
                agent.setResetDate(Instant.now());
                this.clearAgentCaches(agent);
                return agent;
            });
    }

    public Agent registerAgent(AgentDTO agentDTO, String password) {
        agentRepository.findOneByLogin(agentDTO.getLogin().toLowerCase()).ifPresent(existingAgent -> {
            boolean removed = removeNonActivatedAgent(existingAgent);
            if (!removed) {
                throw new UsernameAlreadyUsedException();
            }
        });
        
        Agent newAgent = new Agent();
        String encryptedPassword = passwordEncoder.encode(password);
        newAgent.setLogin(agentDTO.getLogin().toLowerCase());
        newAgent.setPassword(encryptedPassword);
        newAgent.setNom(agentDTO.getNom());
        newAgent.setPrenom(agentDTO.getPrenom());
        // new agent is not active
        newAgent.setActif(false);
        // new agent gets registration key
        newAgent.setActivationKey(RandomUtil.generateActivationKey());
        Set<Profile> authorities = new HashSet<>();
        profileRepository.findByName("ROLE_USER").ifPresent(authorities::add);
        newAgent.setProfiles(authorities);
        agentRepository.save(newAgent);
        this.clearAgentCaches(newAgent);
        log.debug("Created Information for Agent: {}", newAgent);
        return newAgent;
    }

    private boolean removeNonActivatedAgent(Agent existingAgent) {
        if (existingAgent.isActif()) {
             return false;
        }
        agentRepository.delete(existingAgent);
        agentRepository.flush();
        this.clearAgentCaches(existingAgent);
        return true;
    }

    public Agent createAgent(AgentDTO agentDTO, String password) {
        Agent agent = new Agent();
        agent.setLogin(agentDTO.getLogin().toLowerCase());
        agent.setNom(agentDTO.getNom());
        agent.setPrenom(agentDTO.getPrenom());
        // agent.setCreatedBy(agentDTO.getCreatedBy());
        String encryptedPassword = passwordEncoder.encode(password);
        agent.setPassword(encryptedPassword);
        agent.setResetKey(RandomUtil.generateResetKey());
        agent.setResetDate(Instant.now());
        agent.setActif(agentDTO.isActif());
        if (agentDTO.getProfiles()!= null) {
            Set<Profile> profiles = agentDTO.getProfiles().stream()
                .map(profileRepository::findByName)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
            agent.setProfiles(profiles);
        }
        agentRepository.save(agent);
        this.clearAgentCaches(agent);
        log.debug("Created Information for Agent: {}", agent);
        return agent;
    }

    /**
     * Update all information for a specific agent, and return the modified agent.
     *
     * @param agentDTO agent to update.
     * @return updated agent.
     */
    public Optional<AgentDTO> updateAgent(AgentDTO agentDTO) {
        return Optional.of(agentRepository
            .findById(agentDTO.getId()))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(agent -> {
                this.clearAgentCaches(agent);
                agent.setLogin(agentDTO.getLogin().toLowerCase());
                agent.setNom(agentDTO.getNom());
                agent.setPrenom(agentDTO.getPrenom());
                agent.setActif(agentDTO.isActif());
                Set<Profile> managedAuthorities = agent.getProfiles();
                managedAuthorities.clear();
                agentDTO.getProfiles().stream()
                    .map(profileRepository::findByName)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .forEach(managedAuthorities::add);
                this.clearAgentCaches(agent);
                log.debug("Changed Information for Agent: {}", agent);
                return agent;
            })
            .map(AgentDTO::new);
    }

    public void deleteAgent(String login) {
        agentRepository.findOneByLogin(login).ifPresent(agent -> {
            agentRepository.delete(agent);
            this.clearAgentCaches(agent);
            log.debug("Deleted Agent: {}", agent);
        });
    }

    /**
     * Update basic information (nom, prenom, email) for the current agent.
     *
     * @param nom  nom of agent.
     * @param prenom  prenom of agent.
     * @param email  email of agent.
     */
    public void updateAgent(String nom, String prenom, String email) {
        SecurityUtils.getCurrentUserLogin()
            .flatMap(agentRepository::findOneByLogin)
            .ifPresent(agent -> {
                agent.setNom(nom);
                agent.setPrenom(prenom);
                if (email != null) {
                    agent.setEmail(email.toLowerCase());
                }
                this.clearAgentCaches(agent);
                log.debug("Changed Information for Agent: {}", agent);
            });
    }


    @Transactional
    public void changePassword(String currentClearTextPassword, String newPassword) {
        SecurityUtils.getCurrentUserLogin()
            .flatMap(agentRepository::findOneByLogin)
            .ifPresent(agent -> {
                String currentEncryptedPassword = agent.getPassword();
                if (!passwordEncoder.matches(currentClearTextPassword, currentEncryptedPassword)) {
                    throw new InvalidPasswordException();
                }
                String encryptedPassword = passwordEncoder.encode(newPassword);
                agent.setPassword(encryptedPassword);
                this.clearAgentCaches(agent);
                log.debug("Changed password for Agent: {}", agent);
            });
    }

    @Transactional(readOnly = true)
    public Page<AgentDTO> getAllManagedAgents(Pageable pageable) {
        return agentRepository.findAll(pageable).map(AgentDTO::new);
    }

    @Transactional(readOnly = true)
    public Optional<Agent> getAgentWithAuthoritiesByLogin(String login) {
        return agentRepository.findOneWithAuthoritiesByLogin(login);
    }

    @Transactional(readOnly = true)
    public Optional<Agent> getAgentWithAuthorities() {
        return SecurityUtils.getCurrentUserLogin().flatMap(agentRepository::findOneWithAuthoritiesByLogin);
    }

    /**
     * Not activated agents should be automatically deleted after 3 days.
     * <p>
     * This is scheduled to get fired everyday, at 01:00 (am).
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void removeNotActivatedAgents() {
        agentRepository
            .findAllByActifIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(Instant.now().minus(3, ChronoUnit.DAYS))
            .forEach(agent -> {
                log.debug("Deleting not activated agent {}", agent.getLogin());
                agentRepository.delete(agent);
                this.clearAgentCaches(agent);
            });
    }

    /**
     * Gets a list of all the authorities.
     * @return a list of all the authorities.
     */
    @Transactional(readOnly = true)
    public List<String> getProfiles() {
        return profileRepository.findAll().stream().map(Profile::getName).collect(Collectors.toList());
    }


    private void clearAgentCaches(Agent agent) {
        Objects.requireNonNull(cacheManager.getCache(AgentRepository.USERS_BY_LOGIN_CACHE)).evict(agent.getLogin());
    }
}
