package com.mfptps.appdgessddi.web;

import com.mfptps.appdgessddi.aop.utils.HeaderUtil;
import com.mfptps.appdgessddi.aop.utils.PaginationUtil;
import com.mfptps.appdgessddi.entities.Structure;
import com.mfptps.appdgessddi.service.StructureService;
import com.mfptps.appdgessddi.service.dto.StructureDTO;
import com.mfptps.appdgessddi.web.exceptions.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class StructureController {
    private final Logger log = LoggerFactory.getLogger(MinistereController.class);

    private static final String ENTITY_NAME = "structure";

    @Value("${application.name}")
    private String applicationName;
    /*

     */
    private final StructureService structureService;

    public StructureController(StructureService structureService) {
        this.structureService = structureService;
    }

    @PostMapping(path = "/structures")
    public ResponseEntity<Structure> create(@Valid @RequestBody StructureDTO structure) throws URISyntaxException {

        Structure structu = structureService.create(structure);
        log.debug("Création de la structure : {}", structure);
        return ResponseEntity.created(new URI("/api/structures/" + structu.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, structu.getId().toString()))
                .body(structu);
    }

    @DeleteMapping(path = "/structures/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("Suppression d'une structure : {}", id);
        structureService.delete(id);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                .build();
    }
    @PutMapping(path = "/structures")
    public ResponseEntity<Structure> updateStructure(@Valid @RequestBody Structure structure) throws URISyntaxException {
        log.debug("Mis à jour d'une structure : {}", structure);
        if (structure.getId() == null) {
            throw new BadRequestAlertException("Id invalide", ENTITY_NAME, "idnull");
        }
        Structure result = structureService.update(structure);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, structure.getId().toString()))
                .body(result);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Structure>> findAllStructure(Pageable pageable) {
        Page<Structure> structure = structureService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), structure);
        return ResponseEntity.ok().headers(headers).body(structure.getContent());
    }

}
