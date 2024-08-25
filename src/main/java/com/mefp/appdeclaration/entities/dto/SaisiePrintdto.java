package com.mefp.appdeclaration.entities.dto;

import com.mefp.appdeclaration.entities.FicheSaisie;
import com.mefp.appdeclaration.service.dto.FicheSaisieDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class SaisiePrintdto {
    private InputStream logo;
    private String region;
    private String structure;
    private List<SaisiePrintList> listSaisies;


}
