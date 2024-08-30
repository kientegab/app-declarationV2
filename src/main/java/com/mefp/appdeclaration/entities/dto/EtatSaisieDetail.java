package com.mefp.appdeclaration.entities.dto;

import java.util.Date;

public class EtatSaisieDetail {
   // private String structure;
    private String nature;
    private Long poids;
    private Date dateSaisie;
    private String lieuSaisie;
    private String itineraire;
    private Long exportation;
    private Long vente;
    private Long usage;
    private Long nombre;
    private String nationalite;
    private String sexe;
    private String tranche;
    private String suiteDonne;

    public EtatSaisieDetail(String nature, Long poids, Date dateSaisie, String lieuSaisie, String itineraire, Long exportation, Long vente, Long usage, Long nombre, String nationalite, String sexe, String tranche, String suiteDonne) {
        this.nature = nature;
        this.poids = poids;
        this.dateSaisie = dateSaisie;
        this.lieuSaisie = lieuSaisie;
        this.itineraire = itineraire;
        this.exportation = exportation;
        this.vente = vente;
        this.usage = usage;
        this.nombre = nombre;
        this.nationalite = nationalite;
        this.sexe = sexe;
        this.tranche = tranche;
        this.suiteDonne=suiteDonne;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }



    public void setPoids(Long poids) {
        this.poids = poids;
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

    public Long getPoids() {
        return poids;
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

    public Long getExportation() {
        return exportation;
    }

    public void setExportation(Long exportation) {
        this.exportation = exportation;
    }

    public Long getVente() {
        return vente;
    }

    public void setVente(Long vente) {
        this.vente = vente;
    }

    public Long getUsage() {
        return usage;
    }

    public void setUsage(Long usage) {
        this.usage = usage;
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
