/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mefp.appdeclaration.config;
import java.util.Optional;
import javax.annotation.PostConstruct;

import com.mefp.appdeclaration.entities.Region;
import com.mefp.appdeclaration.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import com.mefp.appdeclaration.entities.Structure;

import com.mefp.appdeclaration.repositories.StructureRepository;
import com.mefp.appdeclaration.service.AgentService;
import com.mefp.appdeclaration.service.CustomException;
import com.mefp.appdeclaration.utils.AppUtil;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Configuration
/**
 * @param ministereRepository
 * @param structureRepository
 * @param ministereStructureRepository
 * @param agentService
 */

public class AppDataInitializer {

    //private final ExerciceRepository exerciceRepository;

   @Autowired
   // private final MinistereRepository ministereRepository;

    private final StructureRepository structureRepository;
    private final RegionRepository regionRepository;

   // private final MinistereStructureRepository ministereStructureRepository;

    private final AgentService agentService;


    public AppDataInitializer(
            //MinistereRepository ministereRepository,
            StructureRepository structureRepository,
            RegionRepository regionRepository,
            //MinistereStructureRepository ministereStructureRepository,
            AgentService agentService) {
        
        //this.ministereRepository = ministereRepository;
        this.structureRepository = structureRepository;
        this.regionRepository=regionRepository;
        //this.ministereStructureRepository = ministereStructureRepository;
        this.agentService = agentService;
    }

    @PostConstruct
    public void initialization() {
        //save Exercices
        // if (0 == exerciceRepository.count()
        //         || !exerciceRepository.findByStatut(ExerciceStatus.EN_COURS).isPresent()
        //         || !exerciceRepository.findByStatut(ExerciceStatus.EN_ATTENTE).isPresent()) {
        //     LocalDate dateDebut = LocalDate.of(LocalDate.now().getYear(), Month.JANUARY, 1);
        //     LocalDate dateFin = LocalDate.of(LocalDate.now().getYear(), Month.DECEMBER, 31);
        //     Exercice exerciceCourant = new Exercice();
        //     Exercice exerciceAVenir = new Exercice();

        //     exerciceCourant.setDebut(dateDebut);
        //     exerciceCourant.setFin(dateFin);
        //     exerciceCourant.setStatut(ExerciceStatus.EN_COURS);

        //     exerciceAVenir.setDebut(dateDebut.plusYears(1));
        //     exerciceAVenir.setFin(dateFin.plusYears(1));
        //     exerciceAVenir.setStatut(ExerciceStatus.EN_ATTENTE);

        //     exerciceRepository.saveAll(Arrays.asList(exerciceCourant, exerciceAVenir));
        // }

        //save Basic Ministere and Structure
//        Optional<Ministere> basicExisting = ministereRepository.findByCode(AppUtil.BASIC_MINISTERE_CODE);
//        if (!basicExisting.isPresent()) {
//            this.recordBasicMinistereAndStrucuture();
//        }
        Optional<Region> regionExisting= regionRepository.findByCode(AppUtil.BASIC_REGION_CODE);
        if (!regionExisting.isPresent()) {
            this.recordBasicMinistereAndStrucuture();
        }

    }


    /**
     *
     */
    void recordBasicMinistereAndStrucuture() {
        try {
            Region region = new Region();
            Structure structure = new Structure();
            //MinistereStructure ministereStructure = new MinistereStructure();

            region.setCode(AppUtil.BASIC_REGION_CODE);
            region.setLibelle(AppUtil.BASIC_REGION_LABEL);
            region.setIdRegion(AppUtil.BASIC_REGION_ID);
            regionRepository.save(region);


            structure.setLibelle(AppUtil.BASIC_STRUCTURE_LABEL);
            structure.setSigle(AppUtil.BASIC_STRUCTURE_SIGLE);
            structure.setId(AppUtil.BASIC_STRUCTURE_ID);
            structure.setRegion(region);
            structureRepository.save(structure);

//            ministereStructure.setMinistere(ministere);
//            ministereStructure.setStructure(structure);
//            ministereStructure.setDateDebut(new Date());
//            ministereStructureRepository.save(ministereStructure);

            //Join system users to new basic structure
            agentService.affectationAgent("admin", structure.getId());
            agentService.affectationAgent("user", structure.getId());
        } catch (Exception e) {
            throw new CustomException("Une erreur s'est produite lors de l'initialisation des ministère et structure de base.");
        }
    }

}
