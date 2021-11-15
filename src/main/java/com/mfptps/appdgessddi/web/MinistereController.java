package com.mfptps.appdgessddi.web;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.mfptps.appdgessddi.entities.Ministere;
import com.mfptps.appdgessddi.service.MinistereService;
import com.mfptps.appdgessddi.service.dto.MinistereDTO;
import com.mfptps.appdgessddi.utils.*;
import com.mfptps.appdgessddi.web.exceptions.BadRequestAlertException;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path = "/api")
public class MinistereController {

    private final Logger log = LoggerFactory.getLogger(MinistereController.class);

    private static final String ENTITY_NAME = "ministere";

    @Value("${application.name}")
    private String applicationName;
    
    private final MinistereService ministereService;

    public MinistereController(MinistereService ministereService) {
        this.ministereService = ministereService;
    }

    @PostMapping(path = "/ministeres")
    public ResponseEntity<Ministere> create(@Valid @RequestBody MinistereDTO ministere) throws URISyntaxException {
        
        Ministere min = ministereService.create(ministere);
        log.debug("Création du Ministere : {}", ministere);
        return ResponseEntity.created(new URI("/api/ministeres/" + min.getId()))
                             .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, min.getId().toString()))
                             .body(min);
    }

    @PutMapping(path = "/ministeres")
    public ResponseEntity<Ministere> updateMinistere(@Valid @RequestBody Ministere ministere) throws URISyntaxException {
        log.debug("Mis à jour du Ministere : {}", ministere);
        if (ministere.getId() == null) {
            throw new BadRequestAlertException("Id invalide", ENTITY_NAME, "idnull");
        }
        Ministere result = ministereService.update(ministere);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ministere.getId().toString()))
            .body(result);
    }

    @GetMapping(path = "/ministeres/{code}")
    public ResponseEntity<Ministere> getMinistere(@PathVariable(name = "code") String code) {
        log.debug("Consultation du Ministere : {}", code);
        Optional<Ministere> ministere = ministereService.get(code);
        return ResponseUtil.wrapOrNotFound(ministere);
    }

    @GetMapping
    public ResponseEntity<List<Ministere>> findAllMinisteres(Pageable pageable) {
        Page<Ministere> minsiteres = ministereService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), minsiteres);
        return ResponseEntity.ok().headers(headers).body(minsiteres.getContent());
    }

    @DeleteMapping(path = "/ministeres/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("Suppression du Ministere : {}", id);
        ministereService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
    
}
