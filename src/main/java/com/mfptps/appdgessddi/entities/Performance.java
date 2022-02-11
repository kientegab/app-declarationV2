package com.mfptps.appdgessddi.entities;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "performance",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"EXERCICE_ID","STRUCTURE_ID","PONDERATION_ID"})})
@SQLDelete(sql = "UPDATE performance SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@FilterDef(name = "deletedFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedFilter", condition = "deleted = :isDeleted")
public class Performance  extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "EFFICACITE")
    private double efficacite ;
    
    @Column(name = "EFFICIENCE")
    private double efficience ;
    
    @Column(name = "GOUVERNANCE")
    private double gouvernance ;
    
    @Column(name = "IMPACT")
    private double impact ;
    
    @Column(name = "PGS")
    private double pgs ;
    
    @Column(name = "EXERCICE_ID", nullable = false)
    private long exerciceId;
    
    @Column(name = "STRUCTURE_ID", nullable = false)
    private long structureId;
    
    @Column(name = "PONDERATION_ID", nullable = false)
    private long ponderationId;
    
    @Column(name = "APPRECIATION", nullable = false)
    private String appreciation;

    public Performance() {
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

    public long getExerciceId() {
        return exerciceId;
    }

    public void setExerciceId(long exerciceId) {
        this.exerciceId = exerciceId;
    }

    public long getStructureId() {
        return structureId;
    }

    public void setStructureId(long structureId) {
        this.structureId = structureId;
    }

    public long getPonderationId() {
        return ponderationId;
    }

    public void setPonderationId(long ponderationId) {
        this.ponderationId = ponderationId;
    }
    
    
}
