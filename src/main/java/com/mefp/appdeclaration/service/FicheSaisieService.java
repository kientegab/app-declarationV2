package com.mefp.appdeclaration.service;

import com.mefp.appdeclaration.service.dto.FicheSaisieDTO;
import org.springframework.stereotype.Service;

@Service
public interface FicheSaisieService{
    FicheSaisieDTO create (FicheSaisieDTO ficheSaisieDTO);
}
