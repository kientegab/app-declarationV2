/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.service.impl;

import com.mfptps.appdgessddi.entities.Structure;
import com.mfptps.appdgessddi.repositories.MinistereStructureRepository;
import com.mfptps.appdgessddi.repositories.ProgrammationRepository;
import com.mfptps.appdgessddi.service.StatisticParameterService;
import com.mfptps.appdgessddi.service.dto.statisticresponses.CountStructureGroupByType;
import com.mfptps.appdgessddi.service.dto.statisticresponses.MinistereGlobalStatsBundleData;
import com.mfptps.appdgessddi.service.dto.statisticresponses.ResumerActiviteData;
import com.mfptps.appdgessddi.service.dto.statisticresponses.ResumerDepenseData;
import com.mfptps.appdgessddi.service.dto.statisticresponses.ResumerStructureData;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Service
public class StatisticParameterServiceImpl implements StatisticParameterService {

    private final MinistereStructureRepository repository;
    private final ProgrammationRepository programmationRepository;

    public StatisticParameterServiceImpl(MinistereStructureRepository repository, ProgrammationRepository programmationRepository) {
        this.repository = repository;
        this.programmationRepository = programmationRepository;
    }

    @Override
    public long nbStructuresByMinistere(long ministereId) {
        return repository.countStructureByMinistere(ministereId);
    }

    @Override
    public List<CountStructureGroupByType> nbStructuresByGroupType(long ministereId) {
        return repository.countStructureByMinistereAndByGroupType(ministereId);
    }

    /**
     * Resume les dépenses du ministere pour l'exercice en cours
     * @param ministereId
     * @param exerciceId
     * @return 
     */
    @Override
    public ResumerDepenseData resumerDepenseParMinistere(Long ministereId,Long exerciceId) {
        // initialisation de la donnée de retour
        ResumerDepenseData data = new ResumerDepenseData();
        data.setLibelle("-");
         
        // Somme des couts prévisionnels pour le ministere et l'exercice donné
        double previsionnel = programmationRepository.coutPrevisionnelMinistreProgrammation(ministereId, exerciceId);
        data.setPrevisionnel(previsionnel);
       
       // Somme des couts réels pour le ministere et l'exercice donné
       double reel = programmationRepository.coutReelMinistereProgrammation(ministereId, exerciceId);
       data.setReel(reel);
        
        return data;
    }

    @Override
    public ResumerActiviteData resumerActiviteParMinistere(Long ministereId,Long exerciceId) {
        // initialisation de la donnée de retour
        ResumerActiviteData data = new ResumerActiviteData();
        data.setLibelle("-");
        
        // termine
        long termine = programmationRepository.countMinistereTermine(ministereId, exerciceId);
        data.setTermine(termine);
        
        //encours
        long encours = programmationRepository.countMinistereEncours(ministereId, exerciceId);
        data.setEncours(encours);
        
        // en attente
        long enattente = programmationRepository.countMinistereAttente(ministereId, exerciceId);
        data.setEnattente(enattente);
        
        // total
        long total = termine + encours + enattente;
        data.setTotal(total);
        
        
         return data;
    }

    @Override
    public List<ResumerStructureData> resumerActiviteParStructure(Long ministereId, Long exerciceId) {
        
        // initialisation de la donnée de retour
        List<ResumerStructureData> data = new ArrayList<>();
        
        // liste des structures
        List<Structure> structures = repository.findAllStructuresByMinistere(ministereId);
        
        for(Structure struct : structures){
            ResumerStructureData resume = new ResumerStructureData();
            resume.setLibelle("Evolution de l'exécution des activités");
            resume.setStructureCode(struct.getSigle());
            
             // termine
            long termine = programmationRepository.countStructureProgrammationTerminer(struct.getId(), exerciceId);
            resume.setTermine(termine);
        
            //encours
            long encours = programmationRepository.countStructureProgrammationEncours(struct.getId(), exerciceId);
            resume.setEncours(encours);

            // en attente
            long enattente = programmationRepository.countStructureProgrammationEnattente(struct.getId(), exerciceId);
            resume.setEnattente(enattente);

            // total
            long total = termine + encours + enattente;
            resume.setTotal(total);
            
            data.add(resume);
            
        }
        
        
        return data;
    }

    /**
     * Regroupe en une seule option les trois précédentes fonctions
     * @param ministereId
     * @param exerciceId
     * @return 
     */
    @Override
    public MinistereGlobalStatsBundleData resumerMinistere(Long ministereId, Long exerciceId) {
        
        MinistereGlobalStatsBundleData data = new MinistereGlobalStatsBundleData();
        
         List<ResumerStructureData> resumes = resumerActiviteParStructure(ministereId, exerciceId);
         data.setResumes(resumes);
         
         ResumerDepenseData depense = resumerDepenseParMinistere(ministereId, exerciceId);
         data.setDepense(depense);
        
         ResumerActiviteData activite = resumerActiviteParMinistere(ministereId, exerciceId);
         data.setActivite(activite);
        
        return data;
         
    }

}
