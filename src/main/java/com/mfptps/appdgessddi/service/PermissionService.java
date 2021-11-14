package com.mfptps.appdgessddi.service;


import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import com.mfptps.appdgessddi.entities.Permission;

/**
 * Service Interface for managing {@link Permission}.
 */
public interface PermissionService {

    /**
     * Save a permission.
     *
     * @param permission the entity to save.
     * @return the persisted entity.
     */
    Permission save(Permission permission);

    /**
     * Get all the permission.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Permission> findAll(Pageable pageable);
    
    /**
     * Get all the permission.
     *
     * @return the list of entities.
     */
    List<Permission> getList();


    /**
     * Get the "id" permission.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Permission> findOne(Long id);

    /**
     * Delete the "id" permission.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
