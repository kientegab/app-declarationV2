/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.service.mapper;

import com.mfptps.appdgessddi.entities.Programmation;
import com.mfptps.appdgessddi.service.dto.ProgrammationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Mapper(componentModel = "spring")
public interface ProgrammationMapper {

    @Mappings({
        @Mapping(target = "structureId", source = "structure.id"),
        @Mapping(target = "projetId", source = "projet.id"),
        @Mapping(target = "sourceFinancementId", source = "sourceFinancement.id"),
        @Mapping(target = "activite", source = "activite"),
        @Mapping(target = "taches", source = "taches")})
    ProgrammationDTO toDTO(Programmation programmation);

    @Mappings({
        @Mapping(target = "coutPrevisionnel", source = "coutPrevisionnel"),
        @Mapping(target = "coutReel", source = "coutReel"),
        @Mapping(target = "cible", source = "cible"),
        @Mapping(target = "observation", source = "observation"),
        @Mapping(target = "singleton", source = "singleton"),
        @Mapping(target = "structure.id", source = "structureId"),
        @Mapping(target = "projet.id", source = "projetId"),
        @Mapping(target = "sourceFinancement.id", source = "sourceFinancementId"),
        @Mapping(target = "activite", source = "activite"),
        @Mapping(target = "taches", source = "taches")})
    Programmation toEntity(ProgrammationDTO programmationDTO);

}
