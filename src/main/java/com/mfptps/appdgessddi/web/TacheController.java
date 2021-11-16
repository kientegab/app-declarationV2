/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.web;

import com.mfptps.appdgessddi.aop.utils.PaginationUtil;
import com.mfptps.appdgessddi.entities.Tache;
import com.mfptps.appdgessddi.service.TacheService;
import com.mfptps.appdgessddi.service.dto.TacheVM;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping(path = "/api/taches")
public class TacheController {

    private static final String ENTITY_NAME = "tache";

    @Value("${application.name}")
    private String applicationName;

    private final TacheService tacheService;

    public TacheController(TacheService tacheService) {
        this.tacheService = tacheService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<List<Tache>> findTacheByProgrammation(@PathVariable(name = "id", required = true) Long id, Pageable pageable) {
        Page<Tache> taches = tacheService.get(id, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), taches);
        return ResponseEntity.ok().headers(headers).body(taches.getContent());
    }

    @GetMapping(path = "/libelle")
    public ResponseEntity<List<Tache>> findTacheByLibelleAndProgrammation(@Valid @RequestBody TacheVM tache, Pageable pageable) {
        Page<Tache> taches = tacheService.get(tache.getLibelle(), tache.getProgrammationId(), pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), taches);
        return ResponseEntity.ok().headers(headers).body(taches.getContent());
    }
}
