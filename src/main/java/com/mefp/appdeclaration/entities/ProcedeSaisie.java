package com.mefp.appdeclaration.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
@Data
@Entity
@Table(name = "procede_saisie")
public class ProcedeSaisie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(targetEntity = FicheSaisie.class)
    @JoinColumn(name="ficheSaisie_id")
    private FicheSaisie ficheSaisie;
    @ManyToOne(targetEntity = Procede.class)
    @JoinColumn(name="procede_id")
    private  Procede procede;

    public ProcedeSaisie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FicheSaisie getFicheSaisie() {
        return ficheSaisie;
    }

    public void setFicheSaisie(FicheSaisie ficheSaisie) {
        this.ficheSaisie = ficheSaisie;
    }

    public Procede getProcede() {
        return procede;
    }

    public void setProcede(Procede procede) {
        this.procede = procede;
    }
}
