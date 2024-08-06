package com.mefp.appdeclaration.service;

import com.mefp.appdeclaration.entities.Nature;
import com.mefp.appdeclaration.repositories.NatureRepository;
import com.mefp.appdeclaration.repositories.NatureSaisieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NatureService {
    @Autowired
    private final NatureRepository natureRepository;

    public NatureService(NatureRepository natureRepository) {
        this.natureRepository = natureRepository;
    }

    public Nature create(Nature nature){
        return natureRepository.save(nature);
    }

    public List<Nature> findAll(){return  natureRepository.findAll();}
    public Optional<Nature> findById(Long id){return natureRepository.findById(id);}

    public Optional<Nature> update(Long id, Nature nature){
        Optional<Nature> laNature= natureRepository.findById(id);
        if(laNature.isPresent()){
            Nature nature1= laNature.get();
            nature1.setCode(nature.getCode());
            nature1.setLibelle(nature.getLibelle());
            nature1.setValideDe(nature.getValideDe());
            nature1.setValideFin(nature.getValideFin());
            nature1.setDescription(nature.getDescription());
            return Optional.of(natureRepository.save(nature1)) ;
        }
        else {
            Optional.empty();
        }
        return laNature;
    }

    public Optional<Nature> delete(Long id){
        Optional<Nature> laNature= natureRepository.findById(id);
        if(laNature.isEmpty()) {
            natureRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("nature not found");
        }
        return laNature;
    }
}
