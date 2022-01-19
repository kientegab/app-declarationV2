/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.service.impl;

import com.mfptps.appdgessddi.entities.Action;
import com.mfptps.appdgessddi.entities.Ministere;
import com.mfptps.appdgessddi.entities.Programme;
import com.mfptps.appdgessddi.entities.Structure;
import com.mfptps.appdgessddi.enums.TypeStructure;
import com.mfptps.appdgessddi.repositories.ActionRepository;
import com.mfptps.appdgessddi.repositories.ExerciceRepository;
import com.mfptps.appdgessddi.repositories.MinistereStructureRepository;
import com.mfptps.appdgessddi.repositories.ObjectifRepository;
import com.mfptps.appdgessddi.repositories.ProgrammationRepository;
import com.mfptps.appdgessddi.repositories.ProgrammeRepository;
import com.mfptps.appdgessddi.repositories.QueryManagerRepository;
import com.mfptps.appdgessddi.repositories.StructureRepository;
import com.mfptps.appdgessddi.repositories.TacheRepository;
import com.mfptps.appdgessddi.service.CommentaireService;
import com.mfptps.appdgessddi.service.EvaluationService;
import com.mfptps.appdgessddi.service.StatisticParameterService;
import com.mfptps.appdgessddi.service.dto.statisticresponses.CountStructureGroupByType;
import com.mfptps.appdgessddi.service.mapper.ProgrammationMapper;
import com.mfptps.appdgessddi.service.reportentities.ProgrammeDataRE;
import com.mfptps.appdgessddi.service.reportentities.ProgrammeRE;
import com.mfptps.appdgessddi.service.reportentities.ReportUtil;
import com.mfptps.appdgessddi.service.reportentities.ViewGlobale;
import com.mfptps.appdgessddi.utils.AppUtil;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Service;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */

@Slf4j
@Service
public class StatisticParameterServiceImpl implements StatisticParameterService {
    
    private final MinistereStructureRepository ministereStructureRepository;
    
    private final ProgrammationRepository programmationRepository;
    private final TacheRepository tacheRepository;
    private final ExerciceRepository exerciceRepository;
    private final ObjectifRepository objectifRepository; 
    private final StructureRepository structureRepository;
    private final EvaluationService evaluationService;
    private final CommentaireService commentaireService;
    private final ActionRepository actionRepository;
    private final ProgrammeRepository programmeRepository; 
    
    private final ProgrammationMapper programmationMapper;
    
    private final QueryManagerRepository query;
    
    public StatisticParameterServiceImpl(ProgrammationRepository programmationRepository,
            ExerciceRepository exerciceRepository,
            TacheRepository tacheRepository,
            ObjectifRepository objectifRepository,
            MinistereStructureRepository ministereStructureRepository,
            StructureRepository structureRepository,
            EvaluationService evaluationService,
            CommentaireService commentaireService,
            ActionRepository actionRepository,
            ProgrammeRepository programmeRepository,
            QueryManagerRepository query,
            ProgrammationMapper programmationMapper) {
        
        this.programmationRepository = programmationRepository;
        this.tacheRepository = tacheRepository;
        this.exerciceRepository = exerciceRepository;
        this.objectifRepository = objectifRepository;
        this.ministereStructureRepository = ministereStructureRepository;
        this.structureRepository = structureRepository;
        this.evaluationService = evaluationService;
        this.commentaireService = commentaireService;
        this.programmationMapper = programmationMapper;
        this.actionRepository = actionRepository;
        this.programmeRepository = programmeRepository;
        this.query  = query;
    }
    
    @Override
    public long nbStructuresByMinistere(long ministereId) {
        return ministereStructureRepository.countStructureByMinistere(ministereId);
    }
    
