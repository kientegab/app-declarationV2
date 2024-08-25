package com.mefp.appdeclaration.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "interpele")
public class Interpele {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, length = 10)
    @JoinColumn(name="interpele_id",nullable = false)
    private Long id;
    private Long nombre;
    private String nationalite;
    private String sexe;
    private String tranche;
    private String suiteDonne;
    @ManyToOne(targetEntity = FicheSaisie.class)
    @JoinColumn(name="ficheSaisie_id")
    private FicheSaisie fiche;

    public FicheSaisie getFiche() {
        return fiche;
    }

    public void setFiche(FicheSaisie fiche) {
        this.fiche = fiche;
    }

    public Interpele() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNombre() {
        return nombre;
    }

    public void setNombre(Long nombre) {
        this.nombre = nombre;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getTranche() {
        return tranche;
    }

    public void setTranche(String tranche) {
        this.tranche = tranche;
    }

    public String getSuiteDonne() {
        return suiteDonne;
    }

    public void setSuiteDonne(String suiteDonne) {
        this.suiteDonne = suiteDonne;
    }
}
