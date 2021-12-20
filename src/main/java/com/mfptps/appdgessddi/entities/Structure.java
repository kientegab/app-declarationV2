package com.mfptps.appdgessddi.entities;

import com.mfptps.appdgessddi.enums.TypeStructure;
import com.mfptps.appdgessddi.enums.convertes.TypeStructureConverter;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "structure")
@SQLDelete(sql = "UPDATE structure SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@FilterDef(
        name = "deletedFilter",
        parameters = @ParamDef(name = "isDeleted", type = "boolean")
)
@Filter(
        name = "deletedFilter",
        condition = "deleted = :isDeleted"
)
public class Structure extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String libelle;
    private String sigle;//added 22112021
    private Integer niveau;//added 20122021
    private String description;
    @Column(nullable = false, length = 1)
    @Convert(converter = TypeStructureConverter.class)
    private TypeStructure type;
    private String statut;//if Structure always exists or no (20112021)
    private String telephone;
    private String emailResp;
    private String emailStruct;
    @ManyToOne
    @JoinColumn(nullable = true)
    private Structure parent;

    public Structure() {

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

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public Integer getNiveau() {
        return niveau;
    }

    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }

    public TypeStructure getType() {
        return type;
    }

    public void setType(TypeStructure type) {
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

    public Structure getParent() {
        return parent;
    }

    public void setParent(Structure parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Structure{"
                + "id=" + id
                + ", libelle='" + libelle + '\''
                + ", sigle='" + sigle + '\''
                + ", niveau='" + niveau + '\''
                + ", description='" + description + '\''
                + ", type='" + type + '\''
                + ", statut='" + statut + '\''
                + ", telephone=" + telephone
                + ", emailResp='" + emailResp + '\''
                + ", emailStruct='" + emailStruct + '\''
                + ", parent=" + parent
                + '}';
    }
}
