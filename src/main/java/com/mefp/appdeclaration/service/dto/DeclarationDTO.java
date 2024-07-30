package com.mefp.appdeclaration.service.dto;

import com.mefp.appdeclaration.entities.DeviseMontant;
import com.mefp.appdeclaration.entities.Document;
import com.mefp.appdeclaration.entities.Voyageur;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class DeclarationDTO {


    private Long id;

    private Date dateDeclaration;

    private String motifVoyage;

    private Long montantCFA;

    private Boolean estDeclare;

    private String nationalite;

    private String justification;

    private String commentaire;


    private Voyageur voyageur;

    private Set<Document> documents = new HashSet<>() ;

    private Set<DeviseMontant> deviseMontants = new HashSet<>() ;

    public DeclarationDTO(Long id) {
        this.id = id;
    }

    public Set<DeviseMontant> getDeviseMontants() {
        return deviseMontants;
    }

    public void setDeviseMontants(Set<DeviseMontant> deviseMontants) {
        this.deviseMontants = deviseMontants;
    }

    public Long getId() {
        return id;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Long getMontantCFA() {
        return montantCFA;
    }

    public void setMontantCFA(Long montantCFA) {
        this.montantCFA = montantCFA;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateDeclaration() {
        return dateDeclaration;
    }

    public void setDateDeclaration(Date dateDeclaration) {
        this.dateDeclaration = dateDeclaration;
    }

    public String getMotifVoyage() {
        return motifVoyage;
    }

    public void setMotifVoyage(String motifVoyage) {
        this.motifVoyage = motifVoyage;
    }



    public Boolean getEstDeclare() {
        return estDeclare;
    }

    public void setEstDeclare(Boolean estDeclare) {
        this.estDeclare = estDeclare;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }


    public Voyageur getVoyageur() {
        return voyageur;
    }

    public void setVoyageur(Voyageur voyageur) {
        this.voyageur = voyageur;
    }

    public DeclarationDTO() {
    }
}