    @Override
    public List<CountStructureGroupByType> nbStructuresByGroupType(long ministereId) {
        return ministereStructureRepository.countStructureByMinistereAndByGroupType(ministereId);
    }
    
//    @Override
//    public void imprimerProgrammeActivites(Long ministereId, Long structureId, long exerciceId, long currentStructureId, OutputStream outputStream) {
//        
//        try {
//            // chargement du ministère concerné
//            Ministere ministere = this.ministereStructureRepository.findByStructureIdAndStatutIsTrue(structureId).get().getMinistere();
//            log.info("________________ MinistereLib : {}", ministere);
//            
//            // structure génératrice du document
//            Structure currentStructure = this.structureRepository.getById(currentStructureId);
//           
//            Structure structureParent = new Structure();
//            if (currentStructure.getParent() != null) {
//                structureParent = this.structureRepository.findById(currentStructure.getParent().getId()).get(); 
//            }
//             
//            // chargement du logo
//            InputStream logoStream = AppUtil.getAppDefaultLogo();
//
//            // le titre du rapport
//            String titre = "PROGRAMME D'ACTIVITES";
//            
//            // Conteneurs intermédiaires utilisés pour construire les données
//            List<Programme> allPrograms = new ArrayList<>();
//            
//            // conteneurs de données à imprimer
//            List<ProgrammeDataRE> mainProgramData = new ArrayList<>();
//            
//            List<ProgrammeRE> programData = new ArrayList<>(); 
//            
//            // conteneur des structures
//            List<Structure> allStructures = new ArrayList<>();
//             
//            // cas de l'ensemble des structures
//            if(structureId == null){
//                allStructures = this.ministereStructureRepository.allNonInternalStructureByMinistere(ministereId, TypeStructure.INTERNE); 
//                // cas d'une structure particulière
//            }else{
//                Structure structure = this.structureRepository.findById(structureId).get();
//                allStructures.add(structure);
//            }
//            
//            // parcourir la liste des structures pour récuperer les programmes concernés par la structure ou le ministère
//            
//            for(Structure struct: allStructures){
//                
//                // recherche des actions liées à la structure sur l'exercice en cours 
//                List<Action> allActions = this.actionRepository.findActionsByStructureAndExercice(struct.getId(), exerciceId);
//                
//                for(Action act : allActions){
//                    // Recherche des programmes liées à chaque action
//                    List<Programme> progs = this.programmeRepository.findProgrammeByAction(act.getId());
//                    for(Programme prog : progs){
//                        if(!allPrograms.contains(prog)){
//                            allPrograms.add(prog);
//                        }
//                    }
//                } 
//            }
// 
//            // Construction des objet pour impression
//            
//            for(Programme prog : allPrograms){ 
//               
//                // chercher tous les objectifs stratégiques
//                ProgrammeRE programmeRE = new ProgrammeRE();
//                programmeRE.setCodeProgramme(prog.getCode());
//                programmeRE.setLibelleProgramme(prog.getLibelle());
//                programmeRE.setStructureProgramme("");
//                
//                List<ObjectifStrategiqueRE> objectifStrategiqueData = new ArrayList<>(); 
//            
//                List<ObjectifOperationnelRE> objectifOperationnelData = new ArrayList<>();
//
//                //List<ActiviteRE> activiteData = new ArrayList<>();
//                
//                List<ActionRE> actionData = new ArrayList<>();
//                
//                List<ObjectifStrategiqueRE> objStrData = this.objectifRepository.constructActionREByProgramme(prog.getId());
//                //programmeRE.setObjectifStrategiqueREs(objStrData);
//                
//                for(ObjectifStrategiqueRE ostr : objStrData){
//                    
//                    List<ActionRE> actData = this.actionRepository.constructActionREREByObjectif(ostr.getId());
//                    
//                    for(ActionRE act : actData){
//                        
//                        List<ObjectifOperationnelRE> listObjOp = this.objectifRepository.constructObjectifOperationnelREByAction(act.getId());
//                        
//                        for(ObjectifOperationnelRE obOp : listObjOp){
//                            
//                            List<ActiviteRE> listActivite = this.programmationRepository.constructActiviteRE(exerciceId, obOp.getId());
//                            
//                            obOp.setActiviteREs(listActivite); 
//                            objectifOperationnelData.add(obOp);
//                        }
//                        
//                        act.setObjectifOperationnelREs(objectifOperationnelData);
//                        actionData.add(act);
//                    }
//                    
//                    ostr.setActionREs(actionData);
//                    objectifStrategiqueData.add(ostr);
//                }
//                
//                programmeRE.setObjectifStrategiqueREs(objectifStrategiqueData);
//                programData.add(programmeRE);
//            }
//            
//            mainProgramData.add(new ProgrammeDataRE(ministere.getLibelle(), structureParent.getLibelle(), currentStructure.getLibelle(), currentStructure.getTelephone(), titre, logoStream, programData));
//
//            
//            InputStream reportStream = this.getClass().getResourceAsStream("/conteneur_principal.jasper");
//
//            Map<String, Object> parameters = new HashMap<>();
//
////            stats.add(dataFE);
//            JRDataSource datasource = new JRBeanCollectionDataSource(mainProgramData);
//
//            JasperReport japerReport = (JasperReport) JRLoader.loadObject(reportStream);
//
//            JasperPrint jasperPrint = JasperFillManager.fillReport(japerReport, parameters, datasource);
//
//            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
//
//        } catch (JRException e) {
//            log.error("Error when exporting data from", e);
//        }
//    }
    
