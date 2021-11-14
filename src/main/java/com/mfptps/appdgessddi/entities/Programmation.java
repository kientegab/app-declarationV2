/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * This entity is similar to Activite
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Entity
@Table(name = "programmation")
public class Programmation extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double coutPrevisionnel;

    private double coutReel;

    private boolean estProgramme = true;//If activite is programmed

    private boolean singleton; //If this Programmation have just one Tache

    private double cible;

    private String observation;

    @Column(name = "valid_initial", nullable = false)
    private boolean validationInitial = true;

    @Column(name = "valid_interne")
    private boolean validationInterne;

    @Column(name = "valid_final")
    private boolean validationFinal;

    //============= relationships 
    @ManyToOne
    private SourceFinancement sourceFinancement;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "programmation"/*, cascade = CascadeType.PERSIST*/)
    private List<Tache> taches;

    @ManyToOne
    private Activites activite;

    @ManyToOne
    private Projet projet;

    @ManyToOne
    private Structure structure;

    //============== CONSTRUCTORS && GETTERS/SETTERS
    public Programmation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getCoutPrevisionnel() {
        return coutPrevisionnel;
    }

    public void setCoutPrevisionnel(double coutPrevisionnel) {
        this.coutPrevisionnel = coutPrevisionnel;
    }

    public double getCoutReel() {
        return coutReel;
    }

    public void setCoutReel(double coutReel) {
        this.coutReel = coutReel;
    }

    public boolean isEstProgramme() {
        return estProgramme;
    }

    public void setEstProgramme(boolean estProgramme) {
        this.estProgramme = estProgramme;
    }

    public boolean isSingleton() {
        return singleton;
    }

    public void setSingleton(boolean singleton) {
        this.singleton = singleton;
    }

    public double getCible() {
        return cible;
    }

    public void setCible(double cible) {
        this.cible = cible;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public boolean isValidationInitial() {
        return validationInitial;
    }

    public void setValidationInitial(boolean validationInitial) {
        this.validationInitial = validationInitial;
    }

    public boolean isValidationInterne() {
        return validationInterne;
    }

    public void setValidationInterne(boolean validationInterne) {
        this.validationInterne = validationInterne;
    }

    public boolean isValidationFinal() {
        return validationFinal;
    }

    public void setValidationFinal(boolean validationFinal) {
        this.validationFinal = validationFinal;
    }

    public SourceFinancement getSourceFinancement() {
        return sourceFinancement;
    }

    public void setSourceFinancement(SourceFinancement sourceFinancement) {
        this.sourceFinancement = sourceFinancement;
    }

    public List<Tache> getTaches() {
        return taches;
    }

    public void setTaches(List<Tache> taches) {
        this.taches = taches;
    }

    public Activites getActivite() {
        return activite;
    }

    public void setActivite(Activites activite) {
        this.activite = activite;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    /**
     * Previous to check if sum of taches's ponderation equals 100%
     *
     * @return
     */
    public double checkPonderation() {
        double total = this.taches.stream().map(tache -> tache.getPonderation())
                .reduce(0D, (subtotal, element) -> subtotal + element);
        return total;
    }
}
