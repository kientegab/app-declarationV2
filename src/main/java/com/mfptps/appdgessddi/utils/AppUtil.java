/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.utils;

import com.mfptps.appdgessddi.entities.Action;
import com.mfptps.appdgessddi.entities.Objectif;
import com.mfptps.appdgessddi.entities.Programmation;
import com.mfptps.appdgessddi.entities.Programme;
import com.mfptps.appdgessddi.enums.BaseStatus;
import com.mfptps.appdgessddi.enums.TypeObjectif;
import com.mfptps.appdgessddi.repositories.ActionRepository;
import com.mfptps.appdgessddi.repositories.ActivitesRepository;
import com.mfptps.appdgessddi.repositories.ObjectifRepository;
import com.mfptps.appdgessddi.repositories.ProgrammationRepository;
import com.mfptps.appdgessddi.repositories.ProgrammeRepository;
import com.mfptps.appdgessddi.service.CustomException;
import java.util.Date;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public class AppUtil {

    /**
     * TYPE OF STRUCTURE
     */
    public static final String STRUCTURE_CENTRALE = "CENTRALE";
    public static final String STRUCTURE_DECONCENTREE = "DECONCENTREE";
    public static final String STRUCTURE_RATTACHEE = "RATTACHEE";
    public static final String STRUCTURE_DE_MISSION = "DE_MISSION";
    public static final String STRUCTURE_INTERNE = "INTERNE";

    /**
     * BASIC MINISTERE AND STRUCTURE DATAS
     */
    public static final String BASIC_MINISTERE_CODE = "00-TEST";
    public static final String BASIC_MINISTERE_LABEL = "Ministere en charge de la fonction publique - TEST";
    public static final String BASIC_MINISTERE_SIGLE = "MFP - TEST";

    public static final String BASIC_STRUCTURE_LABEL = "Structure de Test";
    public static final String BASIC_STRUCTURE_SIGLE = "STRUC - TEST";
    public static final String BASIC_STRUCTURE_TELEPHONE = "00.00.00.00";

    /**
     * TYPE OF OBJECTIF
     */
    public static final String OBJECTIF_STRATEGIQUE = "STRATEGIQUE";
    public static final String OBJECTIF_OPERATIONNEL = "OPERATIONNEL";

    /**
     * TYPE OF INDICATEUR
     */
    public static final String INDICATEUR_IMPACT = "IMPACT";
    public static final String INDICATEUR_EFFET = "EFFET";

    /**
     * TYPE OF EXERCICE OR BASE STATUS
     */
    public static final String EN_COURS = "EN_COURS";
    public static final String EN_ATTENTE = "EN_ATTENTE";
    public static final String CLOS = "CLOS";
    public static final String ANNULE = "ANNULE";
    public static final String TERMINEE = "TERMINEE";
    public static final String PAS_COMMENCEE = "PAS_COMMENCEE";

    /**
     *
     * @param debut : startDate
     * @param fin : endDate
     */
    public static void checkDebutBeforeFin(Date debut, Date fin) {
        if (debut.after(fin)) {
            throw new CustomException("Veuillez renseigner une date de debut antérieure à la date de fin.");
        }
    }

    /**
     * Generate a code of some activite
     *
     * @param repository
     * @return
     */
    public static String codeGeneratorActivite(ActivitesRepository repository) {
        return "" + (repository.count() + 1L);
    }

    /**
     * Generate a code of some programme budgetaire (which can extend over 10..4
     * years)
     *
     * @param repository
     * @return
     */
    public static String codeGeneratorProgramme(ProgrammeRepository repository) {
        return "" + (repository.count() + 1L);
    }

    /**
     * Generate a code of some objectif (strategique or operationnel)
     *
     * @param repository
     * @param programmeRepository
     * @param actionRepository
     * @param objectif
     * @return
     */
    public static String codeGeneratorObjectif(ObjectifRepository repository, ProgrammeRepository programmeRepository, ActionRepository actionRepository, Objectif objectif) {
        String codeGenerated = "";
        if (objectif.getType().getLabel().equals(OBJECTIF_STRATEGIQUE)) {
            Programme programme = programmeRepository.findById(objectif.getProgramme().getId())
                    .orElseThrow(() -> new CustomException("Veuillez rattacher le programme budgétaire approprié à cet objectif."));
            long count = repository.countObjectifStrategique(programme.getId(), BaseStatus.EN_COURS, TypeObjectif.STRATEGIQUE);
            codeGenerated = "" + programme.getCode() + "." + (count + 1L);
        } else if (objectif.getType().getLabel().equals(OBJECTIF_OPERATIONNEL)) {
            if (objectif.getParent() != null) {//subObjectifOperationnel
                Objectif parent = repository.findById(objectif.getParent().getId())
                        .orElseThrow(() -> new CustomException("Sous objectif mal référencé."));
                long count = repository.countObjectifSubOperationnel(parent.getId(), TypeObjectif.OPERATIONNEL);
                codeGenerated = "" + parent.getCode() + "." + (count + 1L);
                return codeGenerated;
            }
            Action action = actionRepository.findById(objectif.getAction().getId())
                    .orElseThrow(() -> new CustomException("Veuillez rattacher l'action appropriée à cet objectif."));
            long count = repository.countObjectifOperationnel(action.getId(), TypeObjectif.OPERATIONNEL);
            codeGenerated = "" + action.getCode() + "." + (count + 1L);
        }
        return codeGenerated;
    }

    /**
     * Generate a code of some Action
     *
     * @param repository
     * @param objectifRepository
     * @param action
     * @return
     */
    public static String codeGeneratorAction(ActionRepository repository, ObjectifRepository objectifRepository, Action action) {
        String codeGenerated = "";
        Objectif objectif = objectifRepository.findByIdAndType(action.getObjectif().getId(), TypeObjectif.STRATEGIQUE)
                .orElseThrow(() -> new CustomException("Veuillez rattacher l'objectif approprié à cette action."));
        codeGenerated = "" + objectif.getCode() + "." + (repository.count() + 1L);
        return codeGenerated;
    }

    /**
     * Generate a code of some Programmation
     *
     * @param repository
     * @param objectifRepository
     * @param programmation
     * @return
     */
    public static String codeGeneratorProgrammation(ProgrammationRepository repository, ObjectifRepository objectifRepository, Programmation programmation) {
        String codeGenerated = "";
        Objectif objectif = objectifRepository.findByIdAndType(programmation.getObjectif().getId(), TypeObjectif.OPERATIONNEL)
                .orElseThrow(() -> new CustomException("Veuillez rattacher l'objectif approprié à cette programmation."));
        long count = repository.countProgrammationByStrucutreAndObjectif(programmation.getStructure().getId(), objectif.getId());
        codeGenerated = "" + objectif.getCode() + "." + (count + 1L);
        return codeGenerated;
    }
}
