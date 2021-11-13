package com.mfptps.appdgessddi.service.mapper;

import com.mfptps.appdgessddi.entities.MinistereStructure;
import com.mfptps.appdgessddi.service.dto.MinistereStructureDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
@Mapper(componentModel = "spring")
public interface MinistereStructureMapper {

    @Mappings({
            @Mapping(target = "statuts", source = "statut")
    })
    MinistereStructureDTO toDto(MinistereStructure ministereS);

    @Mappings({
            @Mapping(target = "statut", source = "statuts")
    })
    MinistereStructure toEntity(MinistereStructureDTO ministereSDTO);
}
