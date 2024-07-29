package bf.mefp.appDeclaration.appdgddeclaration.service;

import bf.mefp.appDeclaration.appdgddeclaration.entity.Declaration;
import bf.mefp.appDeclaration.appdgddeclaration.entity.StatDeclaration;
import bf.mefp.appDeclaration.appdgddeclaration.entity.Structure;
import bf.mefp.appDeclaration.appdgddeclaration.entity.dto.Declarationdto;
import bf.mefp.appDeclaration.appdgddeclaration.entity.dto.ListDeclaration;
import bf.mefp.appDeclaration.appdgddeclaration.entity.dto.PeriodeExport;
import bf.mefp.appDeclaration.appdgddeclaration.repository.DeclarationRepository;
import bf.mefp.appDeclaration.appdgddeclaration.repository.StructureRepository;
import bf.mefp.appDeclaration.appdgddeclaration.utils.ResponseMessage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

@Service
public  class DeclarationService {

    private final DeclarationRepository declarationRepository;

    private final ResourceLoader resourceLoader;

   private final StructureRepository structureRepository;

    public DeclarationService(DeclarationRepository declarationRepository, ResourceLoader resourceLoader, StructureRepository structureRepository) {
        this.declarationRepository = declarationRepository;
        this.resourceLoader = resourceLoader;
        this.structureRepository = structureRepository;
    }

    public Declaration createDeclaration(Declaration declaration){
        Declaration laDeclaration=declarationRepository.save(declaration);
        return laDeclaration;
    }

    public Optional<Declaration> findById(Long id){
        return  declarationRepository.findById(id);

    }
    public Long getDeclarationCount() {
        return declarationRepository.count(); // Remplacez "activiteRepository" par le nom réel de votre repository
    }

    public String getDeclarationByMonth(){
        return  declarationRepository.countDeclaration();
    }

    public List<Declaration> findAllDeclaration(){
        return declarationRepository.findAll();
    }
    public List<Declaration> findByVoyageurId(Long voyageurId){
       return  declarationRepository.findByVoyageurId(voyageurId);
    }

    public void deleteDeclaration(Long id){
        Optional<Declaration> laDeclaration= declarationRepository.findById(id);
        if(laDeclaration.isPresent()){
            declarationRepository.deleteById(id);
        } else {
            throw new RuntimeException("Declaration not found");
        }
    }

    public Optional<Declaration> updateDeclaration(Long id, Declaration declaration){
        Optional<Declaration> optionalDeclaration= declarationRepository.findById(id);
        if(optionalDeclaration.isPresent()){
            Declaration laDeclaration= optionalDeclaration.get();
            laDeclaration.setDateDeclaration(declaration.getDateDeclaration());
            laDeclaration.setMotifVoyage(declaration.getMotifVoyage());
            laDeclaration.setDevise(declaration.getDevise());
            laDeclaration.setMontant(declaration.getMontant());
            laDeclaration.setMontantCFA(declaration.getMontantCFA());
            laDeclaration.setJustification(declaration.getJustification());
            return Optional.of(declarationRepository.save((laDeclaration)));
        } else {
            return Optional.empty();
        }
    }

    public void exportDeclaration(PeriodeExport periodeExport, OutputStream outputStream){

            try {
                //
                InputStream imgLogo = resourceLoader.getResource("classpath:logo_douane.jpeg").getInputStream();
//recup struct..
                Structure structure = structureRepository.findById(1L).orElseThrow( ()-> new RuntimeException("structure inexistante"));
                List<ListDeclaration> listDeclarations= new ArrayList<>();
                List<Declaration> declarations=declarationRepository.findByPeriode(periodeExport.getDateDebut(), periodeExport.getDateFin());
                for (Declaration dec:declarations) {
                    ListDeclaration declarationdto= new ListDeclaration();
                    declarationdto.setNumero(dec.getId().toString());
                    declarationdto.setDateDeclaration(dec.getDateDeclaration().toString());
                    declarationdto.setNomComplet(dec.getVoyageur().getNom()+" "+ dec.getVoyageur().getPrenom());
                    //declarationdto.setNationalite("A DEFINIR");
                    declarationdto.setNationalite(dec.getNationalite());
                    declarationdto.setReferDocument(dec.getVoyageur().getRefDocument());
                    declarationdto.setTelephone(dec.getVoyageur().getTelephone().toString());
                    declarationdto.setProfession(dec.getVoyageur().getProfession());
                    declarationdto.setProvenance(dec.getVoyageur().getVilleProvenance().getLibelle());
                    declarationdto.setDestination(dec.getVoyageur().getVilleDestination());
                    declarationdto.setMotifVoyage(dec.getMotifVoyage());
                    declarationdto.setMontant(dec.getMontant().toString()+" "+ dec.getDevise().getCode());
                    declarationdto.setJustificatif(dec.getJustification());
                    declarationdto.setCommentaire(dec.getCommentaire());
                    listDeclarations.add(declarationdto);
                }
                // conteneur de données de base à imprimer
                Declarationdto declarationdto = new Declarationdto(imgLogo, structure.getRegion().getLibelle(), structure.getLibelle(), listDeclarations);

                InputStream inputStream = this.getClass().getResourceAsStream("/declaration.jasper");

                JRDataSource  jRDataSource = new JRBeanCollectionDataSource(Arrays.asList(declarationdto)) ;

                Map <String, Object> map = new HashMap<>();

                JasperReport  japerReport = (JasperReport) JRLoader.loadObject(inputStream);

                JasperPrint  jaspertPrint = JasperFillManager.fillReport(japerReport, map, jRDataSource);

                JasperExportManager.exportReportToPdfStream(jaspertPrint, outputStream);

            } catch (JRException e) {
                throw new RuntimeException("Document indisponible, pour cause d'erreur inconnue ! Veuillez réessayer SVP."+e.getMessage());
            } catch (IOException ex) {
                System.out.println("Erreur. ex = "+ex);
            }
        }


}
