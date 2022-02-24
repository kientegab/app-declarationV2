/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mfptps.appdgessddi.entities.Exercice;
import com.mfptps.appdgessddi.entities.Ponderation;
import com.mfptps.appdgessddi.entities.Structure;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public class PerformanceEntityDTO {

    private Long id;

    private double efficacite;

    private double efficience;

    private double gouvernance;

    private double impact;

    private double pgs;

    private String appreciation;

    private Ponderation ponderation;

    private Exercice exercice;

    @JsonIgnoreProperties("parent")
    private Structure structure;

    //======================================================
    public PerformanceEntityDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getEfficacite() {
        return efficacite;
    }

    public void setEfficacite(double efficacite) {
        this.efficacite = efficacite;
    }

    public double getEfficience() {
        return efficience;
    }

    public void setEfficience(double efficience) {
        this.efficience = efficience;
    }

    public double getGouvernance() {
        return gouvernance;
    }

    public void setGouvernance(double gouvernance) {
        this.gouvernance = gouvernance;
    }

    public double getImpact() {
        return impact;
    }

    public void setImpact(double impact) {
        this.impact = impact;
    }

    public double getPgs() {
        return pgs;
    }

    public void setPgs(double pgs) {
        this.pgs = pgs;
    }

    public String getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }

    public Ponderation getPonderation() {
        return ponderation;
    }

    public void setPonderation(Ponderation ponderation) {
        this.ponderation = ponderation;
    }

    public Exercice getExercice() {
        return exercice;
    }

    public void setExercice(Exercice exercice) {
        this.exercice = exercice;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

}
