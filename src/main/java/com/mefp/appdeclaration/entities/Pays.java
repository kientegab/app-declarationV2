package com.mefp.appdeclaration.entities;

//import jakarta.persistence.*;
import lombok.Data;

import javax.persistence.*;

//@author  <brikientega@gmail.com>
@Data
@Entity
@Table(name = "pays", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"libelle"}, name = "ux_libelle_in_pays")})
public class Pays  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_pays")
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name="pays_id",nullable = false)
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private String code;

    @Column(nullable = false, length = 90)
    private String libelle;

    @Column(length = 90)
    private String nationalite;

    public Pays(Long id) {
        this.id = id;
    }

    public Pays() {
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

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }
}
