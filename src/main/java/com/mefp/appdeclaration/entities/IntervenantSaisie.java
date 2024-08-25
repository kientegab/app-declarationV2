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
    @ManyToOne(targetEntity = Structure.class)
    @JoinColumn(name="structure_id")
    private Structure structureIntervenant;
    @ManyToOne(targetEntity = FicheSaisie.class)
    @JoinColumn(name="ficheSaisie_id")
    private FicheSaisie ficheSaisie;
    @Column(nullable = true)
    private String identiteIntervenant;
    @Column(nullable = true)
    private String contactIntervenant;

    public Structure getStructureIntervenant() {
        return structureIntervenant;
    }

    public void setStructureIntervenant(Structure structureIntervenant) {
        this.structureIntervenant = structureIntervenant;
    }

    public IntervenantSaisie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Structure getStructureSaisie() {
//        return structureSaisie;
//    }
//
//    public void setStructureSaisie(Structure structureSaisie) {
//        this.structureSaisie = structureSaisie;
//    }


    public String getIdentiteIntervenant() {
        return identiteIntervenant;
    }

    public void setIdentiteIntervenant(String identiteIntervenant) {
        this.identiteIntervenant = identiteIntervenant;
    }

    public String getContactIntervenant() {
        return contactIntervenant;
    }

    public void setContactIntervenant(String contactIntervenant) {
        this.contactIntervenant = contactIntervenant;
    }

    public FicheSaisie getFicheSaisie() {
        return ficheSaisie;
    }

    public void setFicheSaisie(FicheSaisie ficheSaisie) {
        this.ficheSaisie = ficheSaisie;
    }
}
