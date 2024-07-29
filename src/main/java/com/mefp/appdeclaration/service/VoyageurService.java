package bf.mefp.appDeclaration.appdgddeclaration.service;

import bf.mefp.appDeclaration.appdgddeclaration.entity.Declaration;
import bf.mefp.appDeclaration.appdgddeclaration.entity.Structure;
import bf.mefp.appDeclaration.appdgddeclaration.entity.Voyageur;
import bf.mefp.appDeclaration.appdgddeclaration.entity.dto.Declarationdto;
import bf.mefp.appDeclaration.appdgddeclaration.entity.dto.ListDeclaration;
import bf.mefp.appDeclaration.appdgddeclaration.entity.dto.ListVoyageur;
import bf.mefp.appDeclaration.appdgddeclaration.entity.dto.Voyageurdto;
import bf.mefp.appDeclaration.appdgddeclaration.repository.StructureRepository;
import bf.mefp.appDeclaration.appdgddeclaration.repository.VoyageurRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

@Service
public class VoyageurService {
  @Autowired
    private final VoyageurRepository voyageurRepository;
    private final ResourceLoader resourceLoader;
    private final StructureRepository structureRepository;

    public VoyageurService(VoyageurRepository voyageurRepository, ResourceLoader resourceLoader, StructureRepository structureRepository) {
        this.voyageurRepository = voyageurRepository;
        this.resourceLoader = resourceLoader;
        this.structureRepository = structureRepository;
    }

    public Voyageur createVoayageur(Voyageur voyageur){
        Voyageur laVoyageur=voyageurRepository.save(voyageur);
        return laVoyageur;
    }

    public Optional<Voyageur> findById(Long id){
        return  voyageurRepository.findById(id);

    }

    public Optional<Voyageur> findByRefDocument(String refDocument){
        return voyageurRepository.findByRefDocument(refDocument);
    }
    public List<Voyageur> findAllVoyageur(){
        return voyageurRepository.findAll();
    }


    public Long getVoyageurCount() {
        return voyageurRepository.count(); // Remplacez "activiteRepository" par le nom réel de votre repository
    }
    public void deleteVoyageur(Long id){
        Optional<Voyageur> leVoyageur= voyageurRepository.findById(id);
        if(leVoyageur.isPresent()){
            voyageurRepository.deleteById(id);
        } else {
            throw new RuntimeException("ville not found");
        }
    }

    public Optional<Voyageur> updateVoyageur(Long id, Voyageur voyageur){
        Optional<Voyageur> optionalVoyageur= voyageurRepository.findById(id);
        if(optionalVoyageur.isPresent()){
            Voyageur leVoyageur= optionalVoyageur.get();
            leVoyageur.setNom(voyageur.getPrenom());
            leVoyageur.setPrenom(voyageur.getPrenom());
            leVoyageur.setNumIdentite(voyageur.getNumIdentite());
            leVoyageur.setRefDocument(voyageur.getRefDocument());
            leVoyageur.setProfession(voyageur.getProfession());
            return Optional.of(voyageurRepository.save((leVoyageur)));
        } else {
            return Optional.empty();
        }
    }

    public void exportVoyageur(OutputStream outputStream){

        try {
            // recup image
            InputStream imgLogo = resourceLoader.getResource("classpath:logo_douane.jpeg").getInputStream();
            //recup struct..
            Structure structure = structureRepository.findById(1L).orElseThrow( ()-> new RuntimeException("structure inexistante"));
            List<ListVoyageur> listVoyageurs= new ArrayList<>();
            List<Voyageur> voyageurs=voyageurRepository.findAll();
            for (Voyageur dec:voyageurs) {
                ListVoyageur voyageurdto= new ListVoyageur();
                 voyageurdto.setNom(dec.getNom());
                 voyageurdto.setPrenom(dec.getPrenom());
                 voyageurdto.setTypeIdentite(dec.getNumIdentite());
                 voyageurdto.setRefIdentite(dec.getRefDocument());
                 voyageurdto.setProfession(dec.getProfession());
                 voyageurdto.setTelephone(dec.getTelephone().toString());
                 voyageurdto.setVilleProvenance(dec.getVilleProvenance().getLibelle());
                 voyageurdto.setVilleDestination(dec.getVilleDestination());
                 listVoyageurs.add(voyageurdto);

            }
            // conteneur de données de base à imprimer
            Voyageurdto voyageurdto = new Voyageurdto(imgLogo, structure.getRegion().getLibelle(), structure.getLibelle(), listVoyageurs);

            InputStream inputStream = this.getClass().getResourceAsStream("/voyageur.jasper");

            JRDataSource jRDataSource = new JRBeanCollectionDataSource(Arrays.asList(voyageurdto)) ;

            Map<String, Object> map = new HashMap<>();

            JasperReport japerReport = (JasperReport) JRLoader.loadObject(inputStream);

            JasperPrint jaspertPrint = JasperFillManager.fillReport(japerReport, map, jRDataSource);

            JasperExportManager.exportReportToPdfStream(jaspertPrint, outputStream);

        } catch (JRException e) {
            throw new RuntimeException("Document indisponible, pour cause d'erreur inconnue ! Veuillez réessayer SVP."+e.getMessage());
        } catch (IOException ex) {
            System.out.println("Erreur. ex = "+ex);
        }
    }


}
