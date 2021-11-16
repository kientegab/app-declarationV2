package com.mfptps.appdgessddi.web;

import com.mfptps.appdgessddi.utils.*;
import com.mfptps.appdgessddi.entities.MinistereStructure;
import com.mfptps.appdgessddi.service.MinistereStructureService;
import com.mfptps.appdgessddi.service.dto.MinistereStructureDTO;
import java.net.URI;
import java.net.URISyntaxException;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class MinistereStructureController {

    private final Logger log = LoggerFactory.getLogger(MinistereController.class);

    private static final String ENTITY_NAME = "ministereStructure";

    @Value("${application.name}")
    private String applicationName;

    private final MinistereStructureService ministereStructureService;

    public MinistereStructureController(MinistereStructureService ministereStructureService) {
        this.ministereStructureService = ministereStructureService;
    }

    @PostMapping(path = "/ministere-structures")
    public ResponseEntity<MinistereStructure> create(@Valid @RequestBody MinistereStructureDTO ministereSDTO) throws URISyntaxException {

        MinistereStructure minS = ministereStructureService.create(ministereSDTO);
        log.debug("Création du Ministere_Strcture : {}", ministereSDTO);
        return ResponseEntity.created(new URI("/api/ministeres/" + minS.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, minS.getId().toString()))
                .body(minS);
    }
}
