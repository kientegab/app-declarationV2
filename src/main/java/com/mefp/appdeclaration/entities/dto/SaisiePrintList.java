package com.mefp.appdeclaration.entities.dto;

import lombok.Data;

import java.util.Date;
@Data
public class SaisiePrintList {
    private String  dateSaisie;
    private String numSaisie;
    private String lieuSaisie;
    private String itineraire;
    private String nature;
    private String procede;
    private String poids;
    private String valeur;

    public SaisiePrintList() {
    }

    public String getDateSaisie() {
        return dateSaisie;
    }

    public void setDateSaisie(String dateSaisie) {
        this.dateSaisie = dateSaisie;
    }

    public String getNumSaisie() {
        return numSaisie;
    }

    public void setNumSaisie(String numSaisie) {
        this.numSaisie = numSaisie;
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

    public String getPoids() {
        return poids;
    }

    public void setPoids(String poids) {
        this.poids = poids;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }
}
