package com.mefp.appdeclaration.service.impl;

import com.mefp.appdeclaration.config.utils;
import com.mefp.appdeclaration.entities.*;
import com.mefp.appdeclaration.entities.dto.*;
import com.mefp.appdeclaration.repositories.*;
import com.mefp.appdeclaration.service.AgentService;
import com.mefp.appdeclaration.service.FicheSaisieService;
import com.mefp.appdeclaration.service.dto.FicheSaisieDTO;
import com.mefp.appdeclaration.service.dto.FicheSaisieCompDTO;
import com.mefp.appdeclaration.service.mapper.FicheSaisieCompMapper;
import com.mefp.appdeclaration.service.mapper.FicheSaisieMapper;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.core.io.ResourceLoader;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

@Service
@Transactional
public class FicheSaisieServiceImpl implements FicheSaisieService {


    @Autowired
    private  final FicheSaisieRepository ficheSaisieRepository;
    @Autowired
    private  final NatureSaisieRepository natureSaisieRepository;
    @Autowired
    private final ProcedeSaisieRepository procedeSaisieRepository;
    @Autowired
    private final IntervSaisieRepository intervSaisieRepository;
    @Autowired
    private  final FicheSaisieMapper ficheSaisieMapper;
    private final FicheSaisieCompMapper ficheSaisieCompMapper;
    private  static int numIncrement= 0001;
    private final AgentService agentService;
    private final StructureRepository  structureRepository;

    private final ResourceLoader resourceLoader;

   private final DestinationRepository destinationRepository;
   private final InterpeleRepository interpeleRepository;

    public FicheSaisieServiceImpl(FicheSaisieRepository ficheSaisieRepository, NatureSaisieRepository natureSaisieRepository, ProcedeSaisieRepository procedeSaisieRepository, IntervSaisieRepository intervSaisieRepository, FicheSaisieMapper ficheSaisieMapper, FicheSaisieCompMapper  ficheSaisieCompMapper, AgentService agentService, StructureRepository structureRepository, ResourceLoader resourceLoader, DestinationRepository destinationRepository, InterpeleRepository interpeleRepository) {
        this.ficheSaisieRepository = ficheSaisieRepository;
        this.natureSaisieRepository = natureSaisieRepository;
        this.procedeSaisieRepository = procedeSaisieRepository;
        this.intervSaisieRepository = intervSaisieRepository;
        this.ficheSaisieMapper = ficheSaisieMapper;
        this.ficheSaisieCompMapper = ficheSaisieCompMapper;
        this.agentService = agentService;
        this.structureRepository = structureRepository;
        this.resourceLoader = resourceLoader;
        this.destinationRepository = destinationRepository;
        this.interpeleRepository = interpeleRepository;
    }

    public  String numeroSaisie(String matricule){
        int val= numIncrement+1;
        String structureSigle= this.agentService.getStructureSigle(matricule);
        String numeroSaisie= structureSigle+ utils.getCurrentYear()+val;
        return  numeroSaisie;
    }

