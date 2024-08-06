package com.mefp.appdeclaration.service.impl;

import com.mefp.appdeclaration.entities.FichePrevention;
import com.mefp.appdeclaration.repositories.FichePreventionRepository;
import com.mefp.appdeclaration.service.FichePreventionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FichePrevSaisieImpl implements FichePreventionService {
    private final FichePreventionRepository fichePreventionRepository;

    public FichePrevSaisieImpl(FichePreventionRepository fichePreventionRepository) {
        this.fichePreventionRepository = fichePreventionRepository;
    }

    public FichePrevention createPrevention(FichePrevention fichePrevention) {
        return fichePreventionRepository.save(fichePrevention);
    }

    public List<FichePrevention> findAllPrevention() {
        return fichePreventionRepository.findAll();
    }

    public Optional<FichePrevention> updatePrevention(FichePrevention fichePrevention, Long id) {
        Optional<FichePrevention> laFiche = fichePreventionRepository.findById(id);
        if (laFiche.isPresent()) {
            FichePrevention fichePrevention1 = laFiche.get();
            fichePrevention1.setActivite(fichePrevention.getActivite());
            fichePrevention1.setStructure(fichePrevention.getStructure());
            fichePrevention1.setDatePrevention(fichePrevention.getDatePrevention());
            fichePrevention1.setDateDebut(fichePrevention.getDateDebut());
            fichePrevention1.setDateFin(fichePrevention.getDateFin());
            fichePrevention1.setLieuActivite(fichePrevention.getLieuActivite());
            fichePrevention1.setCibleActivite(fichePrevention.getCibleActivite());
            fichePrevention1.setNbFemme(fichePrevention.getNbFemme());
            fichePrevention1.setNbPerson(fichePrevention.getNbPerson());

            return  Optional.of(fichePreventionRepository.save(fichePrevention1));
        } else {
            return Optional.empty();
        }

    }
}

