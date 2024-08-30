package com.mefp.appdeclaration.web;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mefp.appdeclaration.entities.Structure;
import com.mefp.appdeclaration.service.StructureService;
import com.mefp.appdeclaration.service.dto.ChangeMinistereDTO;
import com.mefp.appdeclaration.service.dto.StructureDTO;
import com.mefp.appdeclaration.utils.*;
import com.mefp.appdeclaration.web.exceptions.BadRequestAlertException;

@RestController
@RequestMapping(path = "/api/dgd")
public class StructureController {

    private final Logger log = LoggerFactory.getLogger(StructureController.class);

    private static final String ENTITY_NAME = "structure";

    @Value("${application.name}")
    private String applicationName;
    /*

     */
    private final StructureService structureService;



    public StructureController(StructureService structureService) {
        this.structureService = structureService;

    }

    /**
     * Access granted to RESP_DDII, DIR_DGESS, ADMIN
     *
     * @param structure
     * @return
     * @throws URISyntaxException
     */
    @PostMapping(path = "/structure")
    @PreAuthorize("hasAnyAuthority(\"" + AppUtil.RDDII + "\",\"" + AppUtil.DD + "\", \"" + AppUtil.ADMIN + "\")")
    public ResponseEntity<Structure> create(@Valid @RequestBody StructureDTO structure) throws URISyntaxException {

        Structure structu = structureService.create(structure);
        log.debug("Création de la structure : {}", structure);
        return ResponseEntity.created(new URI("/api/structures/" + structu.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, structu.getId().toString()))
                .body(structu);
    }

    @DeleteMapping(path = "/structure/{id}")
    @PreAuthorize("hasAnyAuthority(\"" + AppUtil.RDDII + "\",\"" + AppUtil.DD + "\", \"" + AppUtil.ADMIN + "\")")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("Suppression d'une structure : {}", id);
        structureService.delete(id);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                .build();
    }

    /**
     * Access granted to RESP_DDII, DIR_DGESS, ADMIN
     *
     * @param structure
     * @return
     * @throws URISyntaxException
     */
    @PutMapping(path = "/structure")
    @PreAuthorize("hasAnyAuthority(\"" + AppUtil.RDDII + "\",\"" + AppUtil.DD + "\", \"" + AppUtil.ADMIN + "\")")
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

//    @GetMapping(path = "/structure")
//    public ResponseEntity<Page<Structure>> findAllStructure(Pageable pageable) {
//        Page<Structure> structure = structureService.findAll(pageable);
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), structure);
//        return ResponseEntity.ok().headers(headers).body(structure);
//    }

    @GetMapping(path = "/structure")
    public ResponseEntity<List<Structure>> findAllStructures() {
        List<Structure> structure = structureService.findAllStructure();
        return ResponseEntity.ok(structure);
    }


    /**
     *
     * @param id : id of ministere
     * @return
     */


    @GetMapping(path = "/structure/{id}")
    public ResponseEntity<Structure> getStructureById(@PathVariable Long id) {
        log.debug("Consultation d une structure : {}", id);
        Optional<Structure> structureFound = structureService.get(id);
        return ResponseUtil.wrapOrNotFound(structureFound);
    }

    /**
     * Access granted to RESP_DDII, DIR_DGESS, ADMIN
     *
     * @param changeMinistereDTO
     * @return
     * @throws URISyntaxException
     */
    @PostMapping(path = "/structures/change-ministere")
    @PreAuthorize("hasAnyAuthority(\"" + AppUtil.RDDII + "\",\"" + AppUtil.DD + "\", \"" + AppUtil.ADMIN + "\")")
    public ResponseEntity<Structure> changeMinistere(@Valid @RequestBody ChangeMinistereDTO changeMinistereDTO) throws URISyntaxException {
        log.debug("Changement de ministere : {}", changeMinistereDTO);
        Structure result = structureService.changementMinistere(changeMinistereDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }
}
