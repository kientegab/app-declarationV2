package com.mfptps.appdgessddi.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "EvaluationGouvernance",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"EXERCICE_ID","CRITERE_ID","STRUCTURE_ID"})})
public class EvaluationGouvernance extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "VALEUR")
    private double valeur ;
    
    @Column(name = "VALEUR_REFERENCE")
    private double valeurReference ;
    
    @Type(type = "yes_no")
    @Column(name = "NON_APPLICABLE")
    private boolean nonapplicable ;
    
    @JsonIgnoreProperties(value = {"observation", "ponderation"})
    @ManyToOne
    @JoinColumn(name = "EXERCICE_ID", nullable = false)
    private Exercice exercice ;
    @ManyToOne
    @JoinColumn(name = "CRITERE_ID", nullable = false)
    private CritereGouvernance critereGouvernance ;

    @JsonIgnoreProperties(value = {"parent"})
    @ManyToOne
    @JoinColumn(name = "STRUCTURE_ID", nullable = false)
    private Structure structure ;

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

    public boolean isNonapplicable() {
        return nonapplicable;
    } 

    public void setNonapplicable(boolean nonapplicable) {
        this.nonapplicable = nonapplicable;
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
        return "EvaluationGouvernance{" +
                "id=" + id +
                ", valeur=" + valeur +
                ", valeurReference=" + valeurReference +
                ", nonapplicable=" + nonapplicable +
                ", exercice=" + exercice +
                ", critereGouvernance=" + critereGouvernance +
                ", structure=" + structure +
                '}';
    }
}
