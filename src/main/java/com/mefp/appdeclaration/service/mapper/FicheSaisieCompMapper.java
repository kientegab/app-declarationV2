package com.mefp.appdeclaration.service.mapper;

import com.mefp.appdeclaration.entities.FicheSaisie;
import com.mefp.appdeclaration.service.dto.FicheSaisieCompDTO;
import com.mefp.appdeclaration.service.dto.FicheSaisieDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FicheSaisieCompMapper {
    FicheSaisieCompDTO  toDTO(FicheSaisie ficheSaisie);
    FicheSaisie toEntity(FicheSaisieCompDTO ficheSaisieCompDTO);
}
