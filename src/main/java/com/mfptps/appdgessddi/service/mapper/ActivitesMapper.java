package com.mfptps.appdgessddi.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.mfptps.appdgessddi.entities.Activites;

import com.mfptps.appdgessddi.service.dto.ActivitesDTO;


@Mapper(componentModel = "spring")

public interface ActivitesMapper {

	@Mapping(source = "code", target = "code")
    @Mapping(source = "libelle", target = "libelle")
    @Mapping(source = "description", target = "description")
	@Mapping(source = "typeActivitesId", target = "typeActivites.id")
	
    Activites toEntity(ActivitesDTO activitesDTO);

    @Mapping(source = "code", target = "code")
    @Mapping(source = "libelle", target = "libelle")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "typeActivites.id", target = "typeActivitesId")
    ActivitesDTO toDto(Activites activites);
}
