package com.mfptps.appdgessddi.service.dto;

import com.mfptps.appdgessddi.entities.Performer;

public class PerformanceDTO {

    private double efficacité ;
    private double efficience ;
    private double gouvernance ;
    private double impact ;
    private double pgs ;



    public PerformanceDTO() {
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

    public double getPgs() {
        return pgs;
    }

    public void setPgs(double pgs) {
        this.pgs = pgs;
    }
}
