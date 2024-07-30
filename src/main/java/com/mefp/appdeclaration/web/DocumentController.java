package com.mefp.appdeclaration.web;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mefp.appdeclaration.entities.Declaration;
import com.mefp.appdeclaration.service.DocService;
import com.mefp.appdeclaration.service.dto.DeclarationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/dgd")
public class DocumentController {

   // private final Logger  log = (Logger) LoggerFactory.getLogger(DocumentController.class);
   // @Value("${application.name}")
    //private String applicationName;
    private static final String ENTITY_NAME = "declaration";
    @Autowired
    private final DocService  docService;
    @Autowired
    private final com.mefp.appdeclaration.service.DocService  documentService;

    public DocumentController(DocService docService, DocService documentService) {
        this.docService = docService;
        this.documentService = documentService;
    }


    @PostMapping("/demandes")
    //  @PreAuthorize("hasAnyAuthority(\"" + AppUtil.ADMIN + "\")")
    public ResponseEntity<DeclarationDTO> create(@Validated @RequestParam(value = "declaration") String demandeJson, @RequestParam(value = "files", required = false) MultipartFile[] fichiersJoint) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        DeclarationDTO demande = new DeclarationDTO();
        try {
            demande = mapper.readValue(demandeJson, DeclarationDTO.class);
        } catch (Exception e) {
//            log.error("Failed to parse string to Demande DTO", e);

            e.printStackTrace();
            //throw new CustomException("Echec lors du formatage des donnees du formulaire");
        }
        DeclarationDTO dem = documentService.upload(demande,fichiersJoint);
        return ResponseEntity.created(new URI("/api/demandes/" + dem.getId()) )
//                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, dem.getId().toString()))
                .body(dem);
    }
}
