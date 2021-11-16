package com.mfptps.appdgessddi.service.impl;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

import com.mfptps.appdgessddi.entities.Permission;
import com.mfptps.appdgessddi.repositories.PermissionRepository;
import com.mfptps.appdgessddi.service.PermissionService;

/**
 * Service Implementation for managing {@link Permission}.
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    private final Logger log = LoggerFactory.getLogger(PermissionServiceImpl.class);

    private final PermissionRepository permissionRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Permission save(Permission permission) {
        log.debug("Request to save Permission : {}", permission);
        return permissionRepository.save(permission);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Permission> findAll(Pageable pageable) {
        log.debug("Request to get all Permission");
        return permissionRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Permission> findOne(Long id) {
        log.debug("Request to get Permission : {}", id);
        return permissionRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Permission : {}", id);
        permissionRepository.deleteById(id);
    }

    @Override
    public List<Permission> getList() {
        log.debug("Request to get all Permission");
        return permissionRepository.streamAll().collect(Collectors.toList());
    }

}