    @Override
    public FicheSaisieCompDTO ajouter(FicheSaisieCompDTO ficheSaisieCompDTO){
        FicheSaisie ficheSaisie=ficheSaisieCompDTO.getFiche();
        Destination destination= ficheSaisieCompDTO.getDestination();
        Interpele interpele= ficheSaisieCompDTO.getInterpele();
        Destination laDestination=new Destination();
        Interpele linterpele= new Interpele();
        if(destination.getExportation()!=null){
            laDestination.setId(destination.getId());
            laDestination.setExportation(destination.getExportation());
            laDestination.setVente(destination.getVente());
            laDestination.setUsage(destination.getUsage());
            laDestination.setFiche(ficheSaisie);
            destinationRepository.save(laDestination);
        }
        if(interpele.getNombre()!=null){
            linterpele.setId(interpele.getId());
            linterpele.setNombre(interpele.getNombre());
            linterpele.setNationalite(interpele.getNationalite());
            linterpele.setSexe(interpele.getSexe());
            linterpele.setTranche(interpele.getTranche());
            linterpele.setSuiteDonne(interpele.getSuiteDonne());
            linterpele.setFiche(ficheSaisie);
            interpeleRepository.save(linterpele);
        }

        FicheSaisieCompDTO ficheSaisieCompDTO1= ficheSaisieCompMapper.toDTO(ficheSaisie);
        ficheSaisieCompDTO1.setDestination(ficheSaisieCompDTO.getDestination());
        ficheSaisieCompDTO1.setInterpele(ficheSaisieCompDTO.getInterpele());
        return ficheSaisieCompDTO1;
    }
    @Override
    public FicheSaisieDTO create(FicheSaisieDTO ficheSaisieDTO, String matricule) {
        FicheSaisie ficheSaisie= new FicheSaisie();
        ficheSaisie.setDateSaisie(ficheSaisieDTO.getDateSaisie());
        ficheSaisie.setNumSaisie(numeroSaisie(matricule));
        ficheSaisie.setAnneeSaisie(utils.getCurrentYear());
        ficheSaisie.setLieuSaisie(ficheSaisieDTO.getLieuSaisie());
        ficheSaisie.setItineraire(ficheSaisieDTO.getItineraire());
        ficheSaisie.setStructureSaisie(ficheSaisieDTO.getStructureSaisie());
        ficheSaisie.setCommentaire(ficheSaisieDTO.getCommentaire());
        ficheSaisieRepository.save(ficheSaisie);

        if(!ficheSaisieDTO.getNature().isEmpty()){
            for(NatureSaisie natureSaisie: ficheSaisieDTO.getNature()){
                NatureSaisie natureSaisie1= new NatureSaisie();
                natureSaisie1.setNature(natureSaisie.getNature());
                natureSaisie1.setPoids(natureSaisie.getPoids());
                natureSaisie1.setValeur(natureSaisie.getValeur());
                natureSaisie1.setFicheSaisie(ficheSaisie);
                natureSaisieRepository.save(natureSaisie1);
            }
        }
        if(!ficheSaisieDTO.getProcede().isEmpty()){
            for(ProcedeSaisie procedeSaisie: ficheSaisieDTO.getProcede()){
                ProcedeSaisie procedeSaisie1= new ProcedeSaisie();
                procedeSaisie1.setProcede(procedeSaisie.getProcede());
                procedeSaisie1.setFicheSaisie(ficheSaisie);
                procedeSaisieRepository.save(procedeSaisie1);
            }
        }
        if(!ficheSaisieDTO.getIntervenant().isEmpty())
        {
            for(IntervenantSaisie intervenantSaisie: ficheSaisieDTO.getIntervenant())
            {
                IntervenantSaisie intervenantSaisie1=new IntervenantSaisie();
                intervenantSaisie1.setStructureIntervenant(intervenantSaisie.getStructureIntervenant());
                intervenantSaisie1.setIdentiteIntervenant(intervenantSaisie.getIdentiteIntervenant());
                intervenantSaisie1.setContactIntervenant(intervenantSaisie.getContactIntervenant());
                intervenantSaisie1.setFicheSaisie(ficheSaisie);
                intervSaisieRepository.save(intervenantSaisie1);
            }
        }


        FicheSaisieDTO ficheSaisieDTO1= ficheSaisieMapper.toDTO(ficheSaisie);
        ficheSaisieDTO1.setNature(ficheSaisieDTO.getNature());
        ficheSaisieDTO1.setProcede(ficheSaisieDTO.getProcede());
        ficheSaisieDTO1.setIntervenant(ficheSaisieDTO.getIntervenant());
        return ficheSaisieDTO1;
    }
   @Override
    public List<FicheSaisie> findAll(){
      return ficheSaisieRepository.findAll();
    }

   public List<EtatSaisie> findPersonalise(Long id){
       return ficheSaisieRepository.findPersonalise(id);
   }

