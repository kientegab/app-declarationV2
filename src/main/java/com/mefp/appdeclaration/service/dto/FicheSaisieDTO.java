package com.mefp.appdeclaration.service.dto;

import com.mefp.appdeclaration.entities.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class FicheSaisieDTO {
    private Long id;

    private String numSaisie;
    private Date dateSaisie;
    private Long anneeSaisie;
    private Structure structureSaisie;
    private Ville lieuSaisie;
    private  String itineraire;
    private String commentaire;
    private Set<NatureSaisie> nature = new HashSet<>() ;
    private Set<ProcedeSaisie> procede = new HashSet<>() ;
    private Set<IntervenantSaisie> intervenant = new HashSet<>() ;

    public FicheSaisieDTO(Long id, String numSaisie, Date dateSaisie, Long anneeSaisie, Structure structureSaisie, Ville lieuSaisie, String itineraire, String commentaire, Set<NatureSaisie> nature, Set<ProcedeSaisie> procede, Set<IntervenantSaisie> intervenant) {
        this.id = id;
        this.numSaisie = numSaisie;
        this.dateSaisie = dateSaisie;
        this.anneeSaisie = anneeSaisie;
        this.structureSaisie = structureSaisie;
        this.lieuSaisie = lieuSaisie;
        this.itineraire = itineraire;
        this.commentaire = commentaire;
        this.nature = nature;
        this.procede = procede;
        this.intervenant = intervenant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getAnneeSaisie() {
        return anneeSaisie;
    }

    public void setAnneeSaisie(Long anneeSaisie) {
        this.anneeSaisie = anneeSaisie;
    }
    public String getNumSaisie() {
        return numSaisie;
    }

    public void setNumSaisie(String numSaisie) {
        this.numSaisie = numSaisie;
    }

    public Date getDateSaisie() {
        return dateSaisie;
    }

    public void setDateSaisie(Date dateSaisie) {
        this.dateSaisie = dateSaisie;
    }

    public Structure getStructureSaisie() {
        return structureSaisie;
    }

    public void setStructureSaisie(Structure structureSaisie) {
        this.structureSaisie = structureSaisie;
    }

    public Ville getLieuSaisie() {
        return lieuSaisie;
    }

    public void setLieuSaisie(Ville lieuSaisie) {
        this.lieuSaisie = lieuSaisie;
    }

    public String getItineraire() {
        return itineraire;
    }

    public void setItineraire(String itineraire) {
        this.itineraire = itineraire;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Set<NatureSaisie> getNature() {
        return nature;
    }

    public void setNature(Set<NatureSaisie> nature) {
        this.nature = nature;
    }


    public Set<ProcedeSaisie> getProcede() {
        return procede;
    }

    public void setProcede(Set<ProcedeSaisie> procede) {
        this.procede = procede;
    }

    public Set<IntervenantSaisie> getIntervenant() {
        return intervenant;
    }

    public void setIntervenant(Set<IntervenantSaisie> intervenant) {
        this.intervenant = intervenant;
    }
}
