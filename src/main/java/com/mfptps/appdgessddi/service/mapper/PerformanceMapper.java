package com.mfptps.appdgessddi.service.mapper;

import com.mfptps.appdgessddi.entities.Performance;
import com.mfptps.appdgessddi.service.dto.PerformanceDTO;
import com.mfptps.appdgessddi.service.dto.PerformanceEntityDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PerformanceMapper {

    @Mappings({
        @Mapping(target = "efficacite", source = "efficacite"),
        @Mapping(target = "efficience", source = "efficience"),
        @Mapping(target = "gouvernance", source = "gouvernance"),
        @Mapping(target = "impact", source = "impact"),
        @Mapping(target = "pgs", source = "pgs")})
    PerformanceDTO toDto(Performance performance);

    @Mappings({
        @Mapping(target = "efficacite", source = "efficacite"),
        @Mapping(target = "efficience", source = "efficience"),
        @Mapping(target = "gouvernance", source = "gouvernance"),
        @Mapping(target = "impact", source = "impact"),
        @Mapping(target = "pgs", source = "pgs")})
    Performance toEntity(PerformanceDTO projetDTO);

    @Mappings({
        @Mapping(target = "id", source = "id"),
        @Mapping(target = "efficacite", source = "efficacite"),
        @Mapping(target = "efficience", source = "efficience"),
        @Mapping(target = "gouvernance", source = "gouvernance"),
        @Mapping(target = "impact", source = "impact"),
        @Mapping(target = "pgs", source = "pgs"),
        @Mapping(target = "appreciation", source = "appreciation"),
        @Mapping(target = "ponderation.id", source = "ponderationId"),
        @Mapping(target = "exercice.id", source = "exerciceId"),
        @Mapping(target = "structure.id", source = "structureId")})
    PerformanceEntityDTO toEntityOrigin(Performance performance);
}
