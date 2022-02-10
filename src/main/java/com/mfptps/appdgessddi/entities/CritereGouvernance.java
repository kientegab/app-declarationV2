package com.mfptps.appdgessddi.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "CritereGouvernance")
public class CritereGouvernance  extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String indicateur ;
    private boolean mode ;
    @Type(type = "yes_no")
    private boolean actif;

    public CritereGouvernance() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIndicateur() {
        return indicateur;
    }

    public void setIndicateur(String indicateur) {
        this.indicateur = indicateur;
    }

    public boolean isMode() {
        return mode;
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }
}
