/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.web;

import com.mfptps.appdgessddi.aop.utils.HeaderUtil;
import com.mfptps.appdgessddi.aop.utils.PaginationUtil;
import com.mfptps.appdgessddi.aop.utils.ResponseUtil;
import com.mfptps.appdgessddi.entities.Programme;
import com.mfptps.appdgessddi.service.ProgrammeService;
import com.mfptps.appdgessddi.service.dto.ProgrammeDTO;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@RequestMapping(path = "/api/programmes")
public class ProgrammeController {

    private static final String ENTITY_NAME = "programme";

    @Value("${application.name}")
    private String applicationName;

    private final ProgrammeService programmeService;

    public ProgrammeController(ProgrammeService programmeService) {
        this.programmeService = programmeService;
    }

    /**
     *
     * @param programmeDto
     * @return
     * @throws URISyntaxException
     */
    @PostMapping
    public ResponseEntity<Programme> createProgramme(@Valid @RequestBody ProgrammeDTO programmeDto) throws URISyntaxException {

        log.debug("_________________Création du Programme : {}", programmeDto);
        Programme programmeSaved = programmeService.create(programmeDto);
        return ResponseEntity.created(new URI("/api/programmes/" + programmeSaved.getId_programme()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, programmeSaved.getId_programme().toString()))
                .body(programmeSaved);
    }

    /**
     *
     * @param programme : json Programme object
     * @return
     * @throws URISyntaxException
     */
    @PutMapping
    public ResponseEntity<Programme> updateProgramme(@Valid @RequestBody Programme programme) throws URISyntaxException {
        log.debug("Mis à jour du Programme : {}", programme);
        if (programme.getId_programme() == null) {
            throw new BadRequestAlertException("Id invalide", ENTITY_NAME, "idnull");
        }
        Programme result = programmeService.update(programme);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, programme.getId_programme().toString()))
                .body(result);
    }

    /**
     *
     * @param code : code of Programme to be found
     * @return
     */
    @GetMapping(path = "/{code}")
    public ResponseEntity<Programme> getProgrammeByCode(@PathVariable(name = "code") String code) {
        log.debug("Consultation du Programme : {}", code);
        Optional<Programme> programmeFound = programmeService.get(code);
        return ResponseUtil.wrapOrNotFound(programmeFound);
    }

    /**
     *
     * @param pageable
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Programme>> findAllProgrammes(Pageable pageable) {
        Page<Programme> programmes = programmeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), programmes);
        return ResponseEntity.ok().headers(headers).body(programmes.getContent());
    }

    /**
     *
     * @param id : id of Programme to be deleted
     * @return
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("Suppression du Programme : {}", id);
        programmeService.delete(id);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                .build();
    }
}
