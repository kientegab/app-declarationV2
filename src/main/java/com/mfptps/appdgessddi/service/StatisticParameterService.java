/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.service;

import com.mfptps.appdgessddi.service.dto.statisticresponses.CountStructureGroupByType;
import java.util.List;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public interface StatisticParameterService {

    long nbStructuresByMinistere(long ministereId);

    List<CountStructureGroupByType> nbStructuresByGroupType(long ministereId);
}
