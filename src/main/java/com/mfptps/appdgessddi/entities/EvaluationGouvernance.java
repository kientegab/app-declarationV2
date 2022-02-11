package com.mfptps.appdgessddi.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

@Entity
@Table(name = "evaluation_gouvernance")
public class EvaluationGouvernance extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double valeur;
    private double valeurReference;
    private boolean applicable;
    @JsonIgnoreProperties(value = {"observation", "ponderation"})
    @ManyToOne
    @JoinColumn(unique = true)
    private Exercice exercice;
    @ManyToOne
    @JoinColumn(unique = true)
    private CritereGouvernance critereGouvernance;

    @JsonIgnoreProperties(value = {"parent"})
    @ManyToOne
    @JoinColumn(unique = true)
    private Structure structure;

    public EvaluationGouvernance() {
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

    @Override
    public String toString() {
        return "EvaluationGouvernance{"
                + "id=" + id
                + ", valeur=" + valeur
                + ", valeurReference=" + valeurReference
                + ", applicable=" + applicable
                + ", exercice=" + exercice
                + ", critereGouvernance=" + critereGouvernance
                + ", structure=" + structure
                + '}';
    }
}
