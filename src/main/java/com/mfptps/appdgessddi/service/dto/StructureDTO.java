package com.mfptps.appdgessddi.service.dto;

import com.mfptps.appdgessddi.entities.Ministere;
import com.mfptps.appdgessddi.entities.Structure;

public class StructureDTO {

    private Long id;
    private String libelle;
    private String description;
    private String type;
    private String statut;
    private String telephone;
    private String emailResp;
    private String emailStruct;

    private Structure structure;

    private Ministere ministere;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
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

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public Ministere getMinistere() {
        return ministere;
    }

    public void setMinistere(Ministere ministere) {
        this.ministere = ministere;
    }

}
