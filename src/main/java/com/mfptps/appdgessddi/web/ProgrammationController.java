/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.web;

import com.mfptps.appdgessddi.entities.Programmation;
import com.mfptps.appdgessddi.service.ProgrammationService;
import com.mfptps.appdgessddi.service.dto.ProgrammationDTO;
import com.mfptps.appdgessddi.utils.*;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    /**
     *
     * @param programmationDTO
     * @return
     * @throws URISyntaxException
     */
    @PostMapping
    public ResponseEntity<Programmation> createProgrammation(@Valid @RequestBody ProgrammationDTO programmationDTO) throws URISyntaxException {
        Programmation saved = programmationService.create(programmationDTO);
        log.debug("Programmation d'une activité : {}", programmationDTO);
        return ResponseEntity.created(new URI("/api/programmations/" + saved.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, saved.getId().toString()))
                .body(saved);
    }

    /**
     *
     * @param structureId : id of Structure referency by ids in path
     * @param pageable
     * @return
     */
    @GetMapping(path = "/all/{ids}")
    public ResponseEntity<List<Programmation>> findAllProgrammations(@PathVariable(name = "ids", required = true) Long structureId, Pageable pageable) {
        Page<Programmation> programmations = programmationService.findAll(structureId, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), programmations);
        return ResponseEntity.ok().headers(headers).body(programmations.getContent());
    }

    /**
     *
     * @param structureId : id of Structure referency by ids in path
     * @param libelle : field libelle of Activite
     * @param pageable
     * @return
     */
    @GetMapping(path = "/libelle/{ids}")
    public ResponseEntity<List<Programmation>> findAllProgrammationsByLibelle(
            @PathVariable(name = "ids", required = true) Long structureId,
            @RequestParam(required = true, name = "libelle") String libelle, Pageable pageable) {
        Page<Programmation> programmations = programmationService.get(structureId, libelle, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), programmations);
        return ResponseEntity.ok().headers(headers).body(programmations.getContent());
    }

    /**
     *
     * @param structureId : id of Structure referency by ids in path
     * @param id : id of Programmation referency by idp in path
     * @return
     */
    @GetMapping(path = "/{ids}/{idp}")
    public ResponseEntity<Programmation> getProgrammationById(@PathVariable(name = "ids", required = true) Long structureId, @PathVariable(name = "idp", required = true) Long id) {
        log.debug("Consultation de la Programmation : {}", id);
        Optional<Programmation> programmation = programmationService.get(structureId, id);
        return ResponseUtil.wrapOrNotFound(programmation);
    }

    /**
     * Validation performed by RESP_STRUC
     *
     * @param structureId : id of Structure referency by ids in path
     * @param programmationId: id of Programmation referency by idp in path
     * @return
     */
    @PutMapping(path = "/validation-initial/{ids}/{idp}")
    public ResponseEntity<Programmation> initialValidation(@PathVariable(name = "ids", required = true) Long structureId,
            @PathVariable(name = "idp", required = true) Long programmationId) {
        log.debug("Validation initiale de la Programmation : {}", programmationId);
        Optional<Programmation> programmation = programmationService.validationInitial(structureId, programmationId);
        return ResponseUtil.wrapOrNotFound(programmation);
    }

    /**
     * Validation performed by RESP_DGESS
     *
     * @param programmationId : id of Programmation
     * @return
     */
    @PutMapping(path = "/validation-interne/{id}")
    public ResponseEntity<Programmation> internalValidation(@PathVariable(name = "id", required = true) Long programmationId) {
        log.debug("Validation interne de la Programmation : {}", programmationId);
        Optional<Programmation> programmation = programmationService.validationInterne(programmationId);
        return ResponseUtil.wrapOrNotFound(programmation);
    }

    /**
     * Validation performed by CASEM
     *
     * @param programmationId : id of Programmation
     * @return
     */
    @PutMapping(path = "/validation-finale/{id}")
    public ResponseEntity<Programmation> finalValidation(@PathVariable(name = "id", required = true) Long programmationId) {
        log.debug("Validation finale de la Programmation : {}", programmationId);
        Optional<Programmation> programmation = programmationService.validationFinal(programmationId);
        return ResponseUtil.wrapOrNotFound(programmation);
    }
}
