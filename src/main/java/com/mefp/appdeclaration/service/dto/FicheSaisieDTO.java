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

    public Long getAnneeSaisie() {
        return anneeSaisie;
    }

    public void setAnneeSaisie(Long anneeSaisie) {
        this.anneeSaisie = anneeSaisie;
    }

    private Structure structureSaisie;

    private Ville lieuSaisie;
    private  String itineraire;
    private String commentaire;
    private Set<NatureSaisie> nature = new HashSet<>() ;
    private Set<ProcedeSaisie> procede = new HashSet<>() ;
    private Set<IntervenantSaisie> intervenant = new HashSet<>() ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getItinéraire() {
        return itineraire;
    }

    public void setItinéraire(String itinéraire) {
        this.itineraire = itinéraire;
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
