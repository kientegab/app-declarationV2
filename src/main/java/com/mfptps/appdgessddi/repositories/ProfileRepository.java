/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.repositories;

import java.util.Optional;

import com.mfptps.appdgessddi.entities.Profile;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author bieve
 */
public interface ProfileRepository extends JpaRepository<Profile, Long>{

    Optional<Profile> findByName(String name);

    @EntityGraph(attributePaths = "privileges")
    Optional<Profile> findOneWithPrivilegesByNameIgnoreCase(String name);

    @Modifying
    @Query(value = "DELETE FROM AGENT_PROFILE WHERE PROFILE_ID = :id", nativeQuery = true)
    int deleteProfileFromAgentAssociation(@Param("id") Long id);
    
    @Modifying
    @Query(value = "DELETE FROM PROFILE_PRIVILEGE WHERE PROFILE_ID = :id", nativeQuery = true)
    int deleteAssociatePrivilege(@Param("id") Long id);
    
}
