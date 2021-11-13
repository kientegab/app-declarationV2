package com.mfptps.appdgessddi.service.dto;

public class ActivitesDTO {

    private String code;
    private String description;
    private String libelle;
    private String status;
    private Long typeActivitesId;

    public Long getTypeActivitesId() {
        return typeActivitesId;
    }

    public void setTypeActivitesId(Long typeActivitesId) {
        this.typeActivitesId = typeActivitesId;
    }

    public ActivitesDTO() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ActivitesDTO [code=" + code + ", description=" + description + ", libelle=" + libelle + "]";
    }

}
