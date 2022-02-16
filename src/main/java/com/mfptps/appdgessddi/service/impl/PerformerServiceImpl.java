package com.mfptps.appdgessddi.service.impl;


import com.mfptps.appdgessddi.entities.Contribuer;
import com.mfptps.appdgessddi.entities.EvaluationGouvernance;
import com.mfptps.appdgessddi.entities.GrillePerformance;
import com.mfptps.appdgessddi.entities.Performance;
import com.mfptps.appdgessddi.entities.Performer;
import com.mfptps.appdgessddi.entities.Ponderation;
import com.mfptps.appdgessddi.entities.Structure;
import com.mfptps.appdgessddi.enums.TypeStructure;
import com.mfptps.appdgessddi.repositories.ContribuerRepository;
import com.mfptps.appdgessddi.repositories.EvaluationGouvernanceRepository;
import com.mfptps.appdgessddi.repositories.EvaluationRepository;
import com.mfptps.appdgessddi.repositories.GrillePerformanceRepository;
import com.mfptps.appdgessddi.repositories.PerformanceRepository;
import com.mfptps.appdgessddi.repositories.PerformerRepository;
import com.mfptps.appdgessddi.repositories.PonderationRepository;
import com.mfptps.appdgessddi.repositories.ProgrammationRepository;
import com.mfptps.appdgessddi.repositories.StructureRepository;
import com.mfptps.appdgessddi.service.PerformerService;
import com.mfptps.appdgessddi.service.dto.PerformanceDTO;
import com.mfptps.appdgessddi.service.dto.PerformerDTO;
import com.mfptps.appdgessddi.service.mapper.PerformerMapper;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
public class PerformerServiceImpl  implements PerformerService {

    private final PerformerRepository performerRepository ;
    private final PerformerMapper performerMapper ;
    private final GrillePerformanceRepository grilleRepository;
    private final StructureRepository structureRepository;
    private final ProgrammationRepository programmationRepository;
    private final PonderationRepository ponderationRepository;
    private final EvaluationGouvernanceRepository evaluationGouvernanceRepository;
    private final PerformanceRepository performanceRepository;
    private final EvaluationRepository evaluationRepository;
    private final ParametrerImpactServiceImpl impactRepository;
    private final ContribuerRepository contribuerRepository;

    public PerformerServiceImpl(PerformerRepository performerRepository, PerformerMapper performerMapper, 
            GrillePerformanceRepository grilleRepository, StructureRepository structureRepository,
            ProgrammationRepository programmationRepository, PonderationRepository ponderationRepository,
            EvaluationGouvernanceRepository evaluationGouvernanceRepository,PerformanceRepository performanceRepository,
            EvaluationRepository evaluationRepository, ParametrerImpactServiceImpl impactRepository,
            ContribuerRepository contribuerRepository) {
        
        this.performerRepository = performerRepository;
        this.performerMapper = performerMapper;
        this.grilleRepository = grilleRepository;
        this.structureRepository = structureRepository;
        this.programmationRepository = programmationRepository;
        this.ponderationRepository = ponderationRepository;
        this.evaluationGouvernanceRepository = evaluationGouvernanceRepository;
        this.performanceRepository = performanceRepository;
        this.evaluationRepository = evaluationRepository;
        this.impactRepository = impactRepository;
        this.contribuerRepository = contribuerRepository;
    }

    @Override
    public Performer create(PerformerDTO performerDTO) {
        Performer performer=performerMapper.toEntity(performerDTO);
        return performerRepository.save(performer);
    }

    @Override
    public Performer update(Performer performer) {
        return performerRepository.save(performer);
    }

    @Override
    public Optional<Performer> get(Long id) {
        return performerRepository.findById(id);
    }

    @Override
    public Page<Performer> findAll(Pageable pageable) {
        return performerRepository.findAll(pageable);
    }

    @Override
    public void delete(Long id) {
       performerRepository.deleteById(id);
    }
    
    //======= section de calcul des performances
    
