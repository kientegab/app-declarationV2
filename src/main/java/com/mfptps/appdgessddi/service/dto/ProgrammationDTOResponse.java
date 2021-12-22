/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mfptps.appdgessddi.entities.Activites;
import com.mfptps.appdgessddi.entities.Evaluation;
import com.mfptps.appdgessddi.entities.Exercice;
import com.mfptps.appdgessddi.entities.Objectif;
import com.mfptps.appdgessddi.entities.Projet;
import com.mfptps.appdgessddi.entities.SourceFinancement;
import com.mfptps.appdgessddi.entities.Structure;
import com.mfptps.appdgessddi.entities.Tache;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to return precise informations from Programmation
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public class ProgrammationDTOResponse {//NON ENCORE UTILISE

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String code;

    private double coutPrevisionnel;

    private double coutReel;

    private double cible;

    private String observations;

    private boolean estProgramme;

    private boolean singleton;

    private boolean validationInitial;

    private boolean validationInterne;

    private boolean validationFinal;

    @JsonIgnoreProperties(value = {"parent"})
    private Structure structure;

    //@JsonIgnoreProperties(value = {"programme"})
    private Projet projet;

    private SourceFinancement sourceFinancement;

    private Activites activite;

    @JsonIgnoreProperties(value = {"parent", "action.objectif"})
    private Objectif objectif;

    @JsonIgnoreProperties(value = {"observation", "ponderation"})
    private Exercice exercice;

    private List<Tache> taches = new ArrayList<>();

    @JsonIgnoreProperties(value = {"programmation"})
    private List<Evaluation> evaluations = new ArrayList<>();

    //=========================== CONSTRUCTORS ==============================
    public ProgrammationDTOResponse() {
    }

    public ProgrammationDTOResponse(String code, double coutPrevisionnel, double coutReel, double cible, String observations, boolean estProgramme, boolean singleton, boolean validationInitial, boolean validationInterne, boolean validationFinal, Structure structure, Projet projet, SourceFinancement sourceFinancement, Activites activite, Objectif objectif, Exercice exercice) {
        this.code = code;
        this.coutPrevisionnel = coutPrevisionnel;
        this.coutReel = coutReel;
        this.cible = cible;
        this.observations = observations;
        this.estProgramme = estProgramme;
        this.singleton = singleton;
        this.validationInitial = validationInitial;
        this.validationInterne = validationInterne;
        this.validationFinal = validationFinal;
        this.structure = structure;
        this.projet = projet;
        this.sourceFinancement = sourceFinancement;
        this.activite = activite;
        this.objectif = objectif;
        this.exercice = exercice;
    }

    //============================ GETTER && SETTER ===========================
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public double getCible() {
        return cible;
    }

    public void setCible(double cible) {
        this.cible = cible;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
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

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public SourceFinancement getSourceFinancement() {
        return sourceFinancement;
    }

    public void setSourceFinancement(SourceFinancement sourceFinancement) {
        this.sourceFinancement = sourceFinancement;
    }

    public Activites getActivite() {
        return activite;
    }

    public void setActivite(Activites activite) {
        this.activite = activite;
    }

    public Objectif getObjectif() {
        return objectif;
    }

    public void setObjectif(Objectif objectif) {
        this.objectif = objectif;
    }

    public Exercice getExercice() {
        return exercice;
    }

    public void setExercice(Exercice exercice) {
        this.exercice = exercice;
    }

    public List<Tache> getTaches() {
        return taches;
    }

    public void setTaches(List<Tache> taches) {
        this.taches = taches;
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

}
