package com.mefp.appdeclaration.service.mapper;

import org.mapstruct.Mapper;

import com.mefp.appdeclaration.entities.Structure;
import com.mefp.appdeclaration.service.dto.StructureDTO;

@Mapper(componentModel = "spring")
public interface StructureMapper {

    StructureDTO toDto(Structure structure);

    Structure toEntity(StructureDTO structureDTO);

    default Structure fromId(Long id) {
        if (id == null) {
            return null;
        }
        Structure structure = new Structure();
        structure.setId(id);
        return structure;
    }

}
