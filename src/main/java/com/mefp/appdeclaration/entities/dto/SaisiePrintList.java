package com.mefp.appdeclaration.entities.dto;

import lombok.Data;

import java.util.Date;
@Data
public class SaisiePrintList {
    private String  dateSaisie;
    private String numSaisie;
    private String lieuSaisie;
    private String itineraire;
    private String nature;
    private String procede;
    private String poids;
    private String valeur;

}
