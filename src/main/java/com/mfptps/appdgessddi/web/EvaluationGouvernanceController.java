package com.mfptps.appdgessddi.web;

import com.mfptps.appdgessddi.entities.EvaluationGouvernance;
import com.mfptps.appdgessddi.service.EvaluationGouvernanaceService;
import com.mfptps.appdgessddi.service.dto.EvaluationGouvernanceDTO;
import com.mfptps.appdgessddi.utils.HeaderUtil;
import com.mfptps.appdgessddi.utils.PaginationUtil;
import com.mfptps.appdgessddi.utils.ResponseUtil;
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
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/evaluat")
public class EvaluationGouvernanceController {


    private final Logger log = LoggerFactory.getLogger(EvaluationGouvernanceController.class);

    private static final String ENTITY_NAME = "EvaluationGouvernance";

    @Value("${application.name}")
    private String applicationName;

    private final EvaluationGouvernanaceService evaluationGouvernanaceService ;

    public EvaluationGouvernanceController(EvaluationGouvernanaceService evaluationGouvernanaceService) {
        this.evaluationGouvernanaceService = evaluationGouvernanaceService;
    }
    @PostMapping
    public ResponseEntity<EvaluationGouvernance> create(@Valid @RequestBody EvaluationGouvernanceDTO evaluationGouvernanceDTO) throws URISyntaxException {

        EvaluationGouvernance evaluationGouvernance = evaluationGouvernanaceService.create(evaluationGouvernanceDTO);
        log.debug("Création d evaluation de gouvernance : {}", evaluationGouvernanceDTO);
        return ResponseEntity.created(new URI("/api/evaluat/" + evaluationGouvernance.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, evaluationGouvernance.getId().toString()))
                .body(evaluationGouvernance);
    }
    @PutMapping
    public ResponseEntity<EvaluationGouvernance> updateEvaluat(@Valid @RequestBody EvaluationGouvernance evaluationGouvernance) throws URISyntaxException {
        log.debug("Mis à jour d evaluation gouvernance : {}", evaluationGouvernance);
        if (evaluationGouvernance.getId() == null) {
            throw new BadRequestAlertException("Id invalide", ENTITY_NAME, "idnull");
        }
        EvaluationGouvernance evaluationGouvernance1 = evaluationGouvernanaceService.update(evaluationGouvernance);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, evaluationGouvernance.getId().toString()))
                .body(evaluationGouvernance1);
    }
    @GetMapping
    public ResponseEntity<List<EvaluationGouvernance>> findAllEvaluats(Pageable pageable) {
        Page<EvaluationGouvernance> evaluationGouvernances = evaluationGouvernanaceService.get(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), evaluationGouvernances);
        return ResponseEntity.ok().headers(headers).body(evaluationGouvernances.getContent());
    }

    @GetMapping(path = "/gouvernance/{id}")
    public ResponseEntity<EvaluationGouvernance> getEvaluationById(@PathVariable(name = "id") Long id) {
      log.debug("Consultation d evaluation : {}", id);
       Optional<EvaluationGouvernance> evaluationGouvernanceOptional = evaluationGouvernanaceService.get(id);
       return ResponseUtil.wrapOrNotFound(evaluationGouvernanceOptional);
   }
}
