/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.web;

import com.mfptps.appdgessddi.aop.utils.HeaderUtil;
import com.mfptps.appdgessddi.entities.Programmation;
import com.mfptps.appdgessddi.service.ProgrammationService;
import com.mfptps.appdgessddi.service.dto.ProgrammationDTO;
import java.net.URI;
import java.net.URISyntaxException;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Slf4j
@RestController
@RequestMapping(path = "/api/programmations")
public class ProgrammationController {

    private static final String ENTITY_NAME = "programmation";

    @Value("${application.name}")
    private String applicationName;

    private final ProgrammationService programmationService;

    public ProgrammationController(ProgrammationService programmationService) {
        this.programmationService = programmationService;
    }

    @PostMapping
    public ResponseEntity<Programmation> createProgrammation(@Valid @RequestBody ProgrammationDTO programmationDTO) throws URISyntaxException {
        Programmation saved = programmationService.create(programmationDTO);
        log.debug("Programmation d'une activité : {}", programmationDTO);
        return ResponseEntity.created(new URI("/api/programmations/" + saved.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, saved.getId().toString()))
                .body(saved);
    }
}
