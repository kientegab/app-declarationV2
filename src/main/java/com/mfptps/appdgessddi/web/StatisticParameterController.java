/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.web;

import com.mfptps.appdgessddi.service.StatisticParameterService;
import com.mfptps.appdgessddi.service.dto.statisticresponses.CountStructureGroupByType;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Slf4j
@RestController
@RequestMapping(path = "/api/param-statistic")
public class StatisticParameterController {

    private final StatisticParameterService statisticParameterService;

    public StatisticParameterController(StatisticParameterService statisticParameterService) {
        this.statisticParameterService = statisticParameterService;
    }

    /**
     *
     * @param id: id of Ministere
     * @return
     */
    @GetMapping(path = "/structure/{id}")
    public ResponseEntity<Long> countStructuresByMinistere(@PathVariable(name = "id") Long id) {
        log.debug("Nombre de structures (de niveau 1) du Ministere : {}", id);
        long counter = statisticParameterService.nbStructuresByMinistere(id);
        return new ResponseEntity<Long>(counter, HttpStatus.OK);
    }

    /**
     *
     * @param id : id of Ministere
     * @return
     */
    @GetMapping(path = "/structure/type/{id}")
    public ResponseEntity<List<CountStructureGroupByType>> countStructuresByMinistereAndByGroupType(@PathVariable(name = "id") Long id) {
        log.debug("Nombre de structures (de niveau 1 et groupées par type) du Ministere : {}", id);
        List<CountStructureGroupByType> response = statisticParameterService.nbStructuresByGroupType(id);
        return ResponseEntity.ok().body(response);
    }

}
