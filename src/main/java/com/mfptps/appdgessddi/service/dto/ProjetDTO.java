/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.service.dto;

import com.mfptps.appdgessddi.entities.Programme;
import java.util.Date;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
//@Getter
//@Setter
public class ProjetDTO {

    // fields of Projet entity
//    @NotNull
//    @NotBlank
    private String libelle;

//    @NotNull
//    @NotBlank
    private String description;

    private String statut;

    private Date debut;

    private Date fin;

    private Programme programme;
}
