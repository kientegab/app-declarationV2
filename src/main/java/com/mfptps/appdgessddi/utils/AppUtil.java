/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.utils;

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
            throw new CustomException("Veuillez renseigner une date debut antérieure à la date de fin.");
        }
    }
}
