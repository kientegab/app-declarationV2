/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.web;

import com.mfptps.appdgessddi.entities.Periode;
import com.mfptps.appdgessddi.service.PeriodeService;
import com.mfptps.appdgessddi.utils.HeaderUtil;
import com.mfptps.appdgessddi.utils.PaginationUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Slf4j
@RestController
@RequestMapping(path = "/api/periodes")
public class PeriodeController {

    private static final String ENTITY_NAME = "periode";

    @Value("${application.name}")
    private String applicationName;

    private final PeriodeService periodeService;

    public PeriodeController(PeriodeService periodeService) {
        this.periodeService = periodeService;
    }

    /**
     *
     * @param periode
     * @return
     * @throws URISyntaxException
     */
    @PostMapping
    public ResponseEntity<Periode> createPeriode(@Valid @RequestBody Periode periode) throws URISyntaxException {
        Periode dataSaved = periodeService.create(periode);
        log.debug("Création d'une Periode : {}", periode);
        return ResponseEntity.created(new URI("/api/periodes/" + dataSaved.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, dataSaved.getId().toString()))
                .body(dataSaved);
    }

    /**
     *
     * @param pageable
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Periode>> findByPeriodiciteActif(Pageable pageable) {
        Page<Periode> liste = periodeService.findByPeriodiciteActif(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), liste);
        return ResponseEntity.ok().headers(headers).body(liste.getContent());
    }
}
