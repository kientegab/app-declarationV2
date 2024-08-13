package com.mefp.appdeclaration.service.dto;

import com.mefp.appdeclaration.entities.Ministere;
import com.mefp.appdeclaration.entities.Region;
import com.mefp.appdeclaration.entities.Structure;

public class StructureDTO {

    private Long id;
    private String libelle;
    private String sigle;
//    private Integer niveau;
//    private String description;
//    private String type;
//    private boolean active;
//    private String telephone;
//    private String emailResp;
//    private String emailStruct;
//
//    private Structure parent;
//
//    private Ministere ministere;
    private Region region;

    public StructureDTO() {
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
