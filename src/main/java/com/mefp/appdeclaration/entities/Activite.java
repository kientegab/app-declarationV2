package com.mefp.appdeclaration.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "activite", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"libelle"}, name = "ux_libelle_in_activite")})
public class Activite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name="activite_id",nullable = false)
    @Column(nullable = false, unique = true, length = 10)
    private Long id;

    @Column(nullable = false, length = 90)
    private String type;

    private String libelle;
    private  String description;
    private Date valideDe;
    private  Date valideFin;

    public Activite() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