    public void printProgrammeActivites(Long ministereId, Long structureId, long exerciceId, long currentStructureId, OutputStream outputStream) {
        
        try {
            // chargement du ministère concerné
            Ministere ministere = this.ministereStructureRepository.findByStructureIdAndStatutIsTrue(structureId).get().getMinistere();
            log.info("________________ MinistereLib : {}", ministere);
            
            // structure génératrice du document
            Structure currentStructure = this.structureRepository.getById(currentStructureId);
           
            Structure structureParent = new Structure();
            if (currentStructure.getParent() != null) {
                structureParent = this.structureRepository.findById(currentStructure.getParent().getId()).get(); 
            }
             
            // chargement du logo
            InputStream logoStream = AppUtil.getAppDefaultLogo();

            // le titre du rapport
            String titre = "PROGRAMME D'ACTIVITES";
            
            // Conteneurs intermédiaires utilisés pour construire les données
            List<Programme> allPrograms = new ArrayList<>();
            
            // conteneurs de données à imprimer
            List<ProgrammeDataRE> mainProgramData = new ArrayList<>();
            
            List<ProgrammeRE> programData = new ArrayList<>(); 
            
            // conteneur des structures
            List<Structure> allStructures = new ArrayList<>();
             
            // cas de l'ensemble des structures
            if(structureId == null){
                allStructures = this.ministereStructureRepository.allNonInternalStructureByMinistere(ministereId, TypeStructure.INTERNE); 
                // cas d'une structure particulière
            }else{
                Structure structure = this.structureRepository.findById(structureId).get();
                allStructures.add(structure);
            }
            
            // parcourir la liste des structures pour récuperer les programmes concernés par la structure ou le ministère
            
            for(Structure struct: allStructures){
                
                // recherche des actions liées à la structure sur l'exercice en cours 
                List<Action> allActions = this.actionRepository.findActionsByStructureAndExercice(struct.getId(), exerciceId);
                
                for(Action act : allActions){
                    // Recherche des programmes liées à chaque action
                    List<Programme> progs = this.programmeRepository.findProgrammeByAction(act.getId());
                    for(Programme prog : progs){
                        if(!allPrograms.contains(prog)){
                            allPrograms.add(prog);
                        }
                    }
                } 
            }
 
            // Construction des objet pour impression
            
            List<ViewGlobale> globalData = this.query.globalDataList();
             
            mainProgramData.add(ReportUtil.consctruct(globalData,ministere.getLibelle(), structureParent.getLibelle(), currentStructure.getLibelle(), currentStructure.getTelephone(), titre, logoStream));

            
            InputStream reportStream = this.getClass().getResourceAsStream("/conteneur_principal.jasper");

            Map<String, Object> parameters = new HashMap<>();

//            stats.add(dataFE);
            JRDataSource datasource = new JRBeanCollectionDataSource(mainProgramData);

            JasperReport japerReport = (JasperReport) JRLoader.loadObject(reportStream);

            JasperPrint jasperPrint = JasperFillManager.fillReport(japerReport, parameters, datasource);

            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

        } catch (JRException e) {
            log.error("Error when exporting data from", e);
        }
    }
}
