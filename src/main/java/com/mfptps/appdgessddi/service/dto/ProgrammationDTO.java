/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.service.dto;

import com.mfptps.appdgessddi.entities.Tache;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public class ProgrammationDTO {

    private double coutPrevisionnel;

    private double coutReel;

    private double cible;

    private String observation;

    private boolean singleton;

    private Long sourceFinancementId;

    private Long activiteId;//TO BE CHANGE INTO ENTITY

    private List<Tache> taches = new ArrayList<>();

    //======================= CONSTRUCTORS && GETTERS/SETTERS
    public ProgrammationDTO() {
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

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public boolean isSingleton() {
        return singleton;
    }

    public void setSingleton(boolean singleton) {
        this.singleton = singleton;
    }

    public Long getSourceFinancementId() {
        return sourceFinancementId;
    }

    public void setSourceFinancementId(Long sourceFinancementId) {
        this.sourceFinancementId = sourceFinancementId;
    }

    public Long getActiviteId() {
        return activiteId;
    }

    public void setActiviteId(Long activiteId) {
        this.activiteId = activiteId;
    }

    public List<Tache> getTaches() {
        return taches;
    }

    public void setTaches(List<Tache> taches) {
        this.taches = taches;
    }

}
