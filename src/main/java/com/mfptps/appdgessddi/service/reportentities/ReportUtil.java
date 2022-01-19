/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mfptps.appdgessddi.service.reportentities;

import com.mfptps.appdgessddi.entities.Structure;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aboubacary
 */
public class ReportUtil {
    
    /**
     * extraction des programmes dans le globalView
     * @param globalData
     * @return 
     */
    protected static List<ProgrammeRE> extractPrograms(List<ViewGlobale> globalData){ 
        List<ProgrammeRE> data = new ArrayList<>(); 
        globalData.stream().map(elmnt -> new ProgrammeRE(elmnt.getCodeProgramme(),elmnt.getLibelleProgramme(),"", null)).filter(prog -> (!data.contains(prog))).forEachOrdered(prog -> {
            data.add(prog);
        }); 
        return data;
    }
    
    /**
     * extraction des objectifs stratégiques par programme
     * @param program
     * @param globalData
     * @return 
     */
    protected static List<ObjectifStrategiqueRE> extractStrategicPurpose(ProgrammeRE program, List<ViewGlobale> globalData){
        List<ObjectifStrategiqueRE> data = new ArrayList<>(); 
        for(ViewGlobale elmt : globalData){
            if(program.getCodeProgramme().equals(elmt.getCodeProgramme())){
                ObjectifStrategiqueRE obj = new ObjectifStrategiqueRE(null, elmt.getCodeObjectifStra(), elmt.getLibelleObjectifStra(), "");
                if(!data.contains(obj)){
                    data.add(obj);
                }
            }
            
        }
        
        return data;
    }
    
    /**
     * extraction des Action par objectif stratégique
     * @param objectif
     * @param globalData
     * @return 
     */
    protected static List<ActionRE> extractAction(ObjectifStrategiqueRE objectif, List<ViewGlobale> globalData){
        List<ActionRE> data = new ArrayList<>(); 
        for(ViewGlobale elmt : globalData){
            if(objectif.getCodeObjectifStra().equals(elmt.getCodeObjectifStra())){
                ActionRE action = new ActionRE(null, elmt.getCodeAction(), elmt.getLibelleAction());
                if(!data.contains(action)){
                    data.add(action);
                }
            } 
        }
        return data;
    }
    
    /**
     * extraction des Objectifs opérationnels par action 
     * @param action
     * @param globalData
     * @return 
     */
    protected static List<ObjectifOperationnelRE> extractOperationalPurpose(ActionRE action, List<ViewGlobale> globalData){
        List<ObjectifOperationnelRE> data = new ArrayList<>(); 
        for(ViewGlobale elmt : globalData){
            if(action.getCodeAction().equals(elmt.getCodeAction())){
                ObjectifOperationnelRE obj = new ObjectifOperationnelRE(null, elmt.getCodeObjectifOpe(), elmt.getLibelleObjectifOpe());
                if(!data.contains(obj)){
                    data.add(obj);
                }
            } 
        } 
        return data;
    }
    
    /**
     * extraction des Activités / Programmations  par objectifs opérationnels 
     * @param objectif
     * @param globalData
     * @return 
     */
    protected static List<ActiviteRE> extractActivity(ObjectifOperationnelRE objectif, List<ViewGlobale> globalData){
        List<ActiviteRE> data = new ArrayList<>(); 
        for(ViewGlobale elmt : globalData){
            if(objectif.getCodeObjectifOp().equals(elmt.getCodeObjectifOpe())){
                ActiviteRE activite = new ActiviteRE(elmt.getCodeProgrammation(),elmt.getLibelleActivite(), "", elmt.getCibleProgrammation(),elmt.getCoutPrevisionnel() , elmt.getLibelleFinancement(), "");
                if(!data.contains(activite)){
                    data.add(activite);
                }
            } 
        } 
        return data;
    }
    
    
    public static ProgrammeDataRE consctruct(List<ViewGlobale> globalData, String ministereLibelle,String structureParentLibelle,String currentStructureLibelle, String currentStructureTelephone, String titre, InputStream logoStream){
        //ministere.getLibelle(), structureParent.getLibelle(), currentStructure.getLibelle(), currentStructure.getTelephone(), titre, logoStream
        
        // Conteneurs intermédiaires utilisés pour construire les données
        List<ProgrammeRE> allPrograms = extractPrograms(globalData);  

        // conteneurs de données à imprimer
        List<ProgrammeDataRE> mainProgramData = new ArrayList<>();

        List<ProgrammeRE> programData = new ArrayList<>();

        // conteneur des structures
        List<Structure> allStructures = new ArrayList<>();
        
        // Construction des objet pour impression
            
        for(ProgrammeRE programmeRE : allPrograms){ 

            // chercher tous les objectifs stratégiques 

            List<ObjectifStrategiqueRE> objectifStrategiqueData = new ArrayList<>(); 

            List<ObjectifOperationnelRE> objectifOperationnelData = new ArrayList<>();

            //List<ActiviteRE> activiteData = new ArrayList<>();

            List<ActionRE> actionData = new ArrayList<>();

            List<ObjectifStrategiqueRE> objStrData = extractStrategicPurpose(programmeRE, globalData);
            //programmeRE.setObjectifStrategiqueREs(objStrData);

            for(ObjectifStrategiqueRE ostr : objStrData){

                List<ActionRE> actData = extractAction(ostr, globalData);

                for(ActionRE act : actData){

                    List<ObjectifOperationnelRE> listObjOp = extractOperationalPurpose(act, globalData);

                    for(ObjectifOperationnelRE obOp : listObjOp){

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
        } 
        
        return new ProgrammeDataRE(ministereLibelle, structureParentLibelle, currentStructureLibelle, currentStructureTelephone, titre, logoStream, programData);
    }
    
    
    
}
