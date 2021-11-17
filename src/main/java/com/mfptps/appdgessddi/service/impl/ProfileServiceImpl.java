package com.mfptps.appdgessddi.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import com.mfptps.appdgessddi.entities.Profile;
import com.mfptps.appdgessddi.repositories.ProfileRepository;
import com.mfptps.appdgessddi.service.ProfileService;

/**
 * Service Implementation for managing {@link Profile}.
 */
@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

    private final Logger log = LoggerFactory.getLogger(ProfileServiceImpl.class);

    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Profile save(Profile profile) {
        log.debug("Request to save Profile : {}", profile);
        return profileRepository.save(profile);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Profile> findAll(Pageable pageable) {
        log.debug("Request to get all Profiles");
        return profileRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Profile> findOne(Long id) {
        log.debug("Request to get Profile : {}", id);
        return profileRepository.findById(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.debug("Request to delete Profile : {}", id);
        /* profileRepository.deleteProfileFromUserAssociation(id);
        profileRepository.deleteAssociateAction(id); */
        profileRepository.deleteById(id);
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<Profile> getProfilerWithActionsByName(String name) {
        // return profileRepository.findOneWithActionsByNameIgnoreCase(name);
        return null;
    }
}