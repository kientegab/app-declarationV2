package com.mefp.appdeclaration.service;


//import jakarta.transaction.Transactional;
import com.mefp.appdeclaration.config.ApplicationProperties;
import com.mefp.appdeclaration.config.utils;
import com.mefp.appdeclaration.entities.Declaration;
import com.mefp.appdeclaration.entities.DeviseMontantdto;
import com.mefp.appdeclaration.entities.Document;
import com.mefp.appdeclaration.entities.dto.Declarationdto;
import com.mefp.appdeclaration.repositories.DeclarationRepository;
import com.mefp.appdeclaration.repositories.DeviseMontantRepository;
import com.mefp.appdeclaration.repositories.DocumentRepository;
import com.mefp.appdeclaration.service.dto.DeclarationDTO;
import com.mefp.appdeclaration.service.mapper.DeclarationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


@Service
@Transactional
//@AllArgsConstructor
public class DocServiceImpl implements DocService {
//    private final Logger  log = (Logger) LoggerFactory.getLogger(DocServiceImpl.class);
    @Autowired
    private   final DeclarationRepository  declarationRepository;
    @Autowired
    private  final DocumentRepository  documentRepository;

    private final DeviseMontantRepository deviseMontantRepository;
    private final DeclarationMapper declarationMapper;
    @Autowired
    private final ApplicationProperties  applicationProperties= new ApplicationProperties();
    @Autowired
    private final utils utils=new utils();

    @Autowired
    public DocServiceImpl(DeclarationRepository declarationRepository, DocumentRepository documentRepository, ApplicationProperties applicationProperties, DeviseMontantRepository deviseMontantRepository, DeclarationMapper declarationMapper){

        this.declarationRepository = declarationRepository;
        this.documentRepository = documentRepository;

        this.deviseMontantRepository = deviseMontantRepository;
        this.declarationMapper = declarationMapper;
    }



    public DeclarationDTO upload(DeclarationDTO demandeDTO, MultipartFile[] fichiersJoint) throws Exception, IOException {
        Declaration declaration = new Declaration();
        DeviseMontantdto deviseMontantdto=new DeviseMontantdto();

        if (demandeDTO.getVoyageur()== null) {
            throw new Exception("Veuillez sélectionner d'abord un voyageur.");
        }

        boolean b= true;
        declaration.setVoyageur(demandeDTO.getVoyageur());
        declaration.setMontantCFA(demandeDTO.getMontantCFA());
        declaration.setMotifVoyage(demandeDTO.getMotifVoyage());
        declaration.setNationalite(demandeDTO.getNationalite());
        declaration.setCommentaire(demandeDTO.getCommentaire());
        declaration.setDateDeclaration(demandeDTO.getDateDeclaration());
        declaration.setJustification(demandeDTO.getJustification());
        declaration.setEstDeclare(b);
        declarationRepository.save(declaration);

        if (fichiersJoint != null && fichiersJoint.length > 0) {
            Set<Document> documents = new HashSet<>() ;
            for (MultipartFile file : fichiersJoint) {
                if (!file.isEmpty()) {
                    String fileUri = utils.saveUploadFileToServer(applicationProperties.getAppUploadsStorage(), "declaration", file);
                    // String fileUri="document";
                    Document document = new Document();
                    document.setDeclaration(declaration);
                    document.setUrl(fileUri);
                    document.setReference(2024 + "_DOC-" + System.currentTimeMillis());
                    document.setNomDocument(file.getName());
                    documentRepository.save(document);
                    documents.add(document);
                }

            }
        }

        if(!demandeDTO.getDeviseMontants().isEmpty()){
            for(DeviseMontantdto deviseMontantdto1:demandeDTO.getDeviseMontants()){
                DeviseMontantdto deviseMontantdto2=new DeviseMontantdto();
                deviseMontantdto2.setDevise(deviseMontantdto1.getDevise());
                deviseMontantdto2.setMontant(deviseMontantdto1.getMontant());
                deviseMontantdto2.setDeclaration(declaration);
                deviseMontantRepository.save(deviseMontantdto2);
            }
        }


        DeclarationDTO declarationdto=declarationMapper.toDTO(declaration);
        declarationdto.setDocuments(declarationdto.getDocuments());
        declarationdto.setDeviseMontants(declarationdto.getDeviseMontants());
        return declarationdto;
    }



}
