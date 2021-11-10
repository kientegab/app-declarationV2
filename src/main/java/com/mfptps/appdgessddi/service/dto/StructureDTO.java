package com.mfptps.appdgessddi.service.dto;

import com.mfptps.appdgessddi.entities.Structure;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class StructureDTO {

    private String libelle ;
    private String descriptions ;
    private String type ;
    private String statut ;
    private int telephone ;
    private String emailResp ;
    private String emailStruct ;

    private Long structureId ;

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getEmailResp() {
        return emailResp;
    }

    public void setEmailResp(String emailResp) {
        this.emailResp = emailResp;
    }

    public String getEmailStruct() {
        return emailStruct;
    }

    public void setEmailStruct(String emailStruct) {
        this.emailStruct = emailStruct;
    }

    public Long getStructureId() {
        return structureId;
    }

    public void setStructureId(Long structureId) {
        this.structureId = structureId;
    }
}
