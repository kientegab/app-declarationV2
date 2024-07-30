package com.mefp.appdeclaration.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mefp.appdeclaration.entities.Profile;
import com.mefp.appdeclaration.repositories.ProfileRepository;
import com.mefp.appdeclaration.security.SecurityUtils;
import com.mefp.appdeclaration.service.ProfileService;
import com.mefp.appdeclaration.service.dto.ProfileDTO;
import com.mefp.appdeclaration.service.mapper.ProfileMapper;
import com.mefp.appdeclaration.utils.AppUtil;

/**
 * Service Implementation for managing {@link Profile}.
 */
@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

    private final Logger log = LoggerFactory.getLogger(ProfileServiceImpl.class);

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;


    public ProfileServiceImpl(ProfileRepository profileRepository, ProfileMapper profileMapper) {
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;

    }

    @Override
    public Profile save(Profile profile) {
        log.debug("Request to save Profile : {}", profile);
        return profileRepository.save(profile);
    }

    @Override
    public Optional<Profile> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProfileDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Profiles");
        Page<ProfileDTO> profiles;

        //Retourne tous les profiles au cas ou c'est un ADMIN qui tente la creation de compte
        if (SecurityUtils.isCurrentUserInRole(AppUtil.ADMIN)) {
            profiles = profileRepository.findAll(pageable).map(ProfileDTO::new);
        } else {//Retourne les profile FOCAL_STRUC et celui du user qui tente la creation de compte
            String matricule = SecurityUtils.getCurrentUserMatricule().get();
            List<Profile> mesProfiles = profileRepository.findUserProfiles(matricule);
            mesProfiles.add(profileRepository.findByName(AppUtil.FS.substring(5, AppUtil.FS.length())).get());

            List<ProfileDTO> list = new ArrayList<>();
            for (Profile profile : mesProfiles) {
                list.add(profileMapper.toDTO(profile));
            }
            profiles = new PageImpl<ProfileDTO>(list, pageable, mesProfiles.size());
            ;
        }
        return profiles;
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<Profile> getProfileWithPrivilegesByName(String name) {
        return profileRepository.findOneWithPrivilegesByNameIgnoreCase(name);
    }

    @Override
    public List<Profile> getProfilByMatricule(String matricule){
        return profileRepository.findUserProfiles(matricule);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.debug("Request to delete Profile : {}", id);
        profileRepository.deleteProfileFromAgentAssociation(id);
        profileRepository.deleteAssociatePrivilege(id);
        profileRepository.deleteById(id);
    }

    @Override
    public List<Profile> getList(){
       List<Profile> laListe=profileRepository.findAll();
        return laListe;
    }
}

