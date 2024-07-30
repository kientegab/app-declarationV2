package com.mefp.appdeclaration.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mefp.appdeclaration.entities.Profile;
import com.mefp.appdeclaration.service.dto.ProfileDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ProfileService}.
 */
public interface ProfileService {

    /**
     * Save a profile.
     *
     * @param profile the entity to save.
     * @return the persisted entity.
     */
    Profile save(Profile profile);

    /**
     * Get all the profiles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    //Page<ProfileDTO> findAll(Pageable pageable);



    /**
     * Get the "id" profile.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Profile> findOne(Long id);

    /**
     * Delete the "id" profile.
     *
   //  * @param id the id of the entity.
     */
    void delete(Long id);

    Optional<Profile> getProfileWithPrivilegesByName(String name);

    List<Profile> getProfilByMatricule(String matricule);

    @Transactional(readOnly = true)
    Page<ProfileDTO> findAll(Pageable pageable);

    List<Profile> getList();
}
