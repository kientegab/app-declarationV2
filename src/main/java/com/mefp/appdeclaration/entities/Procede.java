package com.mefp.appdeclaration.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "procede", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"libelle"}, name = "ux_libelle_in_procede")})
public class Procede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name="procede_id",nullable = false)
    @Column(nullable = false, unique = true, length = 10)
    private Long id;

    @Column(nullable = false, length = 90)
    private String code;

    private String libelle;
    private  String description;
    private Date valideDe;  // la date de début de validité de la nature
    private  Date valideFin; // la date de fin de validité de la nature

    public Procede() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Date getValideDe() {
        return valideDe;
    }

    public void setValideDe(Date valideDe) {
        this.valideDe = valideDe;
    }

    public Date getValideFin() {
        return valideFin;
    }

    public void setValideFin(Date valideFin) {
        this.valideFin = valideFin;
    }
}
