/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.service.mapper;

import com.mfptps.appdgessddi.entities.Projet;
import com.mfptps.appdgessddi.service.dto.ProjetDTO;
import org.mapstruct.Mapper;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Mapper(componentModel = "spring")
public interface ProjetMapper {

    /**
     * Here we have the same fields name in both Projet and ProjetDTO. So,
     * values of target referency ProjetDTO fields
     *
     * @param projet
     * @return
     */
//    @Mappings({
//        @Mapping(target = "libelle", source = "projet.libelle"),
//        @Mapping(target = "description", source = "projet.description"),
//        @Mapping(target = "statut", source = "projet.statut"),
//        @Mapping(target = "debut", source = "projet.debut"),
//        @Mapping(target = "fin", source = "projet.fin"),
//        @Mapping(target = "programme", source = "projet.programme")
//    })
    ProjetDTO toDto(Projet projet);

    /**
     * Convert a DTO to Projet entity
     *
     * @param projetDTO
     * @return
     */
//    @Mappings({
//        @Mapping(target = "libelle", source = "projetDTO.libelle"),
//        @Mapping(target = "description", source = "projetDTO.description"),
//        @Mapping(target = "statut", source = "projetDTO.statut"),
//        @Mapping(target = "debut", source = "projetDTO.debut"),
//        @Mapping(target = "fin", source = "projetDTO.fin"),
//        @Mapping(target = "programme", source = "projetDTO.programme")
//    })
    Projet toEntity(ProjetDTO projetDTO);
}
