package com.mefp.appdeclaration.service;
import com.mefp.appdeclaration.entities.Region;
import com.mefp.appdeclaration.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionService {
    @Autowired
    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public Region createRegion(Region region){
        Region laRegion= regionRepository.save(region);
        return laRegion;
    }

    public List<Region> findAllRegion(){
        List<Region> laListe= regionRepository.findAll();
        return laListe;
    }

    public Long getRegionCount() {
        return regionRepository.count(); // Remplacez "activiteRepository" par le nom réel de votre repository
    }

    public Optional<Region> updateRegion(Long id, Region region){
        Optional<Region> optionalRegion= regionRepository.findById(id);
        if(optionalRegion.isPresent()){
            Region laRegion= optionalRegion.get();
            laRegion.setCode(region.getCode());
            laRegion.setLibelle(region.getLibelle());
            return Optional.of(regionRepository.save(laRegion));
        }else {
            return Optional.empty();
        }

    }

    public void deleteRegion(Long id) {
        Optional<Region> optionalRegion = regionRepository.findById(id);
        if (optionalRegion.isPresent()) {
            regionRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("region not found");
        }
    }
}
