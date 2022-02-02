/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.service.impl;

import com.mfptps.appdgessddi.entities.Exercice;
import com.mfptps.appdgessddi.entities.Ministere;
import com.mfptps.appdgessddi.entities.Programmation;
import com.mfptps.appdgessddi.entities.Programme;
import com.mfptps.appdgessddi.entities.Structure;
import com.mfptps.appdgessddi.entities.Tache;
import com.mfptps.appdgessddi.enums.ExerciceStatus;
import com.mfptps.appdgessddi.enums.TypeStructure;
import com.mfptps.appdgessddi.repositories.ExerciceRepository;
import com.mfptps.appdgessddi.repositories.MinistereStructureRepository;
import com.mfptps.appdgessddi.repositories.ObjectifRepository;
import com.mfptps.appdgessddi.repositories.ProgrammationRepository;
import com.mfptps.appdgessddi.repositories.QueryManagerRepository;
import com.mfptps.appdgessddi.repositories.StructureRepository;
import com.mfptps.appdgessddi.repositories.TacheRepository;
import com.mfptps.appdgessddi.security.SecurityUtils;
import com.mfptps.appdgessddi.service.CommentaireService;
import com.mfptps.appdgessddi.service.CustomException;
import com.mfptps.appdgessddi.service.EvaluationService;
import com.mfptps.appdgessddi.service.ProgrammationService;
import com.mfptps.appdgessddi.service.dto.CommentaireDTO;
import com.mfptps.appdgessddi.service.dto.PeriodesDTO;
import com.mfptps.appdgessddi.service.dto.ProgrammationDTO;
import com.mfptps.appdgessddi.service.mapper.MinistereStructureMapper;
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
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Slf4j
@Service
@Transactional
public class ProgrammationServiceImpl implements ProgrammationService {

    private final ProgrammationRepository programmationRepository;
    private final TacheRepository tacheRepository;
    private final ExerciceRepository exerciceRepository;
    private final ObjectifRepository objectifRepository;
    private final MinistereStructureRepository ministereStructureRepository;
    private final StructureRepository structureRepository;
    private final EvaluationService evaluationService;
    private final CommentaireService commentaireService;
    private final ProgrammationMapper programmationMapper;
    private final MinistereStructureMapper ministereStructureMapper;
    private final QueryManagerRepository query;

    public ProgrammationServiceImpl(ProgrammationRepository programmationRepository,
            ExerciceRepository exerciceRepository,
            TacheRepository tacheRepository,
            ObjectifRepository objectifRepository,
            MinistereStructureRepository ministereStructureRepository,
            StructureRepository structureRepository,
            EvaluationService evaluationService,
            CommentaireService commentaireService,
            ProgrammationMapper programmationMapper,
            MinistereStructureMapper ministereStructureMapper,
            QueryManagerRepository query) {
        this.programmationRepository = programmationRepository;
        this.tacheRepository = tacheRepository;
        this.exerciceRepository = exerciceRepository;
        this.objectifRepository = objectifRepository;
        this.ministereStructureRepository = ministereStructureRepository;
        this.structureRepository = structureRepository;
        this.evaluationService = evaluationService;
        this.commentaireService = commentaireService;
        this.programmationMapper = programmationMapper;
        this.ministereStructureMapper = ministereStructureMapper;
        this.query = query;

    }

    /**
     *
     * @param programmationDTO : DTO content Activite, Programmation, Projet,
     * SourceFinancement, and Tache(s) fields
     * @return
     */
    @Override
    @Transactional(rollbackFor = CustomException.class)
    public Programmation create(ProgrammationDTO programmationDTO) {
        Programmation programmationMapped = programmationMapper.toEntity(programmationDTO);
        log.debug("Sum of Ponderations = {} %", programmationMapped.checkPonderation());

//        if (!programmationDTO.isSingleton() && programmationMapped.checkValeur() != programmationDTO.getCible()) {
//            throw new CustomException("La somme des valeurs de vos taches n'atteint pas la cible (" + programmationDTO.getCible() + ") de l'activité programmée.");
//        }
        this.checkIfAllPeriodeNotFalse(programmationDTO.getPeriodes());
        String code = AppUtil.codeGeneratorProgrammation(programmationRepository, objectifRepository, programmationMapped);
        Exercice exerciceEnAttente = exerciceRepository.findByStatut(ExerciceStatus.EN_ATTENTE).orElseThrow(() -> new CustomException("Aucun exercice en attente."));
        programmationMapped.setExercice(exerciceEnAttente);
        programmationMapped.setCode(code);
        Programmation response = programmationRepository.save(programmationMapped);
        evaluationService.addEvaluation(programmationDTO.getPeriodes(), response);

        if (programmationDTO.isSingleton()) {//Activite with one Tache
            Tache tache = new Tache();
            tache.setValeur(programmationDTO.getCible());
            tache.setPonderation(100);
            tache.setLibelle(programmationDTO.getActivite().getLibelle());
            tache.setProgrammation(response);
            tacheRepository.save(tache);
        } else {//Activite with many Tache. Then we create those Taches linking ProgrammationId
            if (programmationMapped.checkPonderation() != 100) {
                throw new CustomException("La somme des ponderations de vos taches doit être égale à 100%.");
            }
            for (Tache tache : programmationDTO.getTaches()) {
                log.debug("Tache info {}", tache);
                tache.setProgrammation(response);
                tacheRepository.save(tache);
            }
        }
        return response;
    }

