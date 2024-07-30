/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mefp.appdeclaration.service;

import java.util.List;

import com.mefp.appdeclaration.service.dto.statisticresponses.AllEvolutionData;
import com.mefp.appdeclaration.service.dto.statisticresponses.CountStructureGroupByType;
import com.mefp.appdeclaration.service.dto.statisticresponses.EvolutionParam;
import com.mefp.appdeclaration.service.dto.statisticresponses.MinistereGlobalPerfData;
import com.mefp.appdeclaration.service.dto.statisticresponses.MinistereGlobalStatsBundleData;
import com.mefp.appdeclaration.service.dto.statisticresponses.ResumerActiviteData;
import com.mefp.appdeclaration.service.dto.statisticresponses.ResumerDepenseData;
import com.mefp.appdeclaration.service.dto.statisticresponses.ResumerSectorielData;
import com.mefp.appdeclaration.service.dto.statisticresponses.ResumerSectorielDepenseData;
import com.mefp.appdeclaration.service.dto.statisticresponses.ResumerStructureData;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public interface StatisticParameterService {

    long nbStructuresByMinistere(long ministereId);

    ResumerDepenseData resumerDepenseParMinistere(Long ministereId, Long exerciceId);

    ResumerActiviteData resumerActiviteParMinistere(Long ministereId, Long exerciceId);

    List<ResumerStructureData> resumerActiviteParStructure(Long ministereId, Long exerciceId);

    MinistereGlobalStatsBundleData resumerMinistere(Long ministereId, Long exerciceId);
    
    MinistereGlobalPerfData resumerPerformance(Long ministereId, Long exerciceId);
    
    MinistereGlobalPerfData resumerGlobalPerformance(Long exerciceId);

    ResumerSectorielData resumerSectoriel(Long ministereId, Long exerciceId);

    ResumerSectorielDepenseData resumerSectorielDepense(Long ministereId, Long exerciceId); 
    
    AllEvolutionData resumerEvolution(EvolutionParam params);
    
    AllEvolutionData resumerEvolutionPerformance(EvolutionParam params);

    List<CountStructureGroupByType> nbStructuresByGroupType(long ministereId);
}
