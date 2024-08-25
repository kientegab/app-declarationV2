package com.mefp.appdeclaration.entities.dto;

import com.mefp.appdeclaration.entities.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class EtatSaisie {

    private String numSaisie;
    private Date dateSaisie;
    private String lieuSaisie;
    private  String itineraire;
    private String nature;
    private String procede;
    private Long totalPoids;
    private Long totalValeur;


    public EtatSaisie(String numSaisie, Date dateSaisie, String lieuSaisie, String itineraire, String nature, String procede, Long totalPoids, Long totalValeur) {
        this.numSaisie = numSaisie;
        this.dateSaisie = dateSaisie;
        this.lieuSaisie = lieuSaisie;
        this.itineraire = itineraire;
        this.nature = nature;
        this.procede = procede;
        this.totalPoids = totalPoids;
        this.totalValeur = totalValeur;

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

    public String getLieuSaisie() {
        return lieuSaisie;
    }

    public void setLieuSaisie(String lieuSaisie) {
        this.lieuSaisie = lieuSaisie;
    }

    public String getItineraire() {
        return itineraire;
    }

    public void setItineraire(String itineraire) {
        this.itineraire = itineraire;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getProcede() {
        return procede;
    }

    public void setProcede(String procede) {
        this.procede = procede;
    }

    public Long getTotalPoids() {
        return totalPoids;
    }

    public void setTotalPoids(Long totalPoids) {
        this.totalPoids = totalPoids;
    }

    public Long getTotalValeur() {
        return totalValeur;
    }

    public void setTotalValeur(Long totalValeur) {
        this.totalValeur = totalValeur;
    }

}
