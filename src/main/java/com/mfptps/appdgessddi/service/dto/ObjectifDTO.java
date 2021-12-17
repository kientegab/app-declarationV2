package com.mfptps.appdgessddi.service.dto;

import com.mfptps.appdgessddi.entities.Action;
import com.mfptps.appdgessddi.entities.Objectif;
import com.mfptps.appdgessddi.enums.TypeObjectif;

public class ObjectifDTO {

    private String code;

    private String libelle;

    private TypeObjectif type;

    private Objectif parent;

    private Action action;

    public ObjectifDTO() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public TypeObjectif getType() {
        return type;
    }

    public void setType(TypeObjectif type) {
        this.type = type;
    }

    public Objectif getParent() {
        return parent;
    }

    public void setParent(Objectif parent) {
        this.parent = parent;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
