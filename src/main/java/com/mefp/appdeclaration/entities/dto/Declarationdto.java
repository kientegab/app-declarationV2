package com.mefp.appdeclaration.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Declarationdto {
    private InputStream logo;
    private String region;
    private String structure;
    private List<ListDeclaration> listDeclarations;


}
