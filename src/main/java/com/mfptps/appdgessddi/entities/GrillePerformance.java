package com.mfptps.appdgessddi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "grille_performance")
public class GrillePerformance extends CommonEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double borneInferieur;
    private double borneSuperieur;
    private String appreciation;
    
    public GrillePerformance() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getBorneInferieur() {
        return borneInferieur;
    }

    public void setBorneInferieur(double borneInferieur) {
        this.borneInferieur = borneInferieur;
    }

    public double getBorneSuperieur() {
        return borneSuperieur;
    }

    public void setBorneSuperieur(double borneSuperieur) {
        this.borneSuperieur = borneSuperieur;
    }

    public String getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }

    public GrillePerformance(double borneInferieur, double borneSuperieur, String appreciation) {
        this.borneInferieur = borneInferieur;
        this.borneSuperieur = borneSuperieur;
        this.appreciation = appreciation;
    }
}
