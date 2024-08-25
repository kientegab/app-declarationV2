package com.mefp.appdeclaration.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "destination")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, length = 10)
    @JoinColumn(name="destnation_id",nullable = false)
    private Long id;

    private Long exportation;
    private Long vente;
    private Long usage;
    @ManyToOne(targetEntity = FicheSaisie.class)
    @JoinColumn(name="ficheSaisie_id")
    private FicheSaisie fiche;

    public FicheSaisie getFiche() {
        return fiche;
    }

    public void setFiche(FicheSaisie fiche) {
        this.fiche = fiche;
    }

    public Destination() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExportation() {
        return exportation;
    }

    public void setExportation(Long exportation) {
        this.exportation = exportation;
    }

    public Long getVente() {
        return vente;
    }

    public void setVente(Long vente) {
        this.vente = vente;
    }

    public Long getUsage() {
        return usage;
    }

    public void setUsage(Long usage) {
        this.usage = usage;
    }
}
