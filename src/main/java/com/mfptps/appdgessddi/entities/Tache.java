/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Entity
@Table(name = "tache")
public class Tache extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String libelle;

    private String description;

    private double ponderation;

    private double valeur;

    @Column(name = "est_execute", nullable = false)
    @Type(type = "yes_no")
    private boolean execute = false;//false means !execute.

    //================ relationships 
    @ManyToOne
    private Programmation programmation;

    //================ CONSTRUCTOR && GETTERS/SETTERS 
    public Tache() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPonderation() {
        return ponderation;
    }

    public void setPonderation(double ponderation) {
        this.ponderation = ponderation;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public boolean isExecute() {
        return execute;
    }

    public void setExecute(boolean execute) {
        this.execute = execute;
    }

    public Programmation getProgrammation() {
        return programmation;
    }

    public void setProgrammation(Programmation programmation) {
        this.programmation = programmation;
    }

}