    @Override
    public void exportDeclaration(OutputStream outputStream){

        try {
            //recup logo
            InputStream imgLogo = resourceLoader.getResource("classpath:logo_douane.jpeg").getInputStream();
            //recup struct..
            Structure lastructure = structureRepository.findById(1L).orElseThrow(() -> new RuntimeException("structure inexistante"));
            List<SaisiePrintList> listSaisies = new ArrayList<>();
            //List<FicheSaisie> ficheSaisies = ficheSaisieRepository.findAll();
            List<EtatSaisie> ficheSaisies = ficheSaisieRepository.findPersonalise(1l);
            SaisiePrintList saisiePrintList = null;
            for (EtatSaisie fiche : ficheSaisies) {
                saisiePrintList = new SaisiePrintList();
                saisiePrintList.setDateSaisie(fiche.getDateSaisie().toString());
                saisiePrintList.setNumSaisie(fiche.getNumSaisie());
                saisiePrintList.setLieuSaisie(fiche.getLieuSaisie());
                saisiePrintList.setItineraire(fiche.getItineraire());
                saisiePrintList.setNature(fiche.getNature());
                saisiePrintList.setProcede(fiche.getProcede());
                saisiePrintList.setPoids(fiche.getTotalPoids().toString());
                saisiePrintList.setValeur(fiche.getTotalValeur().toString());
                listSaisies.add(saisiePrintList);
            }
            // conteneur de données de base à imprimer
            SaisiePrintdto saisiePrintdto = new SaisiePrintdto(imgLogo, lastructure.getRegion().getLibelle(), lastructure.getLibelle(), listSaisies);

            InputStream inputStream = this.getClass().getResourceAsStream("/ficheSaisie.jasper");

            JRDataSource jRDataSource = new JRBeanCollectionDataSource(Arrays.asList(saisiePrintList));

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
    @Override
    public Optional<FicheSaisieDTO> update(FicheSaisieDTO ficheSaisieDTO, Long id){
        Optional<FicheSaisie> laFiche= ficheSaisieRepository.findById(id);
        if(!laFiche.isPresent()){
            FicheSaisie ficheSaisie=laFiche.get();
            ficheSaisie.setDateSaisie(ficheSaisieDTO.getDateSaisie());
            ficheSaisie.setNumSaisie(ficheSaisieDTO.getNumSaisie());
            ficheSaisie.setAnneeSaisie(ficheSaisieDTO.getAnneeSaisie());
            ficheSaisie.setLieuSaisie(ficheSaisieDTO.getLieuSaisie());
            ficheSaisie.setItineraire(ficheSaisieDTO.getItineraire());
            ficheSaisie.setStructureSaisie(ficheSaisieDTO.getStructureSaisie());
            ficheSaisie.setCommentaire(ficheSaisieDTO.getCommentaire());
            Optional.of(ficheSaisieRepository.save(ficheSaisie));

            if(!ficheSaisieDTO.getNature().isEmpty()){
                for(NatureSaisie natureSaisie: ficheSaisieDTO.getNature()){
                    NatureSaisie natureSaisie1= new NatureSaisie();
                    natureSaisie1.setNature(natureSaisie.getNature());
                    natureSaisie1.setPoids(natureSaisie.getPoids());
                    natureSaisie1.setValeur(natureSaisie.getValeur());
                    natureSaisie1.getFicheSaisie(ficheSaisie);
                    natureSaisieRepository.save(natureSaisie1);
                }
            }
            if(!ficheSaisieDTO.getProcede().isEmpty()){
                for(ProcedeSaisie procedeSaisie: ficheSaisieDTO.getProcede()){
                    ProcedeSaisie procedeSaisie1= new ProcedeSaisie();
                    procedeSaisie1.setProcede(procedeSaisie.getProcede());
                    procedeSaisie1.setFicheSaisie(ficheSaisie);
                    procedeSaisieRepository.save(procedeSaisie1);
                }
            }
            if(!ficheSaisieDTO.getIntervenant().isEmpty())
            {
                for(IntervenantSaisie intervenantSaisie: ficheSaisieDTO.getIntervenant())
                {
                    IntervenantSaisie intervenantSaisie1=new IntervenantSaisie();
                    intervenantSaisie1.setStructureIntervenant(intervenantSaisie.getStructureIntervenant());
                    intervenantSaisie1.setIdentiteIntervenant(intervenantSaisie.getIdentiteIntervenant());
                    intervenantSaisie1.setContactIntervenant(intervenantSaisie.getContactIntervenant());
                    intervenantSaisie1.setFicheSaisie(ficheSaisie);
                    intervSaisieRepository.save(intervenantSaisie1);
                }
            }


            FicheSaisieDTO ficheSaisieDTO1= ficheSaisieMapper.toDTO(ficheSaisie);
            ficheSaisieDTO1.setNature(ficheSaisieDTO.getNature());
            ficheSaisieDTO1.setProcede(ficheSaisieDTO.getProcede());
            ficheSaisieDTO1.setIntervenant(ficheSaisieDTO.getIntervenant());
            return Optional.of(ficheSaisieDTO1);
        }

        else {
          return  Optional.empty();
        }
    }

    @Override
    public Optional<FicheSaisie> findFicheSaisieById(Long id){
        FicheSaisie laFiche= ficheSaisieRepository.findFicheSaisieById(id);
        return Optional.of(laFiche);
    }
}

