/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mfptps.appdgessddi.service.mapper;

import com.mfptps.appdgessddi.entities.Programme;
import com.mfptps.appdgessddi.service.dto.ProgrammeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 *
 * @author Fatogoma HEBIE <fat19ebie@gmail.com>
 */
@Mapper(componentModel = "spring")
public interface ProgrammeMapper {

    Programme toEntity(ProgrammeDTO programmeDTO);

    @Mapping(source = "code_programme", target = "code")
    @Mapping(source = "libelle_programme", target = "libelle")
    @Mapping(source = "description_programme", target = "description")
    @Mapping(source = "statut_programme", target = "statut")
    @Mapping(source = "debut_programme", target = "debut")
    @Mapping(source = "fin_programme", target = "fin")
    @Mapping(source = "details_programme", target = "details")
    ProgrammeDTO toDto(Programme programme);

}
