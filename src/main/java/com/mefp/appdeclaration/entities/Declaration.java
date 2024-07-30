package com.mefp.appdeclaration.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import jakarta.persistence.*;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "declaration")
public class Declaration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_decl")
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, length = 10)
    @JoinColumn(name="declaration_id",nullable = false)
    private Long id;

    private Date dateDeclaration;

    private String motifVoyage;
    //@ManyToOne(targetEntity = Devise.class)
   // @JoinColumn(name="devise_id")

   // private Devise devise;

   // private Long montant;

    private Long montantCFA;

    private Boolean estDeclare;

    private String nationalite;

    @Column(nullable = true)
    private String justification;

    private String commentaire;

    @ManyToOne(targetEntity = Voyageur.class)
    @JoinColumn(name="voyageur_id")
    private Voyageur voyageur;
    //@OneToMany(mappedBy = "declaration", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   // @JsonIgnoreProperties(value = {"declaration"}, allowSetters = true)
    //private Set<Document> documents = new HashSet<>() ;
   // private List<Document> documents;
   //@OneToMany(mappedBy = "devise", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   //@JsonIgnoreProperties(value = {"devise"}, allowSetters = true)
    //private Set<DeviseMontantdto> deviseMontants;
   //private List<DeviseMontantdto> deviseMontants;
    public Declaration(Long id) {
        this.id = id;
    }



    public Long getId() {
        return id;
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

    public Declaration() {
    }
}
