package com.mefp.appdeclaration.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Data
@Entity
@Table(name = "fiche_Saisie")
public class FicheSaisie extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name="ficheSaisie_id",nullable = false)
    @Column(nullable = false, unique = true, length = 10)
    private Long id;
//    @Column(nullable = false, unique = true, length = 50)
    private String numSaisie;
//    @Column(nullable = false, unique = true, length = 50)
    private Date dateSaisie;
    private int anneeSaisie;
    @ManyToOne(targetEntity = Structure.class)
    @JoinColumn(name="structure_id")
    private Structure structureSaisie;
    @ManyToOne(targetEntity = Ville.class)
    @JoinColumn(name="ville_id")
    private Ville lieuSaisie;
    private  String itineraire;
    private String commentaire;

    public FicheSaisie() {
    }


    public FicheSaisie(Long id, String numSaisie, Date dateSaisie, int anneeSaisie, Structure structureSaisie, Ville lieuSaisie, String itineraire, String commentaire) {
        this.id = id;
        this.numSaisie = numSaisie;
        this.dateSaisie = dateSaisie;
        this.anneeSaisie = anneeSaisie;
        this.structureSaisie = structureSaisie;
        this.lieuSaisie = lieuSaisie;
        this.itineraire = itineraire;
        this.commentaire = commentaire;
    }


    public int getAnneeSaisie() {
        return anneeSaisie;
    }

    public void setAnneeSaisie(int anneeSaisie) {
        this.anneeSaisie = anneeSaisie;
    }

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
}
