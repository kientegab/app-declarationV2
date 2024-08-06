package com.mefp.appdeclaration.service;

import com.mefp.appdeclaration.entities.Activite;
import com.mefp.appdeclaration.entities.Nature;
import com.mefp.appdeclaration.repositories.ActiviteRepository;
import com.mefp.appdeclaration.repositories.NatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActiviteService {
    @Autowired
    private final ActiviteRepository activiteRepository;

    public ActiviteService(ActiviteRepository activiteRepository) {
        this.activiteRepository = activiteRepository;
    }


    public Activite create(Activite  activite){
        return activiteRepository.save(activite);
    }

    public List<Activite> findAll(){return  activiteRepository.findAll();}
    public Optional<Activite> findById(Long id){return activiteRepository.findById(id);}

    public Optional<Activite> update(Long id, Activite activite){
        Optional<Activite> lactivite= activiteRepository.findById(id);
        if(lactivite.isEmpty()){
            Activite activite1= lactivite.get();

            activite1.setLibelle(activite.getLibelle());
            activite1.setValideDe(activite.getValideDe());
            activite1.setValideFin(activite.getValideFin());
            activite1.setDescription(activite.getDescription());
            return Optional.of(activiteRepository.save(activite1)) ;
        }
        else {
            Optional.empty();
        }
        return lactivite;
    }

    public Optional<Activite> delete(Long id){
        Optional<Activite> lactivite= activiteRepository.findById(id);
        if(lactivite.isEmpty()) {
            activiteRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("nature not found");
        }
        return lactivite;
    }
}
