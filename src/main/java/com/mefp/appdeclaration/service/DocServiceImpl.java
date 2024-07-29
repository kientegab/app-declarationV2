package bf.mefp.appDeclaration.appdgddeclaration.service;

import bf.mefp.appDeclaration.appdgddeclaration.config.ApplicationProperties;
import bf.mefp.appDeclaration.appdgddeclaration.config.utils;
import bf.mefp.appDeclaration.appdgddeclaration.entity.Declaration;
import bf.mefp.appDeclaration.appdgddeclaration.entity.Document;
import bf.mefp.appDeclaration.appdgddeclaration.repository.DeclarationRepository;
import bf.mefp.appDeclaration.appdgddeclaration.repository.DocumentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
//@AllArgsConstructor
public class DocServiceImpl implements DocService {
//    private final Logger  log = (Logger) LoggerFactory.getLogger(DocServiceImpl.class);
    @Autowired
    private   final DeclarationRepository declarationRepository;
    @Autowired
    private  final DocumentRepository documentRepository;

    @Autowired
    private final ApplicationProperties  applicationProperties= new ApplicationProperties();
    @Autowired
    private final utils utils=new utils();

    @Autowired
    public DocServiceImpl(DeclarationRepository declarationRepository, DocumentRepository documentRepository, ApplicationProperties applicationProperties, bf.mefp.appDeclaration.appdgddeclaration.config.utils utils){

        this.declarationRepository = declarationRepository;
        this.documentRepository = documentRepository;

    }



    @Override
    public Declaration upload(Declaration demandeDTO, MultipartFile[] fichiersJoint) throws Exception, IOException {
       Declaration demande = new Declaration();


//        if (demande.getDateDeclaration()== null) {
//           throw new Exception("Saisir la période de début et la période de fin de la demande.");
//        }

        if (fichiersJoint != null && fichiersJoint.length > 0) {
            Set<Document> documents = new HashSet<>() ;
            for (MultipartFile file : fichiersJoint) {
                if (!file.isEmpty()) {
                    String fileUri = utils.saveUploadFileToServer(applicationProperties.getAppUploadsStorage(), "declaration", file);
                   // String fileUri="document";
                    Document document = new Document();
                    document.setDeclaration(demande);
                    document.setUrl(fileUri);
                    document.setReference(2024 + "_DOC-" + System.currentTimeMillis());
                    document.setNomDocument(file.getName());
                    documents.add(document);
                }
            }
            demande.setDocuments(documents);
        }
        boolean b= true;
        demande.setVoyageur(demandeDTO.getVoyageur());
        demande.setDevise(demandeDTO.getDevise());
        demande.setMontantCFA(demandeDTO.getMontantCFA());
        demande.setMotifVoyage(demandeDTO.getMotifVoyage());
        demande.setNationalite(demandeDTO.getNationalite());
        demande.setCommentaire(demandeDTO.getCommentaire());
        demande.setDateDeclaration(demandeDTO.getDateDeclaration());
        demande.setMontant(demandeDTO.getMontant());
        demande.setJustification(demandeDTO.getJustification());
        demande.setEstDeclare(b);
        return declarationRepository.save(demande);
    }


}
