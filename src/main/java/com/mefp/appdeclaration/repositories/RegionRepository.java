package com.mefp.appdeclaration.repositories;
import com.mefp.appdeclaration.entities.Ministere;
import com.mefp.appdeclaration.entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {
    Optional<Region> findByCode(String code);
}
