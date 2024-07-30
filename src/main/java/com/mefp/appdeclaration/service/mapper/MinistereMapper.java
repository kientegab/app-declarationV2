package com.mefp.appdeclaration.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.mefp.appdeclaration.entities.Ministere;
import com.mefp.appdeclaration.service.dto.MinistereDTO;

@Mapper(componentModel = "spring")
public interface MinistereMapper {

    @Mappings({
        @Mapping(target = "code", source = "ministere.code")
    })
    MinistereDTO toDto(Ministere ministere);

    @Mappings({
        @Mapping(target = "code", source = "ministereDTO.code")
    })
    Ministere toEntity(MinistereDTO ministereDTO);
}
