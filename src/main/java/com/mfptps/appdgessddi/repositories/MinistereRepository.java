package com.mfptps.appdgessddi.repositories;

import com.mfptps.appdgessddi.entities.Ministere;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface MinistereRepository extends JpaRepository<Ministere, Long>{

    Ministere findByCode(String code);

    /* @Modifying( ="DELETE FROM Ministere WHERE code :=code")
    void deleteByCode(String code); */
    
}
