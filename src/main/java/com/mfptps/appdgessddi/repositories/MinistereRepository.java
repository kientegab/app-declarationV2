package com.mfptps.appdgessddi.repositories;

import java.util.Optional;

import com.mfptps.appdgessddi.entities.Ministere;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MinistereRepository extends JpaRepository<Ministere, Long>{

    Optional<Ministere> findByCode(String code);
    
}
