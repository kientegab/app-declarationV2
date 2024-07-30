package com.mefp.appdeclaration.entities;

//@author  <brikientega@gmail.com>

//import jakarta.persistence.*;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table (name = "Voyageur")
public class Voyageur  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "sequence_voyageur")
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name="voyageur_id",nullable = false)
    @Column(nullable = false, unique = true, length = 10)
    private Long id;

    private String nom;

    private String prenom;

    private  String numIdentite;

    @Column(nullable = false, length =20)
    private  String refDocument;

    private Long telephone;

    private String adresse;

    private String profession;

    @ManyToOne(targetEntity = Ville.class)
    @JoinColumn(name = "ville_id", nullable = false)
    private Ville villeProvenance;

    private String villeDestination;

    public Voyageur() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNumIdentite() {
        return numIdentite;
    }

    public void setNumIdentite(String numIdentite) {
        this.numIdentite = numIdentite;
    }

    public String getRefDocument() {
        return refDocument;
    }

    public void setRefDocument(String refDocument) {
        this.refDocument = refDocument;
    }

    public Long getTelephone() {
        return telephone;
    }

    public void setTelephone(Long telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Ville getVilleProvenance() {
        return villeProvenance;
    }

    public void setVilleProvenance(Ville villeProvenance) {
        this.villeProvenance = villeProvenance;
    }

    public String getVilleDestination() {
        return villeDestination;
    }

    public void setVilleDestination(String villeDestination) {
        this.villeDestination = villeDestination;
    }
}
