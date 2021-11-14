/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.repositories;

import java.util.Optional;

import com.mfptps.appdgessddi.entities.Profile;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author bieve
 */
public interface ProfileRepository extends JpaRepository<Profile, Long>{

    Optional<Profile> findByName(String name);

    @EntityGraph(attributePaths = "permissions")
    Optional<Profile> findOneWithPermissionsByNameIgnoreCase(String name);
    
}
