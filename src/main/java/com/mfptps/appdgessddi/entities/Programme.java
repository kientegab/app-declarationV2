/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.entities;

import com.mfptps.appdgessddi.enums.BaseStatus;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * This is the Programme budgetaire entity
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "programme")
@SQLDelete(sql = "UPDATE programme SET deleted = true WHERE id=?") // to manage softDeletion
@Where(clause = "deleted = false")
@FilterDef(name = "deletedFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedFilter", condition = "deleted = :isDeleted")
public class Programme extends CommonEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id_programme;

        @Column(nullable = false, length = 2)
        private String code_programme;

        @Column(nullable = false, updatable = true)
        private String libelle_programme;

        private String description_programme;

        @Column(name = "programme_status")
        private BaseStatus statut_programme;

        private Date debut_programme;

        private Date fin_programme;

        private String details_programme;

    public Programme() {
    }

    public Long getId_programme() {
        return id_programme;
    }

    public void setId_programme(Long id_programme) {
        this.id_programme = id_programme;
    }

    public String getCode_programme() {
        return code_programme;
    }

    public void setCode_programme(String code_programme) {
        this.code_programme = code_programme;
    }

    public String getLibelle_programme() {
        return libelle_programme;
    }

    public void setLibelle_programme(String libelle_programme) {
        this.libelle_programme = libelle_programme;
    }

    public String getDescription_programme() {
        return description_programme;
    }

    public void setDescription_programme(String description_programme) {
        this.description_programme = description_programme;
    }

    public BaseStatus getStatut_programme() {
        return statut_programme;
    }

    public void setStatut_programme(BaseStatus statut_programme) {
        this.statut_programme = statut_programme;
    }

    public Date getDebut_programme() {
        return debut_programme;
    }

    public void setDebut_programme(Date debut_programme) {
        this.debut_programme = debut_programme;
    }

    public Date getFin_programme() {
        return fin_programme;
    }

    public void setFin_programme(Date fin_programme) {
        this.fin_programme = fin_programme;
    }

    public String getDetails_programme() {
        return details_programme;
    }

    public void setDetails_programme(String details_programme) {
        this.details_programme = details_programme;
    }
        
        
}
