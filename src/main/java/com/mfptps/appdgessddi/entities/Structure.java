package com.mfptps.appdgessddi.entities;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "structure")
@SQLDelete(sql="UPDATE structure SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@FilterDef(
        name = "deletedFilter",
        parameters = @ParamDef(name = "isDeleted", type = "boolean")
)
@Filter(
        name = "deletedFilter",
        condition = "deleted = :isDeleted"
)
public class Structure  extends CommonEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle ;
    private String description ;
    private String type ;
    private String statut ;
    private int telephone ;
    private String emailResp ;
    private String emailStruct ;
    @ManyToOne
    @JoinColumn(name = "structure_id",nullable = true)
    private Structure structure ;


    public Structure(){

    }

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

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    @Override
    public String toString() {
        return "Structure{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", statut='" + statut + '\'' +
                ", telephone=" + telephone +
                ", emailResp='" + emailResp + '\'' +
                ", emailStruct='" + emailStruct + '\'' +
                ", structure=" + structure +
                '}';
    }
}
