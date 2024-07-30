/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mefp.appdeclaration.service.mapper;

import org.mapstruct.Mapper;

import com.mefp.appdeclaration.entities.Profile;
import com.mefp.appdeclaration.service.dto.ProfileDTO;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Mapper(componentModel = "spring")
public interface ProfileMapper {

    ProfileDTO toDTO(Profile profile);

    Profile toEntity(ProfileDTO profileDTO);
}
