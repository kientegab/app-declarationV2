package com.mefp.appdeclaration.service.mapper;

import com.mefp.appdeclaration.entities.Declaration;
import com.mefp.appdeclaration.service.dto.DeclarationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeclarationMapper {

    DeclarationDTO toDTO(Declaration declaration);
    Declaration toEntity(DeclarationDTO declarationDTO);
}
