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
@Table(name = "performance")
@SQLDelete(sql = "UPDATE performance SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@FilterDef(name = "deletedFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedFilter", condition = "deleted = :isDeleted")
public class Performance  extends CommonEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double efficacité ;
    private double efficience ;
    private double gouvernance ;
    private double impact ;
    private double pgs ;
    private Performer performer ;

    public Performance() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Performer getPerformer() {
        return performer;
    }

    public void setPerformer(Performer performer) {
        this.performer = performer;
    }
}
