package com.mefp.appdeclaration.service.dto;

import com.mefp.appdeclaration.entities.Destination;
import com.mefp.appdeclaration.entities.FicheSaisie;
import com.mefp.appdeclaration.entities.Interpele;


public class FicheSaisieCompDTO {
    private FicheSaisie fiche;
    private Destination destination;
    private Interpele interpele;

    public FicheSaisieCompDTO() {
    }

    public void setFiche(FicheSaisie fiche) {
        this.fiche = fiche;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public void setInterpele(Interpele interpele) {
        this.interpele = interpele;
    }

    public FicheSaisie getFiche() {
        return fiche;
    }

    public Destination getDestination() {
        return destination;
    }

    public Interpele getInterpele() {
        return interpele;
    }
}
