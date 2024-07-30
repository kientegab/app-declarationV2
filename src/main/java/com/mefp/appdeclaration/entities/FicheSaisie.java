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
    @Column(nullable = false, unique = true, length = 50)
    private String numSaisie;
    private Date dateSaisie;

    @ManyToOne(targetEntity = Structure.class)
    @JoinColumn(name="structure_id")
    private Structure structureSaisie;
    @ManyToOne(targetEntity = Ville.class)
    @JoinColumn(name="ville_id")
    private Ville lieuSaisie;
    private  String itinéraire;
    private String commentaire;

    public FicheSaisie() {
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

    public String getItinéraire() {
        return itinéraire;
    }

    public void setItinéraire(String itinéraire) {
        this.itinéraire = itinéraire;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}
