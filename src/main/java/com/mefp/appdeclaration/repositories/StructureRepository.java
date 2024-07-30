package com.mefp.appdeclaration.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mefp.appdeclaration.entities.Structure;
import com.mefp.appdeclaration.enums.TypeStructure;

public interface StructureRepository extends JpaRepository<Structure, Long> {

//     @Query("SELECT s.* FROM Structure s"
//             + "WHERE s.deleted = false")
    Optional<Structure> findStructureById(Long tacheId);


    

}
