package com.mefp.appdeclaration.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
@Data
@Entity
@Table(name = "nature_saisie")
public class NatureSaisie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(targetEntity = Nature.class)
    @JoinColumn(name="nature_id")
    private Nature nature;
    private Long poids;
    private Long valeur;
    @ManyToOne(targetEntity = FicheSaisie.class)
    @JoinColumn(name="ficheSaisie_id")
    private FicheSaisie ficheSaisie;

    public NatureSaisie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Nature getNature() {
        return nature;
    }

    public void setNature(Nature nature) {
        this.nature = nature;
    }

    public Long getPoids() {
        return poids;
    }

    public void setPoids(Long poids) {
        this.poids = poids;
    }

    public Long getValeur() {
        return valeur;
    }

    public void setValeur(Long valeur) {
        this.valeur = valeur;
    }

    public FicheSaisie getFicheSaisie(FicheSaisie ficheSaisie) {
        return this.ficheSaisie;
    }

    public void setFicheSaisie(FicheSaisie ficheSaisie) {
        this.ficheSaisie = ficheSaisie;
    }
}
