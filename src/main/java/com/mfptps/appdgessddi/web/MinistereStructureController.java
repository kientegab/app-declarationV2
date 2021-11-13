package com.mfptps.appdgessddi.web;

import com.mfptps.appdgessddi.aop.utils.HeaderUtil;
import com.mfptps.appdgessddi.aop.utils.PaginationUtil;
import com.mfptps.appdgessddi.entities.MinistereStructure;
import com.mfptps.appdgessddi.service.MinistereStructureService;
import com.mfptps.appdgessddi.service.dto.MinistereStructureDTO;
import com.mfptps.appdgessddi.service.dto.StructureDTO;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

//    @GetMapping(path = "/ministere-structures")
//    public ResponseEntity<List<MinistereStructure>> findAllStructure(Pageable pageable) {
//        Page<MinistereStructure> structure = ministereStructureService.findAll(pageable);
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), structure);
//        return ResponseEntity.ok().headers(headers).body(structure.getContent());
//    }
    @GetMapping(path = "/ministere-structures")
    public ResponseEntity<List<StructureDTO>> findAllStructure(Pageable pageable) {
        Page<StructureDTO> structure = ministereStructureService.findAllBeta(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), structure);
        return ResponseEntity.ok().headers(headers).body(structure.getContent());
    }
}
