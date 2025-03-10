package com.mefp.appdeclaration.service;

import com.mefp.appdeclaration.entities.Pays;
import com.mefp.appdeclaration.repositories.PaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaysService {
    @Autowired

    private final PaysRepository  paysRepository;

    public PaysService(PaysRepository paysRepository) {
        this.paysRepository = paysRepository;
    }

    public Pays createPays(Pays pays){
        Pays newpays= paysRepository.save(pays);
        return newpays;
    }

    public  Optional<Pays> findById(Long id){
       return  paysRepository.findById(id);

    }

    public List<Pays> findAllPays(){
        return  paysRepository.findAll();
    }

    public Optional<Pays> updatePays(Long id, Pays pays){
        Optional<Pays> optionalPaysPays= paysRepository.findById(id);
        if(optionalPaysPays.isPresent()){
            Pays lepays= optionalPaysPays.get();
            lepays.setCode(pays.getCode());
            lepays.setLibelle(pays.getLibelle());
            lepays.setNationalite(pays.getNationalite());
            return Optional.of(paysRepository.save(lepays));
        }else {
            return Optional.empty();
        }

    }

    public void deletePays(Long id) {
        Optional<Pays> optionalPaysPays = paysRepository.findById(id);
        if (optionalPaysPays.isPresent()) {
            paysRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Pays not found");
        }
    }
}
