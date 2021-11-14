package com.mfptps.appdgessddi.service.dto;

import com.mfptps.appdgessddi.entities.Performance;


public class PonderationDTO {

    private double efficacité ;
    private double efficience ;
    private double gouvernance ;
    private double impact ;
    private boolean actif ;

    private Performance performance ;

    public PonderationDTO() {
    }

    public double getEfficacité() {
        return efficacité;
    }

    public void setEfficacité(double efficacité) {
        this.efficacité = efficacité;
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

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }
}
