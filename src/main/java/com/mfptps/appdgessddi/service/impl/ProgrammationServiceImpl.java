/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.service.impl;

import com.mfptps.appdgessddi.entities.Exercice;
import com.mfptps.appdgessddi.entities.Ministere;
import com.mfptps.appdgessddi.entities.Periode;
import com.mfptps.appdgessddi.entities.Programmation;
import com.mfptps.appdgessddi.entities.ProgrammationPhysique;
import com.mfptps.appdgessddi.entities.Structure;
import com.mfptps.appdgessddi.entities.Tache;
import com.mfptps.appdgessddi.entities.TacheEvaluer;
import com.mfptps.appdgessddi.enums.ExerciceStatus;
import com.mfptps.appdgessddi.enums.TypeStructure;
import com.mfptps.appdgessddi.repositories.ExerciceRepository;
import com.mfptps.appdgessddi.repositories.MinistereStructureRepository;
import com.mfptps.appdgessddi.repositories.ObjectifRepository;
import com.mfptps.appdgessddi.repositories.ProgrammationPhysiqueRepository;
import com.mfptps.appdgessddi.repositories.ProgrammationRepository;
import com.mfptps.appdgessddi.repositories.QueryManagerRepository;
import com.mfptps.appdgessddi.repositories.StructureRepository;
import com.mfptps.appdgessddi.repositories.TacheEvaluerRepository;
import com.mfptps.appdgessddi.repositories.TacheRepository;
import com.mfptps.appdgessddi.security.SecurityUtils;
import com.mfptps.appdgessddi.service.CommentaireService;
import com.mfptps.appdgessddi.service.CustomException;
import com.mfptps.appdgessddi.service.ExerciceService;
import com.mfptps.appdgessddi.service.ProgrammationPhysiqueService;
import com.mfptps.appdgessddi.service.ProgrammationService;
import com.mfptps.appdgessddi.service.dto.CommentaireDTO;
import com.mfptps.appdgessddi.service.dto.PeriodesDTO;
import com.mfptps.appdgessddi.service.dto.ProgrammationDTO;
import com.mfptps.appdgessddi.service.dto.ProgrammationForEvaluationDTO;
import com.mfptps.appdgessddi.service.mapper.ProgrammationMapper;
import com.mfptps.appdgessddi.service.reportentities.ProgrammeDataRE;
import com.mfptps.appdgessddi.service.reportentities.ReportUtil;
import com.mfptps.appdgessddi.service.reportentities.ViewGlobale;
import com.mfptps.appdgessddi.utils.AppUtil;
import com.mfptps.appdgessddi.utils.ResponseCheckPeriode;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
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
    private final TacheEvaluerRepository tacheEvaluerRepository;
    private final ExerciceRepository exerciceRepository;
    private final ObjectifRepository objectifRepository;
    private final MinistereStructureRepository ministereStructureRepository;
    private final StructureRepository structureRepository;
    private final ProgrammationPhysiqueService programmationPhysiqueService;
    private final ProgrammationPhysiqueRepository programmationPhysiqueRepository;
    private final CommentaireService commentaireService;
    private final ProgrammationMapper programmationMapper;
    private final ExerciceService exerciceService;
    private final QueryManagerRepository query;

    public ProgrammationServiceImpl(ProgrammationRepository programmationRepository,
            ExerciceRepository exerciceRepository,
            TacheRepository tacheRepository,
            TacheEvaluerRepository tacheEvaluerRepository,
            ObjectifRepository objectifRepository,
            MinistereStructureRepository ministereStructureRepository,
            StructureRepository structureRepository,
            ProgrammationPhysiqueService programmationPhysiqueService,
            ProgrammationPhysiqueRepository programmationPhysiqueRepository,
            CommentaireService commentaireService,
            ProgrammationMapper programmationMapper,
            ExerciceService exerciceService,
            QueryManagerRepository query) {
        this.programmationRepository = programmationRepository;
        this.tacheRepository = tacheRepository;
        this.tacheEvaluerRepository = tacheEvaluerRepository;
        this.exerciceRepository = exerciceRepository;
        this.objectifRepository = objectifRepository;
        this.ministereStructureRepository = ministereStructureRepository;
        this.structureRepository = structureRepository;
        this.programmationPhysiqueService = programmationPhysiqueService;
        this.programmationPhysiqueRepository = programmationPhysiqueRepository;
        this.commentaireService = commentaireService;
        this.programmationMapper = programmationMapper;
        this.exerciceService = exerciceService;
        this.query = query;

    }

    /**
     *
     * @param programmationDTO : DTO contents Activite, Programmation, Projet,
     * SourceFinancement, and Tache(s) fields
     * @return
     */
    @Override
    @Transactional(rollbackFor = CustomException.class)
    public Programmation create(ProgrammationDTO programmationDTO) {
        Programmation programmationMapped = programmationMapper.toEntity(programmationDTO);
        log.debug("Sum of Ponderations = {} %", programmationMapped.checkPonderation());

        //s'assurer qu'au moins une periode a ete selectionnee 
        this.checkIfAllPeriodeNotFalse(programmationDTO.getPeriodes());

        //initialisation de la programmation mappee
        String code = AppUtil.codeGeneratorProgrammation(programmationRepository, objectifRepository, programmationMapped);
        Exercice exerciceEnAttente = exerciceRepository.findByStatut(ExerciceStatus.EN_ATTENTE).orElseThrow(() -> new CustomException("Aucun exercice en attente."));
        programmationMapped.setExercice(exerciceEnAttente);
        programmationMapped.setCode(code);
        programmationMapped.setCible(programmationDTO.getCible() <= 0 ? 1D : programmationDTO.getCible());

        //enregistre la programmation et ses periodes
        Programmation response = programmationRepository.save(programmationMapped);
        programmationPhysiqueService.addProgrammationPhysique(programmationDTO.getPeriodes(), response);

        //recherche, normalisation et update du deadline de la programmation
        Date deadLine = programmationPhysiqueRepository.findPeriodeMaxDateForProgrammation(response.getId());
        deadLine = AppUtil.repairDate(deadLine, exerciceEnAttente.getDebut().getYear());
        response.setDeadLine(deadLine);
//FAUT-IL .SAVE ENCORE RESPONSE ????????????????????????????

        //initialise et enregistre les taches
        if (programmationDTO.isSingleton()) {//Activite with one Tache
            Tache tache = new Tache();
            tache.setValeur(programmationMapped.getCible());
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
        Optional<Programmation> response = programmationRepository.findById(programmation.getId())
                .filter(p -> !p.isValidationFinal())
                .map(p -> {
                    return programmationRepository.save(programmation);
                });

        if (!response.isPresent()) {
            throw new CustomException("Modification non autorisée car la programmation est déjà validée.");
        }
        return response.get();
    }

    /**
     *
     * @param structureId
     * @param libelle : libelle of Activite
     * @param pageable
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
    public ProgrammationForEvaluationDTO getForEvaluation(long programmationId) {
        Programmation programmation = programmationRepository.findById(programmationId).orElseThrow(() -> new CustomException("Programmation d'id " + programmationId + " inexistante"));

        return this.constructProgrammationForEvaluationDTO(programmation);
    }

    public ProgrammationForEvaluationDTO constructProgrammationForEvaluationDTO(Programmation programmation) {
        ProgrammationForEvaluationDTO response = programmationMapper.toEvaluationDTO(programmation);

        ResponseCheckPeriode checkPeriodes = AppUtil.checkProgrammationPhysique(response.getId(), programmationPhysiqueRepository);

        response.setTauxActuel(this.tauxExecutionByExerciceOrPeriode(Arrays.asList(programmation), checkPeriodes.getPeriode()));
        response.setValeurActuelle(this.valeurCibleAtteinte(programmation.getTaches()));
        Optional<Periode> periode = programmationPhysiqueRepository.findByPeriodeAndProgrammation(checkPeriodes.getPeriode(), programmation.getId());
        if (periode.isPresent()) {
            response.setPeriodeActuelle(periode.get().getLibelle());
        }

        String periodes = "";
        for (ProgrammationPhysique pph : checkPeriodes.getPeriodes()) {
            periodes = periodes + pph.getPeriode().getLibelle() + "-";
        }
        response.setPeriodes(periodes.substring(0, (periodes.length() - 1)));

        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Programmation> findAll(Long structureId, Pageable pageable) {
        return programmationRepository.findAll(structureId, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Programmation> findAllENCOURS(Long structureId, Pageable pageable) {
        return programmationRepository.findAll(structureId, ExerciceStatus.EN_COURS, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Programmation> findAllValided(Long structureId, Pageable pageable) {
        return programmationRepository.findAllValided(structureId, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProgrammationForEvaluationDTO> findAllAfterEvaluation(long structureId, long exerciceId) {
        List<Programmation> list = programmationRepository.findByStructureAndExerciceValided(structureId, exerciceId);
        List<ProgrammationForEvaluationDTO> response = new ArrayList<>();

        for (Programmation prog : list) {
            response.add(this.constructProgrammationForEvaluationDTO(prog));
        }
        return response;
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
                    if ((SecurityUtils.isCurrentUserInRole(AppUtil.RS) || SecurityUtils.isCurrentUserInRole(AppUtil.ADMIN))
                            && (programmation.getStructure().getId() == structureId) && (!programmation.isValidationInitial())) {
                        programmation.setValidationInitial(true);
                    }
                    if ((SecurityUtils.isCurrentUserInRole(AppUtil.RD) || SecurityUtils.isCurrentUserInRole(AppUtil.ADMIN))
                            && programmation.isValidationInitial()) {
                        programmation.setValidationInterne(true);//validation DGESS
                    }
                    return programmation;
                });
        if (!response.isPresent()) {
            throw new CustomException("La programmation d'id " + programmationId + " est inexistante ou déjà validée");
        }
        return response;
    }

    @Override
    public String allValidationInitiale(Long structureId) {
        List<Programmation> list = programmationRepository.findAll(structureId);
        List<Programmation> updater = new ArrayList<>();

        if (SecurityUtils.isCurrentUserInRole(AppUtil.RS) || SecurityUtils.isCurrentUserInRole(AppUtil.ADMIN)) {
            for (Programmation programmation : list) {
                programmation.setValidationInitial(true);
                updater.add(programmation);
            }
            programmationRepository.saveAll(updater);
        }
        return "Validations initiales bien effectuées.";
    }

    @Override
    public String allValidationInterne(Long structureId) {
        List<Programmation> list = programmationRepository.findAll(structureId);
        List<Programmation> updater = new ArrayList<>();
        boolean echec = false;

        if (SecurityUtils.isCurrentUserInRole(AppUtil.RD) || SecurityUtils.isCurrentUserInRole(AppUtil.ADMIN)) {
            for (Programmation programmation : list) {
                if (programmation.isValidationInitial()) {
                    programmation.setValidationInterne(true);
                    updater.add(programmation);
                } else {
                    echec = true;
                }
            }
            programmationRepository.saveAll(updater);
        }
        return echec ? "Validation effectuée partiellement, car certaines activités n'ont pas été validées par " + list.get(0).getStructure().getSigle() : "Validations internes bien effectuées.";
    }

    @Override
    public String allValidationCASEM(Long structureId) {
        List<Programmation> list = programmationRepository.findAll(structureId);
        List<Programmation> updater = new ArrayList<>();
        boolean echec = false;

        if (SecurityUtils.isCurrentUserInRole(AppUtil.RD) || SecurityUtils.isCurrentUserInRole(AppUtil.ADMIN)) {

            for (Programmation programmation : list) {
                if (programmation.isValidationInitial() && programmation.isValidationInterne()) {
                    programmation.setValidationFinal(true);
                    updater.add(programmation);
                } else {
                    echec = true;
                }
            }
            programmationRepository.saveAll(updater);
        }

        return echec ? "Validation effectuée partiellement, car certaines activités n'ont pas été validées précédemment." : "Validations finales (CASEM) bien effectuées.";
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
            if (!programmation.isPresent()) {
                throw new CustomException("La programmation d'id " + commentaireDTO.getProgrammationId() + " est inexistante, ou déjà rejetée");
            }
        } catch (CustomException e) {
            throw new CustomException("Une erreur s'est produite lors du rejet de la programmation. " + e);
        }
    }

    @Override
    @Transactional
    public void printProgrammeActivites(long ministereId, Long structureId, long exerciceId, long currentStructureId, String fileFormat, OutputStream outputStream) {

        try {
            // chargement du ministère concerné
            Ministere ministere = this.ministereStructureRepository.findByStructureIdAndStatutIsTrue(currentStructureId).get().getMinistere();

            // structure génératrice du document
            Optional<Structure> currentStructure = Optional.ofNullable(structureRepository.getById(currentStructureId));

            Optional<Exercice> exercice = exerciceService.get(exerciceId);

            Structure structureParent = new Structure();
            if (currentStructure.get().getParent() != null) {
                structureParent = this.structureRepository.findById(currentStructure.get().getParent().getId()).get();
            }

            // chargement du logo
            InputStream logoStream = AppUtil.getAppDefaultLogo();

            // le titre du rapport
            String titre = "PROGRAMME D'ACTIVITES " + exercice.get().getDebut().getYear();

            // conteneurs de données à imprimer
            List<ProgrammeDataRE> mainProgramData = new ArrayList<>();

            // conteneur des structures
            List<Structure> allStructures = new ArrayList<>();

            // Construction des objet pour impression
            List<ViewGlobale> globalData = new ArrayList<>();

            // cas de l'ensemble des structures
            if (structureId == null) {
                allStructures = this.ministereStructureRepository.allNonInternalStructureByMinistere(ministereId, TypeStructure.INTERNE);
                globalData = this.query.globalDataMinistere(exercice.get().getId());
            } else {// cas d'une structure particulière
                Structure structure = this.structureRepository.findById(structureId).get();
                allStructures.add(structure);
                globalData = this.query.globalDataStructure(exercice.get().getId(), structure.getId());
            }

            mainProgramData = ReportUtil.consctruct(ministere.getLibelle(), structureParent.getLibelle(), currentStructure.get().getLibelle(), currentStructure.get().getTelephone(), titre, logoStream, globalData);

            InputStream reportStream = this.getClass().getResourceAsStream("/conteneur_principal.jasper");

            Map<String, Object> parameters = new HashMap<>();

            JRDataSource datasource = new JRBeanCollectionDataSource(mainProgramData);

            JasperReport japerReport = (JasperReport) JRLoader.loadObject(reportStream);

            JasperPrint jasperPrint = JasperFillManager.fillReport(japerReport, parameters, datasource);

            if (fileFormat.trim().equals("pdf")) {//export to pdf file
                JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
            } else if (fileFormat.trim().equals("excel")) {//export to Excel sheet
                SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
                configuration.setOnePagePerSheet(true);
                configuration.setIgnoreGraphics(false);

                File outputFile = new File("C:\\Users\\Canisius\\Pictures\\Programme_activites.xlsx");
                try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        OutputStream fileOutputStream = new FileOutputStream(outputFile)) {
                    Exporter exporter = new JRXlsxExporter();
                    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
                    exporter.setConfiguration(configuration);
                    exporter.exportReport();
                    byteArrayOutputStream.writeTo(fileOutputStream);
                } catch (IOException ex) {
                    log.error("Error when exporting data from", ex);
                }
            }

//            JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Canisius\\Pictures\\test2.pdf");//exportReportToPdfStream(jasperPrint, outStream);
        } catch (JRException e) {
            log.error("Error when exporting data from", e);
        }
    }

    @Override
    @Transactional
    public void printRapportActivites(long ministereId, Long structureId, long exerciceId, long currentStructureId, String fileFormat, OutputStream outputStream) {

        try {
            // chargement du ministère concerné
            Ministere ministere = this.ministereStructureRepository.findByStructureIdAndStatutIsTrue(currentStructureId).get().getMinistere();

            // structure génératrice du document
            Optional<Structure> currentStructure = Optional.ofNullable(structureRepository.getById(currentStructureId));

            Optional<Exercice> exercice = exerciceService.get(exerciceId);

            Structure structureParent = new Structure();
            if (currentStructure.get().getParent() != null) {
                structureParent = this.structureRepository.findById(currentStructure.get().getParent().getId()).get();
            }

            // chargement du logo
            InputStream logoStream = AppUtil.getAppDefaultLogo();

            // le titre du rapport
            String titre = "RAPPORT D'ACTIVITES " + exercice.get().getDebut().getYear();

            // conteneurs de données à imprimer
            List<ProgrammeDataRE> mainProgramData = new ArrayList<>();

            // Construction des objet pour impression
            List<ViewGlobale> globalData = new ArrayList<>();

            // conteneur des structures
            List<Structure> allStructures = new ArrayList<>();

            // cas de l'ensemble des structures
            if (structureId == null) {
                allStructures = this.ministereStructureRepository.allNonInternalStructureByMinistere(ministereId, TypeStructure.INTERNE);
                globalData = this.query.globalDataMinistere(exercice.get().getId());
            } else {// cas d'une structure particulière
                Structure structure = this.structureRepository.findById(structureId).get();
                allStructures.add(structure);
                globalData = this.query.globalDataStructure(exercice.get().getId(), structure.getId());
            }

            mainProgramData = ReportUtil.consctructRapport(ministere.getLibelle(), structureParent.getLibelle(), currentStructure.get().getLibelle(), currentStructure.get().getTelephone(), titre, logoStream, globalData);

            InputStream reportStream = this.getClass().getResourceAsStream("/conteneur_principal_rapport.jasper");

            Map<String, Object> parameters = new HashMap<>();

            JRDataSource datasource = new JRBeanCollectionDataSource(mainProgramData);

            JasperReport japerReport = (JasperReport) JRLoader.loadObject(reportStream);

            JasperPrint jasperPrint = JasperFillManager.fillReport(japerReport, parameters, datasource);

            if (fileFormat.trim().equals("pdf")) {//export to pdf file
                //JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Canisius\\Pictures\\Rapport_activites.pdf");
                JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
            }

        } catch (JRException e) {
            log.error("Error when exporting data from", e);
        }
    }

    /**
     * TAUX D'EXECUTION PAR STRUCTURE
     *
     * @param structureId
     * @param exerciceId
     * @param periodeId
     * @return
     */
    @Override
    public double tauxExecution(long structureId, long exerciceId, Long periodeId) {
        double result = 0;
        if (null == periodeId) {
            //Recuperer toutes programmations concernees par l'exercice
            List<Programmation> programmations = programmationRepository.findByStructureAndExerciceValided(structureId, exerciceId);

            result = this.tauxExecutionByExerciceOrPeriode(programmations, null);
        } else {
            //Recuperer toutes programmations concernees par l'exercice et la pph
            List<Programmation> programmations = programmationRepository.findByStructureAndExerciceAndPeriodeValided(structureId, exerciceId, periodeId);

            result = this.tauxExecutionByExerciceOrPeriode(programmations, periodeId);
        }
        return result;
    }

    /**
     * SOUS FONCTION (TAUX PAR STRUCTURE): Taux d'execution par exercice/pph
     *
     * @param structureId
     * @param exerciceId
     * @param periodeId
     * @return
     */
    public double tauxExecutionByExerciceOrPeriode(List<Programmation> programmations, Long periodeId) {
        double taux = 0;//taux global recherche

        for (Programmation prog : programmations) {
            double tauxTaches = 0;//taux global de chaque programmation
            //on totalise les taux des taches de chaque programmation
            for (Tache tache : prog.getTaches()) {
                if (periodeId == null) {//============== CALCULS SANS TENIR COMPTE DE LA PEDIODE
                    //Optional<TacheEvaluer> tacheEvaluee = tacheEvaluerRepository.getByTacheAndActive(tache.getId());
                    tauxTaches = this.tauxByValueOfPeriode(prog, tache, null, tauxTaches);
                } else {//============== CALCULS EN FONCTION DE LA PERIODE
                    Optional<TacheEvaluer> tacheEvaluee = tacheEvaluerRepository.getByTacheAndPeriodeActive(tache.getId(), periodeId);
                    tauxTaches = this.tauxByValueOfPeriode(prog, tache, tacheEvaluee, tauxTaches);
                }
            }
            //on totalise le taux de chaque programmation 
            taux += tauxTaches;
        }
        return taux;
    }

    /**
     * Calcul des valeurs cible atteintes des taches d'une programmation
     *
     * @param taches
     * @return
     */
    private double valeurCibleAtteinte(List<Tache> taches) {
        double valeur = 0;

        valeur = taches.stream().filter(tache -> (tache.getValeur() != 1D)).filter(tache -> (tacheEvaluerRepository.getByTacheAndActive(tache.getId()).isPresent())).map(tache -> tacheEvaluerRepository.cumuleeOfTache(tache.getId())).reduce(valeur, (accumulator, _item) -> accumulator + _item);

        return valeur;
    }

    //SOUS FONCTION DE tauxExecutionByExerciceOrPeriode(...)
    private double tauxByValueOfPeriode(Programmation prog, Tache tache, Optional<TacheEvaluer> tacheEvaluee, double tauxTaches) {
        if ((tache.getValeur() == 1D) && tache.isExecute()) {//tache sans cible(cible = 1)  deja execute
            tauxTaches += tache.getPonderation();
        } else if ((tache.getValeur() != 1D) && tache.isExecute()) {//tache a cible deja execute(meme au dela de la cible prevue)
            tauxTaches += tache.getPonderation();
        } else if ((tache.getValeur() != 1D) && !tache.isExecute() && tacheEvaluee != null) {//tache a cible execute partiellement
            tauxTaches = this.tauxByValueOfProgrammationCible(prog, tache, tacheEvaluee, tauxTaches);
        }

        return tauxTaches;
    }

    //SOUS FONCTION DE tauxByValueOfPeriode(...)
    private double tauxByValueOfProgrammationCible(Programmation prog, Tache tache, Optional<TacheEvaluer> tacheEvaluee, double tauxTaches) {
        if (tacheEvaluee.isPresent()) {
            if (prog.getCible() != 1D) {//programmation a cible 
                tauxTaches += (tacheEvaluee.get().getValeurCumulee() / prog.getCible()) * tache.getPonderation();
            } else {//programmation sans cible (cible = 1) 
                tauxTaches += (tacheEvaluee.get().getValeurCumulee() / tache.getValeur()) * tache.getPonderation();
            }
        }
        return tauxTaches;
    }

    /**
     * TAUX D'EXECUTION PAR MINISTERE
     *
     * @param ministereId
     * @param exerciceId
     * @param periodeId
     * @return
     */
    @Override
    public double tauxExecutionGlobal(long ministereId, long exerciceId, Long periodeId) {
        double tauxGlobal = 0;
        if (null == periodeId) {
            //Recuperer toutes programmations concernees par l'exercice
            List<Programmation> programmations = programmationRepository.findByMinistereAndExerciceValided(ministereId, exerciceId);

            tauxGlobal = this.tauxExecutionByExerciceOrPeriode(programmations, null);
        } else {
            //Recuperer toutes programmations concernees par l'exercice et la pph
            List<Programmation> programmations = programmationRepository.findByMinistereAndExerciceAndPeriodeValided(ministereId, exerciceId, periodeId);

            tauxGlobal = this.tauxExecutionByExerciceOrPeriode(programmations, periodeId);
        }
        return tauxGlobal;
    }

}
