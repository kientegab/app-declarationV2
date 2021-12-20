/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.service.impl;

import com.mfptps.appdgessddi.repositories.MinistereStructureRepository;
import com.mfptps.appdgessddi.service.StatisticParameterService;
import com.mfptps.appdgessddi.service.dto.statisticresponses.CountStructureGroupByType;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Service
public class StatisticParameterServiceImpl implements StatisticParameterService {
    
    private final MinistereStructureRepository ministereStructureRepository;
    
    public StatisticParameterServiceImpl(MinistereStructureRepository ministereStructureRepository) {
        this.ministereStructureRepository = ministereStructureRepository;
    }
    
    @Override
    public long nbStructuresByMinistere(long ministereId) {
        return ministereStructureRepository.countStructureByMinistere(ministereId);
    }
    
    @Override
    public List<CountStructureGroupByType> nbStructuresByGroupType(long ministereId) {
        return ministereStructureRepository.countStructureByMinistereAndByGroupType(ministereId);
    }
    
}
