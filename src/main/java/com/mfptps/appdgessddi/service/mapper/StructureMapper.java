package com.mfptps.appdgessddi.service.mapper;


import com.mfptps.appdgessddi.entities.Structure;
import com.mfptps.appdgessddi.service.dto.StructureDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface StructureMapper {

    @Mappings({
            @Mapping(target = "descriptions", source = "description")
    })
    StructureDTO  toDto(Structure structure);
    @Mappings({
            @Mapping(target = "description", source = "descriptions"),
            @Mapping(target = "structure", source = "structureId")

    })
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
