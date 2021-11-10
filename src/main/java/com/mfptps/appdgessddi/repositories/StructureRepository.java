package com.mfptps.appdgessddi.repositories;

import com.mfptps.appdgessddi.entities.Ministere;
import com.mfptps.appdgessddi.entities.Structure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface StructureRepository extends JpaRepository<Structure, Long> {

}
