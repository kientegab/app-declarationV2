package com.mefp.appdeclaration.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "fiche_prevention")
public class FichePrevention extends CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name="fichePrevention_id",nullable = false)
    @Column(nullable = false, unique = true, length = 10)
    private Long id;
    @Column(nullable = false, unique = true, length = 50)
    private Date datePrevention;
    private Date dateDebut;
    private Date dateFin;
    private String lieuActivite;
    private String cibleActivite;
    private  Long nbPerson;
    private Long nbFemme;
    private String commentaire;
    @ManyToOne(targetEntity = Structure.class)
    @JoinColumn(name="structure_id")
    private Structure structure;
    @ManyToOne(targetEntity = Activite.class)
    @JoinColumn(name="activite_id")
    private Activite activite;

    public FichePrevention() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatePrevention() {
        return datePrevention;
    }

    public void setDatePrevention(Date datePrevention) {
        this.datePrevention = datePrevention;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getLieuActivite() {
        return lieuActivite;
    }

    public void setLieuActivite(String lieuActivite) {
        this.lieuActivite = lieuActivite;
    }

    public String getCibleActivite() {
        return cibleActivite;
    }

    public void setCibleActivite(String cibleActivite) {
        this.cibleActivite = cibleActivite;
    }

    public Long getNbPerson() {
        return nbPerson;
    }

    public void setNbPerson(Long nbPerson) {
        this.nbPerson = nbPerson;
    }

    public Long getNbFemme() {
        return nbFemme;
    }

    public void setNbFemme(Long nbFemme) {
        this.nbFemme = nbFemme;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public Activite getActivite() {
        return activite;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }
}
