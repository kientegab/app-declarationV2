package com.mfptps.appdgessddi.entities;

import com.mfptps.appdgessddi.enums.ExerciceStatus;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "exercice")
@SQLDelete(sql = "UPDATE exercice SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@FilterDef(name = "deletedFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedFilter", condition = "deleted = :isDeleted")
public class Exercice extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    @Enumerated(EnumType.STRING)
    private ExerciceStatus statut;
    private LocalDate debut;
    private LocalDate fin;
    @ManyToOne
    private Observations observations;
    /*
     */
    @ManyToOne
    private Ponderation ponderation;

    public Exercice() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExerciceStatus getStatut() {
        return statut;
    }

    public void setStatut(ExerciceStatus statut) {
        this.statut = statut;
    }

    public LocalDate getDebut() {
        return debut;
    }

    public void setDebut(LocalDate debut) {
        this.debut = debut;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    public Observations getObservations() {
        return observations;
    }

    public void setObservations(Observations observations) {
        this.observations = observations;
    }

    public Ponderation getPonderation() {
        return ponderation;
    }

    public void setPonderation(Ponderation ponderation) {
        this.ponderation = ponderation;
    }

}
