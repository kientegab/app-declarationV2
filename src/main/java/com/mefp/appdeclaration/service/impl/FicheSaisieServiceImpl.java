package com.mefp.appdeclaration.service.impl;

import com.mefp.appdeclaration.entities.FicheSaisie;
import com.mefp.appdeclaration.entities.IntervenantSaisie;
import com.mefp.appdeclaration.entities.NatureSaisie;
import com.mefp.appdeclaration.entities.ProcedeSaisie;
import com.mefp.appdeclaration.repositories.FicheSaisieRepository;
import com.mefp.appdeclaration.repositories.IntervSaisieRepository;
import com.mefp.appdeclaration.repositories.NatureSaisieRepository;
import com.mefp.appdeclaration.repositories.ProcedeSaisieRepository;
import com.mefp.appdeclaration.service.FicheSaisieService;
import com.mefp.appdeclaration.service.dto.FicheSaisieDTO;
import com.mefp.appdeclaration.service.mapper.FicheSaisieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
    public FicheSaisieServiceImpl(FicheSaisieRepository ficheSaisieRepository, NatureSaisieRepository natureSaisieRepository, ProcedeSaisieRepository procedeSaisieRepository, IntervSaisieRepository intervSaisieRepository, FicheSaisieMapper ficheSaisieMapper) {
        this.ficheSaisieRepository = ficheSaisieRepository;
        this.natureSaisieRepository = natureSaisieRepository;
        this.procedeSaisieRepository = procedeSaisieRepository;
        this.intervSaisieRepository = intervSaisieRepository;
        this.ficheSaisieMapper = ficheSaisieMapper;
    }

    @Override
    public FicheSaisieDTO create(FicheSaisieDTO ficheSaisieDTO) {
        FicheSaisie ficheSaisie= new FicheSaisie();
        ficheSaisie.setDateSaisie(ficheSaisieDTO.getDateSaisie());
        ficheSaisie.setNumSaisie(ficheSaisieDTO.getNumSaisie());
        ficheSaisie.setAnneeSaisie(Long.valueOf(ficheSaisieDTO.getDateSaisie().getYear()));
        ficheSaisie.setLieuSaisie(ficheSaisieDTO.getLieuSaisie());
        ficheSaisie.setItinéraire(ficheSaisieDTO.getItinéraire());
        ficheSaisie.setStructureSaisie(ficheSaisieDTO.getStructureSaisie());
        ficheSaisie.setCommentaire(ficheSaisieDTO.getCommentaire());
        ficheSaisieRepository.save(ficheSaisie);

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

    @Override
    public Optional<FicheSaisieDTO> update(FicheSaisieDTO ficheSaisieDTO, Long id){
        Optional<FicheSaisie> laFiche= ficheSaisieRepository.findById(id);
        if(!laFiche.isPresent()){
            FicheSaisie ficheSaisie=laFiche.get();

            ficheSaisie.setDateSaisie(ficheSaisieDTO.getDateSaisie());
            ficheSaisie.setNumSaisie(ficheSaisieDTO.getNumSaisie());
            ficheSaisie.setAnneeSaisie(Long.valueOf(ficheSaisieDTO.getDateSaisie().getYear()));
            ficheSaisie.setLieuSaisie(ficheSaisieDTO.getLieuSaisie());
            ficheSaisie.setItinéraire(ficheSaisieDTO.getItinéraire());
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
}

