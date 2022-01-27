/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mfptps.appdgessddi.service.reportentities;

import com.mfptps.appdgessddi.entities.Structure;
import com.mfptps.appdgessddi.repositories.QueryManagerRepository;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *
 * @author aboubacary
 */
@Slf4j
@Service
public class ReportUtil {

    private static QueryManagerRepository query;

    public ReportUtil(QueryManagerRepository query) {
        this.query = query;
    }

    public static boolean containsCodeProgramme(List<ProgrammeRE> programmes, final String codeProgramme) {
        return programmes.stream()
                .map(ProgrammeRE::getCodeProgramme)
                .filter(codeProgramme::equals).findFirst()
                .isPresent();
    }

    public static boolean containsCodeObjectifStrategique(List<ObjectifStrategiqueRE> objectifsStra, final String codeObjectifStra) {
        return objectifsStra.stream()
                .map(ObjectifStrategiqueRE::getCodeObjectifStra)
                .filter(codeObjectifStra::equals).findFirst()
                .isPresent();
    }

    public static boolean containsCodeAction(List<ActionRE> actions, final String codeAction) {
        return actions.stream()
                .map(ActionRE::getCodeAction)
                .filter(codeAction::equals).findFirst()
                .isPresent();
    }

    public static boolean containsCodeObjectifOperational(List<ObjectifOperationnelRE> objectifsop, final String codeObjectifOp) {
        return objectifsop.stream()
                .map(ObjectifOperationnelRE::getCodeObjectifOp)
                .filter(codeObjectifOp::equals).findFirst()
                .isPresent();
    }

    public static boolean containsCodeActivite(List<ActiviteRE> activites, final String codeProgrammation) {
        return activites.stream()
                .map(ActiviteRE::getCodeActivite)
                .filter(codeProgrammation::equals).findFirst()
                .isPresent();
    }

    /**
     * extraction des programmes dans le globalView
     *
     * @param globalData
     * @return
     */
    protected static List<ProgrammeRE> extractPrograms(List<ViewGlobale> globalData) {
        List<ProgrammeRE> data = new ArrayList<>();

        globalData.stream()
                .map(elmnt -> new ProgrammeRE(elmnt.getCodeProgramme(), elmnt.getLibelleProgramme(), "", null))
                .filter(prog -> (!containsCodeProgramme(data, prog.getCodeProgramme()))).forEachOrdered(prog -> {
            data.add(prog);
        });
        return data;
    }

    /**
     * extraction des objectifs stratégiques par programme
     *
     * @param program
     * @param globalData
     * @return
     */
    protected static List<ObjectifStrategiqueRE> extractStrategicPurpose(ProgrammeRE program, List<ViewGlobale> globalData) {
        List<ObjectifStrategiqueRE> data = new ArrayList<>();
        for (ViewGlobale elmt : globalData) {
            if (program.getCodeProgramme().equals(elmt.getCodeProgramme())) {
                ObjectifStrategiqueRE obj = new ObjectifStrategiqueRE(null, elmt.getCodeObjectifStra(), elmt.getLibelleObjectifStra(), "");
                if (!containsCodeObjectifStrategique(data, elmt.getCodeObjectifStra())) {
                    data.add(obj);
                }
            }
        }

        return data;
    }

    /**
     * extraction des Action par objectif stratégique
     *
     * @param objectif
     * @param globalData
     * @return
     */
    protected static List<ActionRE> extractAction(ObjectifStrategiqueRE objectif, List<ViewGlobale> globalData) {
        List<ActionRE> data = new ArrayList<>();
        for (ViewGlobale elmt : globalData) {
            if (objectif.getCodeObjectifStra().equals(elmt.getCodeObjectifStra())) {
                ActionRE action = new ActionRE(null, elmt.getCodeAction(), elmt.getLibelleAction());
                if (!containsCodeAction(data, elmt.getCodeAction())) {
                    data.add(action);
                }
            }
        }
        return data;
    }

    /**
     * extraction des Objectifs opérationnels par action
     *
     * @param action
     * @param globalData
     * @return
     */
    protected static List<ObjectifOperationnelRE> extractOperationalPurpose(ActionRE action, List<ViewGlobale> globalData) {
        List<ObjectifOperationnelRE> data = new ArrayList<>();
        for (ViewGlobale elmt : globalData) {
            if (action.getCodeAction().equals(elmt.getCodeAction())) {
                ObjectifOperationnelRE obj = new ObjectifOperationnelRE(null, elmt.getCodeObjectifOpe(), elmt.getLibelleObjectifOpe());
                if (!containsCodeObjectifOperational(data, elmt.getCodeObjectifOpe())) {
                    data.add(obj);
                }
            }
        }
        return data;
    }

