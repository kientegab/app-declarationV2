package com.mefp.appdeclaration.service;
import com.mefp.appdeclaration.entities.Devise;
import com.mefp.appdeclaration.repositories.DeviseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviseService {
    @Autowired

    private  final DeviseRepository  deviseRepository;

    public DeviseService(DeviseRepository deviseRepository) {
        this.deviseRepository = deviseRepository;
    }

    public Devise createDevise(Devise devise){
        Devise laDevise= deviseRepository.save(devise);
        return  laDevise;
    }

    public List<Devise>  findAllDevise(){
        return  deviseRepository.findAll();
    }


    public Optional<Devise> updateDevise(Long id, Devise devise){
        Optional<Devise> optionalDevise= deviseRepository.findById(id);
        if(optionalDevise.isPresent()){
            Devise ladevise= optionalDevise.get();
            ladevise.setCode(devise.getCode());
            ladevise.setLibelle(devise.getLibelle());
            return Optional.of(deviseRepository.save(ladevise));
        }else {
            return Optional.empty();
        }

    }

    public void deleteDevise(Long id) {
        Optional<Devise> optionalPaysPays = deviseRepository.findById(id);
        if (optionalPaysPays.isPresent()) {
            deviseRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Devise not found");
        }
    }
}
