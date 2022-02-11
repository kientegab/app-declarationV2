/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mfptps.appdgessddi.entities.Activites;
import com.mfptps.appdgessddi.entities.Exercice;
import com.mfptps.appdgessddi.entities.Objectif;
import com.mfptps.appdgessddi.entities.Projet;
import com.mfptps.appdgessddi.entities.SourceFinancement;
import com.mfptps.appdgessddi.entities.Structure;
import com.mfptps.appdgessddi.entities.Tache;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Ce DTO est construit et renvoyer pour l'evaluation d'une programmation
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Getter
@Setter
public class ProgrammationForEvaluationDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String code;

    private double coutPrevisionnel;

    private double coutReel;

    private boolean estProgramme;

    private boolean singleton;

    private double cible;

    private double taux;

    private double valeurActuelle;

    private double tauxActuel;

    private String resultatsAttendus;

    private String resultatsAtteints;

    private String observations;

    private boolean validationInitial;

    private boolean validationInterne;

    private boolean validationFinal;

    private SourceFinancement sourceFinancement;

    private List<Tache> taches;

    private Activites activite;

    private Projet projet;

    @JsonIgnoreProperties(value = {"parent"})
    private Structure structure;

    private Exercice exercice;

    private Objectif objectif;
}