    /**
     * extraction des Activités / Programmations par objectifs opérationnels
     *
     * @param objectif
     * @param globalData
     * @return
     */
    protected static List<ActiviteRE> extractActivity(ObjectifOperationnelRE objectif, List<ViewGlobale> globalData) {
        List<ProgrammationPhysiqueRE> programmationPhysiqueData = ReportUtil.query.programmationPhysiqueList();

        List<ActiviteRE> data = new ArrayList<>();
        for (ViewGlobale elmt : globalData) {
            if (objectif.getCodeObjectifOp().equals(elmt.getCodeObjectifOpe())) {
                ActiviteRE activite = new ActiviteRE(elmt.getCodeProgrammation(), elmt.getLibelleActivite(), "", elmt.getCibleProgrammation(), elmt.getCoutPrevisionnel(), null, null, null, null, null, null, elmt.getLibelleFinancement(), elmt.getSigleStructure());
                for (ProgrammationPhysiqueRE physique : programmationPhysiqueData) {
                    if (elmt.getId() == physique.getIdProgrammation()) {
                        switch (physique.getLibellePeriode()) {
                            case "T1":
                                activite.setT1("X");
                                break;
                            case "T2":
                                activite.setT2("X");
                                break;
                            case "T3":
                                activite.setT3("X");
                                break;
                            case "T4":
                                activite.setT4("X");
                                break;
                            case "S1":
                                activite.setS1("X");
                                break;
                            case "S2":
                                activite.setS2("X");
                                break;
                            default:
                                System.out.println("_______________________ default case...");
                        }
                    }
                }
                if (!containsCodeActivite(data, elmt.getCodeProgrammation())) {
                    data.add(activite);
                }
            }
        }

        return data;
    }

    public static List<ProgrammeDataRE> consctruct(List<ViewGlobale> globalData, String ministereLibelle, String structureParentLibelle, String currentStructureLibelle, String currentStructureTelephone, String titre, InputStream logoStream) {
        //ministere.getLibelle(), structureParent.getLibelle(), currentStructure.getLibelle(), currentStructure.getTelephone(), titre, logoStream

        // Conteneurs intermédiaires utilisés pour construire les données
        List<ProgrammeRE> allPrograms = extractPrograms(globalData);

        // conteneurs de données à imprimer
        List<ProgrammeDataRE> mainProgramData = new ArrayList<>();

        List<ProgrammeRE> programData = new ArrayList<>();

        // conteneur des structures
        List<Structure> allStructures = new ArrayList<>();

        // Construction des objet pour impression
        for (ProgrammeRE programmeRE : allPrograms) {
            programData = new ArrayList<>();
            // chercher tous les objectifs stratégiques 
            List<ObjectifStrategiqueRE> objectifStrategiqueData = new ArrayList<>();

            List<ObjectifOperationnelRE> objectifOperationnelData = new ArrayList<>();

            //List<ActiviteRE> activiteData = new ArrayList<>();
            List<ActionRE> actionData = new ArrayList<>();

            List<ObjectifStrategiqueRE> objStrData = extractStrategicPurpose(programmeRE, globalData);

            //programmeRE.setObjectifStrategiqueREs(objStrData);
            for (ObjectifStrategiqueRE ostr : objStrData) {
                List<ActionRE> actData = extractAction(ostr, globalData);

                for (ActionRE act : actData) {
                    List<ObjectifOperationnelRE> listObjOp = extractOperationalPurpose(act, globalData);

                    for (ObjectifOperationnelRE obOp : listObjOp) {
                        List<ActiviteRE> listActivite = extractActivity(obOp, globalData);

                        obOp.setActiviteREs(listActivite);
                        objectifOperationnelData.add(obOp);
                    }

                    act.setObjectifOperationnelREs(objectifOperationnelData);
                    actionData.add(act);
                }

                ostr.setActionREs(actionData);
                objectifStrategiqueData.add(ostr);
            }

            programmeRE.setObjectifStrategiqueREs(objectifStrategiqueData);
            programData.add(programmeRE);

            mainProgramData.add(new ProgrammeDataRE(ministereLibelle, structureParentLibelle, currentStructureLibelle, currentStructureTelephone, titre, logoStream, programData));

        }

        return mainProgramData;
    }

}