    public PerformanceDTO calculatePerformance(Long ministerId, Long structureId, Long exerciceId, Long userId){
        
        PerformanceDTO performance = new PerformanceDTO();
        
        // variables de stockage des éléments de performance
        double efficacite = 0;
        double efficience = 0;
        double gouvernance = 0;
        double impact = 0;
        
        double pgs = 0; 
        String appreciation ;
        
        // Déclaration de la liste des structures concernées
        List<Structure> mesStructures = new ArrayList<>(); 
        
        // vérification, savoir si c'est tout le ministère ou si c'est juste une structure 
        boolean many = ((ministerId != null) && (structureId == null));
        
        // récupération de la podération par défaut
        Ponderation ponderation = ponderationRepository.findActivePonderation().orElse(null);
        
        if(many){
           mesStructures =  structureRepository.findMinistereStructure(ministerId, TypeStructure.INTERNE);
        }else{
            Structure singleStructure = new Structure();
            singleStructure.setId(structureId);
            mesStructures.add(singleStructure);
        }
        
        
        // compteur des structures
        int count = mesStructures.size();
        
        // Parcours des structures
        for(Structure structure : mesStructures){
            
            Performance perf = performanceRepository.findCurrentStructurePerformance(structure.getId(), exerciceId).orElse(new Performance()); 
            perf.setPonderationId(ponderation.getId());
            perf.setExerciceId(exerciceId);
            
            // récupération des évaluations de gouvernance
            List<EvaluationGouvernance> evalGouv = evaluationGouvernanceRepository.findStructureEvaluation(structure.getId(), exerciceId); // changment du type
            
            // nombre total d'activités programmées
            double nap = programmationRepository.countStructureProgrammation(structure.getId(), exerciceId); 
            
            // =+ taux global de réalisation des objectifs TGRO; 
            // ce taux vient de la somme des taux d'exécution par structure, cette valeur est stocquée dans la table évaluation
            double tgro = evaluationRepository.findEvaluation(structure.getId(), exerciceId);
            
            // nombre activités réalisées à temps
            long nart = programmationRepository.countActiviteRealiserATemps(structure.getId(), exerciceId); 
            
            // =+ coefficient temps CT
            double coeffTemps = nart / nap;
            
            // calcul de l'efficacité ea
            double ea = (tgro * 60 + coeffTemps * 40) / 100;  
            
            perf.setEfficacite(ea);
            efficacite = efficacite + ea;
            
            // ====== + FIN CALCULS ea =====//
            
            // calcul de l'efficience ei
            double ei ;
            
            // =+ montant total dépensée
            double montant_total = programmationRepository.coutTotalStructureProgrammation(structure.getId(), exerciceId); 
            // =+ somme des couts prévisionnels des activités réalisées à 100%
            double cout_previsionnel = 0;
            // =+ somme des couts réels des activités réalisées à 100%
            double cout_effectif = 0;
            
            ei = (cout_previsionnel - cout_effectif) / montant_total;  
            
            perf.setEfficience(ei);
            
            efficience = efficience + ei;
            
            // calcul de la gouvernance gouv
            double gouv = 0;
            
            if(evalGouv != null && !evalGouv.isEmpty()){
                for(EvaluationGouvernance eval : evalGouv){
                    gouv = gouv + (eval.getValeur()/eval.getValeurReference()) * 100;
                } 
                // après addition on fait la moyenne
                gouv = gouv/evalGouv.size();
            }
            
            perf.setGouvernance(gouv);
            gouvernance = gouvernance + gouv;
            
            
            // calcul de l'impact imp
            double imp = 0;
            
            if(ponderation.getImpact() > 0){
                // traitement spécifique ici
                // list des impacts définis par la DGESS
                
                List<Contribuer> contribs = contribuerRepository.findAllStructureContribution(structure.getId(), exerciceId);
                int nombre = contribs.size();
                
                for(Contribuer contrib : contribs){
                    // I = (VA – VR) / (Cible-VR) x 100 où VA = valeur atteinte = Valeur dans Contribuer et VR = Valeur référence i.e cible dans Contribuer 
                    //et Cible correspond à cible dans ParametrerImpact
                    imp = imp + (((contrib.getValeur() - contrib.getCible())/ (contrib.getParametrerImpact().getCible() - contrib.getCible())) * 100); 
                } 
                
                if(nombre > 0){
                    imp = imp / nombre;
                } 
            }
            
            perf.setImpact(imp);
            
            impact = impact + imp;
            
            // calcul de la performance globale de la structure
            
            double pg = (ponderation.getEfficacite() * ea + ponderation.getEfficience() * ei + ponderation.getGouvernance() * gouv + ponderation.getImpact() * imp) * 100 ;
            perf.setPgs(pg);
             
            // Sauvegarde de la performance par structure
            appreciation = grilleRepository.findGrilleAppreciation(pg).orElse("");
            perf.setAppreciation(appreciation);
            performanceRepository.save(perf);
             
            pgs = pgs + pg;
                        
        }
        
        // chargement des évaluations par structure
        
        // calcul de la moyenne si nécéssaire
        
        efficacite = efficacite / count;
        efficience = efficience / count;
        gouvernance = gouvernance / count;
        impact = impact/ count;
        pgs = pgs / count; 
        
        appreciation = grilleRepository.findGrilleAppreciation(pgs).orElse("");
        
        // dernières données
        performance.setGlobal(mesStructures.size()>1);
        performance.setEfficacite(efficacite);
        performance.setEfficience(efficience);
        performance.setImpact(impact);
        performance.setGouvernance(gouvernance);
        performance.setPgm(pgs);
        performance.setPgs(pgs);
        performance.setAppreciation(appreciation);
        
        return performance;
    }
}
