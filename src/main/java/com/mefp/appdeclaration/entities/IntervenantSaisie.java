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
//    @JoinColumn(name="structure_id")
//    private Structure structureSaisie;
//    @ManyToOne(targetEntity = FicheSaisie.class)
    @JoinColumn(name="ficheSaisie_id")
    private FicheSaisie ficheSaisie;
    @Column(nullable = true)
    private String IdentiteIntervenant;
    @Column(nullable = true)
    private String ContactIntervenant;

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
        return IdentiteIntervenant;
    }

    public void setIdentiteIntervenant(String identiteIntervenant) {
        IdentiteIntervenant = identiteIntervenant;
    }

    public String getContactIntervenant() {
        return ContactIntervenant;
    }

    public void setContactIntervenant(String contactIntervenant) {
        ContactIntervenant = contactIntervenant;
    }

    public FicheSaisie getFicheSaisie() {
        return ficheSaisie;
    }

    public void setFicheSaisie(FicheSaisie ficheSaisie) {
        this.ficheSaisie = ficheSaisie;
    }
}
