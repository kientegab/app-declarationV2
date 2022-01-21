/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mfptps.appdgessddi.service.reportentities;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActiviteRE implements Serializable {

    /**
     * ACTIVITE LEVEL
     */
    private String codeActivite;

    private String libelleActivite;

    private String indicateurActivite;

    private String cibleActivite;

    private double coutPrevisionnel;

    private String financementLibelle;

    private String structureActivite;

}
