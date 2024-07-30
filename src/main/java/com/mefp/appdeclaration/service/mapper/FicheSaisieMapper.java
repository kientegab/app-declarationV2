package com.mefp.appdeclaration.service.mapper;

import com.mefp.appdeclaration.entities.FicheSaisie;
import com.mefp.appdeclaration.service.dto.FicheSaisieDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FicheSaisieMapper {
    FicheSaisieDTO toDTO(FicheSaisie ficheSaisie);
    FicheSaisie toEntity(FicheSaisieDTO ficheSaisieDTO);
}
