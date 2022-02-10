package com.mfptps.appdgessddi.service.dto;

import com.mfptps.appdgessddi.entities.CritereGouvernance;
import com.mfptps.appdgessddi.entities.Exercice;
import com.mfptps.appdgessddi.entities.Structure;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class EvaluationGouvernanceDTO {

    private Long id;
    private double valeur ;
    private double valeurReference ;
    private boolean applicable ;
    private Exercice exercice ;
    private CritereGouvernance critereGouvernance ;
    private Structure structure ;

    public EvaluationGouvernanceDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public double getValeurReference() {
        return valeurReference;
    }

    public void setValeurReference(double valeurReference) {
        this.valeurReference = valeurReference;
    }

    public boolean isApplicable() {
        return applicable;
    }

    public void setApplicable(boolean applicable) {
        this.applicable = applicable;
    }

    public Exercice getExercice() {
        return exercice;
    }

    public void setExercice(Exercice exercice) {
        this.exercice = exercice;
    }

    public CritereGouvernance getCritereGouvernance() {
        return critereGouvernance;
    }

    public void setCritereGouvernance(CritereGouvernance critereGouvernance) {
        this.critereGouvernance = critereGouvernance;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }
}
