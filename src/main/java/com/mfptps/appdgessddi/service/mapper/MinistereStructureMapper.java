package com.mfptps.appdgessddi.service.mapper;

import com.mfptps.appdgessddi.entities.MinistereStructure;
import com.mfptps.appdgessddi.service.dto.MinistereStructureDTO;
import com.mfptps.appdgessddi.service.dto.StructureDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface MinistereStructureMapper {

    @Mappings({
        @Mapping(target = "statut", source = "statut")
    })
    MinistereStructureDTO toDto(MinistereStructure ministereS);

    @Mappings({
        @Mapping(target = "statut", source = "statut")
    })
    MinistereStructure toEntity(MinistereStructureDTO ministereSDTO);

    @Mappings({
        @Mapping(target = "libelle", source = "structure.libelle"),
        @Mapping(target = "statut", source = "structure.statut"),
        @Mapping(target = "description", source = "structure.description"),
        @Mapping(target = "telephone", source = "structure.telephone"),
        @Mapping(target = "type", source = "structure.type"),
        @Mapping(target = "emailResp", source = "structure.emailResp"),
        @Mapping(target = "emailStruct", source = "structure.emailStruct"),
        @Mapping(target = "ministereId", source = "ministere.id")})
    StructureDTO toStructureDTO(MinistereStructure ministereStructure);
}