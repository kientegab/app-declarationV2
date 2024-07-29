package bf.mefp.appDeclaration.appdgddeclaration.controller;

import bf.mefp.appDeclaration.appdgddeclaration.entity.Declaration;
import bf.mefp.appDeclaration.appdgddeclaration.service.DocService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/dgd")
public class DocumentController {

   // private final Logger  log = (Logger) LoggerFactory.getLogger(DocumentController.class);
   // @Value("${application.name}")
    //private String applicationName;
    private static final String ENTITY_NAME = "declaration";
    @Autowired
    private final DocService docService;
    @Autowired
    private final bf.mefp.appDeclaration.appdgddeclaration.service.DocService documentService;

    public DocumentController(DocService docService, DocService documentService) {
        this.docService = docService;
        this.documentService = documentService;
    }


    @PostMapping("/demandes")
    //  @PreAuthorize("hasAnyAuthority(\"" + AppUtil.ADMIN + "\")")
    public ResponseEntity<Declaration> create(@Validated @RequestParam(value = "declaration") String demandeJson, @RequestParam(value = "files", required = false) MultipartFile[] fichiersJoint) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        Declaration demande = new Declaration();
        try {
            demande = mapper.readValue(demandeJson, Declaration.class);
        } catch (Exception e) {
//            log.error("Failed to parse string to Demande DTO", e);

            e.printStackTrace();
            //throw new CustomException("Echec lors du formatage des donnees du formulaire");
        }
        Declaration dem = documentService.upload(demande,fichiersJoint);
        return ResponseEntity.created(new URI("/api/demandes/" + dem.getId()) )
//                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, dem.getId().toString()))
                .body(dem);
    }
}
