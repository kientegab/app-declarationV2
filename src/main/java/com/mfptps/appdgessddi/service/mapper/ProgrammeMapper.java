/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.service.mapper;

import com.mfptps.appdgessddi.entities.Programme;
import com.mfptps.appdgessddi.service.dto.ProgrammeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Mapper(componentModel = "spring")
public interface ProgrammeMapper {

    /**
     *
     * @param programmeDTO
     * @return
     */
//    @Mappings({
//        @Mapping(target = "code", source = "programmeDTO.code"),
//        @Mapping(target = "libelle", source = "programmeDTO.libelle"),
//        @Mapping(target = "description", source = "programmeDTO.description"),
//        @Mapping(target = "statut", source = "programmeDTO.statut"),
//        @Mapping(target = "debut", source = "programmeDTO.debut"),
//        @Mapping(target = "fin", source = "programmeDTO.fin"),
//        @Mapping(target = "details", source = "programmeDTO.details")
//    })
    // @Mappings({
    @Mapping(target = "code_programme", source = "code")
    @Mapping(target = "libelle_programme", source = "libelle")
    @Mapping(target = "description_programme", source = "description")
    @Mapping(target = "statut_programme", source = "statut")
    @Mapping(target = "debut_programme", source = "debut")
    @Mapping(target = "fin_programme", source = "fin")
    @Mapping(target = "details_programme", source = "details")
    // })
    Programme toEntity(ProgrammeDTO programmeDTO);

    /**
     *
     * @param programme
     * @return
     */
//    @Mappings({
//        @Mapping(target = "code", source = "programme.code"),
//        @Mapping(target = "libelle", source = "programme.libelle"),
//        @Mapping(target = "description", source = "programme.description"),
//        @Mapping(target = "statut", source = "programme.statut"),
//        @Mapping(target = "debut", source = "programme.debut"),
//        @Mapping(target = "fin", source = "programme.fin"),
//        @Mapping(target = "details", source = "programme.details")
//    })
    //  @Mappings({
    @Mapping(target = "code", source = "code_programme")
    @Mapping(target = "libelle", source = "libelle_programme")
    @Mapping(target = "description", source = "description_programme")
    @Mapping(target = "statut", source = "statut_programme")
    @Mapping(target = "debut", source = "debut_programme")
    @Mapping(target = "fin", source = "fin_programme")
    @Mapping(target = "details", source = "details_programme")
    //  })
    ProgrammeDTO toDto(Programme programme);
}
