package com.mefp.appdeclaration.entities;

//import jakarta.persistence.*;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "devise", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"libelle"}, name = "ux_libelle_in_devise")})
public class Devise extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_devise")
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name="devise_id",nullable = false)
    @Column(nullable = false, unique = true, length = 10)
    private Long id;

    @Column(nullable = false, length = 90)
    private String code;

    private String libelle;

    public Devise() {
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
}
