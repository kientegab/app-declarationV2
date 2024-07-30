/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mefp.appdeclaration.utils;

import java.io.InputStream;
import java.util.Date;
import javax.validation.constraints.Size;

import com.mefp.appdeclaration.service.CustomException;
import com.mefp.appdeclaration.web.vm.ManagedAgentVM;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Slf4j
public class AppUtil {

    private static InputStream logoStream;

    @Size(min = ManagedAgentVM.PASSWORD_MIN_LENGTH, max = ManagedAgentVM.PASSWORD_MAX_LENGTH)
    public static final String DEFAULT_PASSWORD = "12345678";

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
    public static final String BASIC_MINISTERE_SIGLE = "MFP - TEST";

   public static final String BASIC_REGION_LABEL = "REGION - TEST";
   public static final String BASIC_REGION_CODE = "00-TEST";
   public static final Long BASIC_REGION_ID = 1L;
    public static final String BASIC_STRUCTURE_LABEL = "Structure de Test";
    public static final String BASIC_STRUCTURE_SIGLE = "STRUC - TEST";
    public static final Long BASIC_STRUCTURE_ID = 1L;



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
     * ALL ROLE/PRIVILEGES OF USERS
     */
    public static final String FS = "ROLE_FOCAL_STRUCT";
    public static final String AGT_D = "ROLE_AGT_DGESS";
    public static final String DD = "ROLE_DIR_DGESS";
    public static final String RD = "ROLE_RESP_DGESS";
    public static final String RS = "ROLE_RESP_STRUCT";
    public static final String DM = "ROLE_DIRCAB_MIN";
    public static final String RM = "ROLE_RESP_MIN";
    public static final String SM = "ROLE_SG_MIN";
    public static final String AD = "ROLE_AGT_DDII";
    public static final String RDDII = "ROLE_RESP_DDII";
    public static final String ADMIN = "ROLE_ADMIN";
    public static final String DAF = "ROLE_DAF";
    public static final String DRH = "ROLE_DRH";
    public static final String USER = "ROLE_USER";

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
    // public static String codeGeneratorActivite(ActivitesRepository repository) {
    //     return "" + (repository.count() + 1L);
    // }

    /**
     * Generate a code of some programme budgetaire (which can extend over 10..4
     * years)
     *
     * @param repository
     * @return
     */
 
    /**
     * Fonction qui renvoie l'extension du futur ficher à créeer et le format du
     * contenu du fichier à renvoyer
     *
     * @param extension
     * @return
     */
    public static String[] constructFormatAndExtension(String extension) {
        String[] result = new String[2];

        switch (extension) {
            case "PDF":
                result[0] = "application/pdf";
                result[1] = ".pdf";
                break;
            case "Excel":
                result[0] = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
                result[1] = ".xlsx";
                break;
            case "Word":
                result[0] = "application/ms-word";
                result[1] = ".docx";
                break;
            default:
                result[0] = "application/pdf";
                result[1] = ".pdf";
                break;
        }

        return result;
    }

}

