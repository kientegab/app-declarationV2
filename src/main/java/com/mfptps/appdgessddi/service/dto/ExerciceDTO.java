package com.mfptps.appdgessddi.service.dto;

import com.mfptps.appdgessddi.entities.Observations;
import com.mfptps.appdgessddi.entities.Ponderation;

import java.util.Date;

public class ExerciceDTO {

    private String description ;
    private String statut ;
    private Date debut ;
    private Date fin ;
    private Observations observations ;
    private Ponderation ponderation ;

    public ExerciceDTO() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
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
