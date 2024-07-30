package com.mefp.appdeclaration.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "intervenant_saisie")
public class IntervenantSaisie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String structureInterv;
    @ManyToOne(targetEntity = FicheSaisie.class)
    @JoinColumn(name="ficheSaisie_id")
    private FicheSaisie ficheSaisie;

    public IntervenantSaisie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStructureInterv() {
        return structureInterv;
    }

    public void setStructureInterv(String structureInterv) {
        this.structureInterv = structureInterv;
    }

    public FicheSaisie getFicheSaisie() {
        return ficheSaisie;
    }

    public void setFicheSaisie(FicheSaisie ficheSaisie) {
        this.ficheSaisie = ficheSaisie;
    }
}