    @Override
    public Programmation update(Programmation programmation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param libelle : libelle of Activite
     * @return
     */
    @Override
    public Page<Programmation> get(Long structureId, String libelle, Pageable pageable) {
        return programmationRepository.findByLibelle(structureId, libelle, pageable);
    }

    /**
     *
     * @param id : id of Programmation
     * @return
     */
    @Override
    public Optional<Programmation> get(Long structureId, Long id) {
        return programmationRepository.findById(structureId, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Programmation> findAll(Long structureId, Pageable pageable) {
        return programmationRepository.findAll(structureId, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Programmation> findAllValided(Long structureId, Pageable pageable) {
        return programmationRepository.findAllValided(structureId, pageable);
    }

    /**
     *
     * @param structureId
     * @param programmationId
     */
    @Override
    public void delete(Long structureId, Long programmationId) {
        int count = programmationRepository.deleteById(structureId, programmationId);
        if (count <= 0) {
            throw new CustomException("Programmation d'id " + programmationId + " non trouvée ou a deja été supprimée.");
        }
    }

    /**
     * Check if no period is checked
     *
     * @param periodes
     */
    void checkIfAllPeriodeNotFalse(List<PeriodesDTO> periodes) {
        boolean exist = false;
        for (PeriodesDTO p : periodes) {
            if (p.isValeur()) {
                exist = true;
            }
        }
        if (!exist) {
            throw new CustomException("Période(s) non spécifiée(s) ! Veuillez préciser la période de réalisation de l'activité");
        }
    }

    /**
     *
     * @param structureId
     * @param programmationId
     * @return
     */
    @Override
    public Optional<Programmation> validationInitialeOrInterne(Long structureId, Long programmationId) {
        Optional<Programmation> response = programmationRepository.findById(programmationId)
                .map(programmation -> {
                    if ((SecurityUtils.isCurrentUserInRole("ROLE_RESP_STRUCT") || SecurityUtils.isCurrentUserInRole("ROLE_ADMIN")) && (programmation.getStructure().getId() == structureId)) {
                        programmation.setValidationInitial(true);
                    }
                    if ((SecurityUtils.isCurrentUserInRole("ROLE_RESP_DGESS") || SecurityUtils.isCurrentUserInRole("ROLE_ADMIN")) && programmation.isValidationInitial()) {
                        programmation.setValidationInterne(true);//validation DGESS
                        programmation.setValidationFinal(true);//validation CASEM... A revoir
                    }
                    return programmation;
                });
        if (!response.isPresent()) {
            throw new CustomException("La programmation d'id " + programmationId + " est inexistante ou déjà validée");
        }
        return response;
    }

    @Override
    public void allValidationInitiale(Long structureId) {
        List<Programmation> list = programmationRepository.findAll(structureId);
        if (SecurityUtils.isCurrentUserInRole("ROLE_RESP_STRUCT") || SecurityUtils.isCurrentUserInRole("ROLE_ADMIN")) {
            for (Programmation programmation : list) {
                programmation.setValidationInitial(true);
            }
        }
    }

    @Override
    public void allValidationInterne(Long structureId) {
        List<Programmation> list = programmationRepository.findAll(structureId);
        if (SecurityUtils.isCurrentUserInRole("ROLE_RESP_DGESS") || SecurityUtils.isCurrentUserInRole("ROLE_ADMIN")) {
            list.stream().filter(programmation -> (programmation.isValidationInitial())).map(programmation -> {
                programmation.setValidationInterne(true);
                return programmation;
            }).forEachOrdered(programmation -> {
                programmation.setValidationFinal(true);
            });
        }
    }

    /**
     *
     * @param commentaireDTO
     */
    @Override
    public void rejetDgessOrCasem(CommentaireDTO commentaireDTO) {
        try {
            Optional<Programmation> programmation = programmationRepository.findById(commentaireDTO.getProgrammationId())
                    .filter(p -> p.isValidationInitial())
                    .map(p -> {
                        p.setValidationInitial(false);
                        p.setValidationInterne(false);
                        commentaireService.create(commentaireDTO);
                        return p;
                    });
            if (programmation.isEmpty()) {
                throw new CustomException("La programmation d'id " + commentaireDTO.getProgrammationId() + " est inexistante, ou déjà rejetée");
            }
        } catch (Exception e) {
            throw new CustomException("Une erreur s'est produite lors du rejet de la programmation. " + e);
        }
    }

    @Override
    @Transactional
    public void printProgrammeActivites(long ministereId, Long structureId, long exerciceId, long currentStructureId, OutputStream outputStream) {

        try {
            // chargement du ministère concerné
            Ministere ministere = this.ministereStructureRepository.findByStructureIdAndStatutIsTrue(currentStructureId).get().getMinistere();

            // structure génératrice du document
            Optional<Structure> currentStructure = Optional.ofNullable(structureRepository.getById(currentStructureId));

            Structure structureParent = new Structure();
            if (currentStructure.get().getParent() != null) {
                structureParent = this.structureRepository.findById(currentStructure.get().getParent().getId()).get();
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
            if (structureId == null) {
                allStructures = this.ministereStructureRepository.allNonInternalStructureByMinistere(ministereId, TypeStructure.INTERNE);
                // cas d'une structure particulière
            } else {
                Structure structure = this.structureRepository.findById(structureId).get();
                allStructures.add(structure);
            }

            // parcourir la liste des structures pour récuperer les programmes concernés par la structure ou le ministère
//            for (Structure struct : allStructures) {//non utilisé
//
//                // recherche des actions liées à la structure sur l'exercice en cours 
//                List<Action> allActions = this.actionRepository.findActionsByStructureAndExercice(struct.getId(), exerciceId);
//
//                for (Action act : allActions) {
//                    // Recherche des programmes liées à chaque action
//                    List<Programme> progs = this.programmeRepository.findProgrammeByAction(act.getId());
//                    for (Programme prog : progs) {
//                        if (!allPrograms.contains(prog)) {
//                            allPrograms.add(prog);
//                        }
//                    }
//                }
//            }
            // Construction des objet pour impression
            List<ViewGlobale> globalData = this.query.globalDataList();

            mainProgramData = ReportUtil.consctruct(globalData, ministere.getLibelle(), structureParent.getLibelle(), currentStructure.get().getLibelle(), currentStructure.get().getTelephone(), titre, logoStream);

            InputStream reportStream = this.getClass().getResourceAsStream("/conteneur_principal.jasper");

            Map<String, Object> parameters = new HashMap<>();

            JRDataSource datasource = new JRBeanCollectionDataSource(mainProgramData);

            JasperReport japerReport = (JasperReport) JRLoader.loadObject(reportStream);

            JasperPrint jasperPrint = JasperFillManager.fillReport(japerReport, parameters, datasource);

            /**
             * export to pdf
             */
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
            /**
             * export to Excel sheet
             */
//            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
//            configuration.setOnePagePerSheet(true);
//            configuration.setIgnoreGraphics(false);
//
//            File outputFile = new File("C:\\Users\\Canisius\\Pictures\\ProgrammeActivite.xlsx");
//            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                    OutputStream fileOutputStream = new FileOutputStream(outputFile)) {
//                Exporter exporter = new JRXlsxExporter();
//                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
//                exporter.setConfiguration(configuration);
//                exporter.exportReport();
//                byteArrayOutputStream.writeTo(fileOutputStream);
//            } catch (IOException ex) {
//                log.error("Error when exporting data from", ex);
//            }
//            JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Canisius\\Pictures\\test2.pdf");//exportReportToPdfStream(jasperPrint, outStream);
        } catch (JRException e) {
            log.error("Error when exporting data from", e);
        }
    }

}
