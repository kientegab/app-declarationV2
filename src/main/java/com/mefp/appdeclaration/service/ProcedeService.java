package com.mefp.appdeclaration.service;

import com.mefp.appdeclaration.entities.Nature;
import com.mefp.appdeclaration.entities.Procede;
import com.mefp.appdeclaration.repositories.ProcedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProcedeService {
    @Autowired
    private final ProcedeRepository procedeRepository;

    public ProcedeService(ProcedeRepository procedeRepository) {
        this.procedeRepository = procedeRepository;
    }


    public Procede create(Procede procede){
        return procedeRepository.save(procede);
    }

    public List<Procede> findAll(){return  procedeRepository.findAll();}
    public Optional<Procede> findById(Long id){return procedeRepository.findById(id);}

    public Optional<Procede> update(Long id, Procede procede){
        Optional<Procede> leprocede= procedeRepository.findById(id);
        if(leprocede.isPresent()){
            Procede procede1= leprocede.get();
            procede1.setCode(procede.getCode());
            procede1.setLibelle(procede.getLibelle());
            procede1.setValideDe(procede.getValideDe());
            procede1.setValideFin(procede.getValideFin());
            procede1.setDescription(procede.getDescription());
            return Optional.of(procedeRepository.save(procede1)) ;
        }
        else {
            Optional.empty();
        }
        return leprocede;
    }

    public Optional<Procede> delete(Long id){
        Optional<Procede> leProcede= procedeRepository.findById(id);
        if(leProcede.isEmpty()) {
            procedeRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("nature not found");
        }
        return leProcede;
    }
}
