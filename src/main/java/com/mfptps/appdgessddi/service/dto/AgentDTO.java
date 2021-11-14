package com.mfptps.appdgessddi.service.dto;

import javax.validation.constraints.*;

import com.mfptps.appdgessddi.config.Constants;
import com.mfptps.appdgessddi.entities.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO representing a agent, with his profiles.
 */
public class AgentDTO {

    private Long id;

    @NotBlank
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String matricule;

    @Size(max = 50)
    private String nom;

    @Size(max = 50)
    private String prenom;
    
    private String telephone;

    @Email
    @Size(min = 5, max = 254)
    private String email;

    private boolean actif = false;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;
    
    private Set<String> permissions;
    
    private Set<String> profiles;

    public AgentDTO() {
    }

    public AgentDTO(Agent agent) {
        this.id = agent.getId();
        this.matricule = agent.getMatricule();
        this.nom = agent.getNom();
        this.prenom = agent.getPrenom();
        this.email = agent.getEmail();
        this.actif = agent.isActif();
        this.createdBy = agent.getCreatedBy();
        this.createdDate = agent.getCreatedDate();
        this.lastModifiedBy = agent.getLastModifiedBy();
        this.lastModifiedDate = agent.getLastModifiedDate();
        Set<Permission> actions = new HashSet<>();
        Set<String> prof = new HashSet<>();
        agent.getProfiles().stream().forEach(r -> {
            prof.add(r.getName());
            actions.addAll(r.getPermissions());
                });
        this.profiles = prof;
        this.permissions = actions.stream()
            .map(Permission::getName)
            .collect(Collectors.toSet());
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public Set<String> getProfiles() {
        return profiles;
    }

    public void setProfiles(Set<String> profiles) {
        this.profiles = profiles;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "AgentDTO{" +
            "matricule='" + matricule + '\'' +
            ", nom='" + nom + '\'' +
            ", prenom='" + prenom + '\'' +
            ", email='" + email + '\'' +
            ", actif=" + actif +
            ", createdBy=" + createdBy +
            ", createdDate=" + createdDate +
            ", lastModifiedBy='" + lastModifiedBy + '\'' +
            ", lastModifiedDate=" + lastModifiedDate +
            ", profiles=" + profiles +
            ", permissions=" + permissions +
            "}";
    }
}
