/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.service.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Getter
@Setter
public class ProgrammeDTO {

//    @NotNull
//    @NotBlank
//    @Size(max = 2)
    private String code;

//    @NotNull
//    @NotBlank
    private String libelle;

    private String description;

    private String statut;

    private Date debut;

    private Date fin;

    private String details;

}