package com.mfptps.appdgessddi.web;

import com.mfptps.appdgessddi.entities.Performance;
import com.mfptps.appdgessddi.service.PerformanceService;
import com.mfptps.appdgessddi.service.dto.PerformanceDTO;
import com.mfptps.appdgessddi.service.dto.PerformanceEntityDTO;
import com.mfptps.appdgessddi.service.dto.RequeteDTO;
import com.mfptps.appdgessddi.utils.HeaderUtil;
import com.mfptps.appdgessddi.utils.PaginationUtil;
import com.mfptps.appdgessddi.utils.ResponseUtil;
import com.mfptps.appdgessddi.web.exceptions.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Slf4j
@RestController
@RequestMapping(path = "/api/performances")
public class PerformanceController {

    private static final String ENTITY_NAME = "performance";

    @Value("${application.name}")
    private String applicationName;

    private final PerformanceService performanceService;

    public PerformanceController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    @PostMapping
    public ResponseEntity<Performance> createPerformance(@Valid @RequestBody PerformanceDTO performanceDTO) throws URISyntaxException {
        Performance performanceSaved = performanceService.create(performanceDTO);
        log.debug("Création d une performance : {}", performanceDTO);
        return ResponseEntity.created(new URI("/api/performances/" + performanceSaved.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, performanceSaved.getId().toString()))
                .body(performanceSaved);
    }

    @PutMapping
    public ResponseEntity<Performance> updatePerformance(@Valid @RequestBody Performance performance) throws URISyntaxException {
        log.debug("Mis à jour d'une performance : {}", performance);
        if (performance.getId() == null) {
            throw new BadRequestAlertException("Id invalide", ENTITY_NAME, "idnull");
        }
        Performance results = performanceService.update(performance);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, performance.getId().toString()))
                .body(results);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Performance> getPerformanceById(@PathVariable(name = "id") Long id) {
        log.debug("Consultation du Performance : {}", id);
        Optional<Performance> performanceFound = performanceService.get(id);
        return ResponseUtil.wrapOrNotFound(performanceFound);
    }

    /**
     * Recherche la performance d'une structure
     *
     * ! NE PAS RENSEIGNER ministerId
     *
     * @param requete : Renseigner la structure uniquement (si besoin de sa
     * performance de l'exercice en cours) ou renseigner la structure et
     * l'exercice (si besoin de sa performance pour un exercice donne)
     *
     * @return
     */
    @PostMapping(path = "/structure")
    public ResponseEntity<PerformanceEntityDTO> getPerformanceByStructure(@RequestBody RequeteDTO requete) {
        log.debug("Consultation de la Performance de la structure: {}", requete.getStructureId());
        Optional<PerformanceEntityDTO> performanceFound = null;
        if (requete.getStructureId() != null && requete.getExerciceId() != null) {
            performanceFound = performanceService.getByStructure(requete.getStructureId(), requete.getExerciceId());
        } else if (requete.getStructureId() != null && requete.getExerciceId() == null) {
            performanceFound = performanceService.getByStructureAndExerciceENCOURS(requete.getStructureId());
        } else {
            throw new BadRequestAlertException("Veuillez renseigner les bon paramètres.", ENTITY_NAME, "idincorrects");
        }
        return ResponseUtil.wrapOrNotFound(performanceFound);
    }

    /**
     * Liste les performances (de toutes les structures) d'un ministere
     *
     * ! NE PAS RENSEIGNER structureId
     *
     * @param requete : Renseigner le ministere uniquement (si besoin de sa
     * performance de l'exercice en cours) ou renseigner le ministere et
     * l'exercice (si besoin de sa performance pour un exercice donne)
     *
     * @return
     */
    @PostMapping(path = "/ministere")
    public ResponseEntity<List<PerformanceEntityDTO>> findPerformanceByMinistere(@RequestBody RequeteDTO requete, Pageable pageable) {
        log.debug("Consultation de la Performance du Ministere: {}", requete.getMinisterId());
        Page<PerformanceEntityDTO> performancesFound = null;
        if (requete.getMinisterId() != null && requete.getExerciceId() != null) {
            performancesFound = performanceService.findAllByMinistere(requete.getMinisterId(), requete.getExerciceId(), pageable);
        } else if (requete.getMinisterId() != null && requete.getExerciceId() == null) {
            performancesFound = performanceService.findAllByMinistereAndExerciceENCOURS(requete.getMinisterId(), pageable);
        } else {
            throw new BadRequestAlertException("Veuillez renseigner les bon paramètres.", ENTITY_NAME, "idincorrects");
        }

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), performancesFound);
        return ResponseEntity.ok().headers(headers).body(performancesFound.getContent());
    }

    @GetMapping
    public ResponseEntity<List<Performance>> findAllPerformance(Pageable pageable) {
        Page<Performance> performances = performanceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), performances);
        return ResponseEntity.ok().headers(headers).body(performances.getContent());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("Suppression d'une performance : {}", id);
        performanceService.delete(id);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                .build();
    }
}
